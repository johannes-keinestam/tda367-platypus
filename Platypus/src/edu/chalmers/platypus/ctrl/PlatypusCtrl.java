package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.Locator;
import edu.chalmers.platypus.util.StateChanges;

public class PlatypusCtrl implements IImageCtrl, IFilterCtrl, IPreviewCtrl, IPresetCtrl {
	private static PlatypusCtrl instance;

	private PlatypusCtrl() {
	}

	public static PlatypusCtrl getInstance() {
		if (instance == null) {
			instance = new PlatypusCtrl();
		}
		return instance;
	}

	public void resetModel() {
		Locator.getModel().getActiveFilters().getList().clear();

		Locator.getModel().getImageBatch().clear();

		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.MODEL_RESET.toString(), null, null));

	}

	public void addImageToBatch(File file) {
		BatchImage newImage = new BatchImage(file);
		Locator.getModel().getImageBatch().add(newImage);

		PropertyChangeEvent pce = new PropertyChangeEvent(this,
				StateChanges.NEW_IMAGE_IN_BATCH.toString(), null, newImage);
		ComBus.notifyListeners(pce);
	}

	public void removeImageFromBatch(BatchImage img) {
		ArrayList<BatchImage> list = Locator.getModel().getImageBatch();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == img) {
				list.remove(i);
				PropertyChangeEvent pce = new PropertyChangeEvent(this,
						StateChanges.IMAGE_REMOVED_FROM_BATCH.toString(), img,
						null);
				ComBus.notifyListeners(pce);
				break;
			}
		}
		if (list.isEmpty()) {
			PropertyChangeEvent pce = new PropertyChangeEvent(this,
					StateChanges.IMAGE_BATCH_EMPTY.toString(), img, null);
			ComBus.notifyListeners(pce);
		}
	}

        public void clearImageBatch() {
            Locator.getModel().getImageBatch().clear();
            PropertyChangeEvent pce = new PropertyChangeEvent(this,
					StateChanges.IMAGE_BATCH_CLEARED.toString(), null, null);
            ComBus.notifyListeners(pce);
        }

	public void saveImages(String path, String ext) {
		RunBatch.start(path, ext);
	}

	public void abortSaveOperation() {
		RunBatch.stop();
	}
	
	public ArrayList<IFilter> getLoadedFilterList() {
		return Locator.getModel().getFilterContainer().getList();
	}
	
	public void addFilterToBatch(IFilter filter) {
		PropertyChangeEvent pce;
		if (!Locator.getModel().getActiveFilters().getList().contains(filter)) {
			Locator.getModel().getActiveFilters().getList().add(filter);
			if (filter instanceof Observable) {
				((Observable) filter).addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						ComBus.notifyListeners(new PropertyChangeEvent(this,
								StateChanges.PREVIEW_IMAGE_UPDATED.toString(),
								null, o));
					}
				});
			}
			pce = new PropertyChangeEvent(this,
					StateChanges.NEW_FILTER_ADDED_TO_BATCH.toString(), null,
					filter);
		} else {
			pce = new PropertyChangeEvent(this,
					StateChanges.ERROR_OCCURED.toString(), null,
					"The selected filter is already in use");
			ComBus.notifyListeners(pce);
			pce = new PropertyChangeEvent(this,
					StateChanges.DUPLICATE_FILTER_SELECTED.toString(), null,
					null);
		}
		ComBus.notifyListeners(pce);
	}

	public void removeFilterFromBatch(IFilter filter) {
		ArrayList<IFilter> list = Locator.getModel().getActiveFilters()
				.getList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == filter) {
				list.remove(i);
				PropertyChangeEvent pce = new PropertyChangeEvent(this,
						StateChanges.FILTER_REMOVED_FROM_BATCH.toString(),
						filter, null);
				ComBus.notifyListeners(pce);
				break;
			}
		}

	}

	public void addFilter(IFilter filter) {
		Locator.getModel().getFilterContainer().addFilter(filter);
		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.NEW_FILTER_ADDED_TO_APPLICATION.toString(), null,
				filter));
	}

	public void importNewFilter(File filter) {
		URL filterURL = null;
		URL url[] = new URL[1];

		try {
			filterURL = filter.toURI().toURL();
			url[0] = filterURL;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (Locator.getModel().getFilterContainer().importFilter(url)) {
			copyNewFilter(filter);
		} else {
			PropertyChangeEvent pce = new PropertyChangeEvent(this,
					StateChanges.ERROR_OCCURED.toString(), null,
					"The specified file is not a valid filter");
			ComBus.notifyListeners(pce);
		}
	}

	public void copyNewFilter(File filter) {
		String filterFileName = filter.getPath();

		FileChannel outputChannel = null;
		FileChannel sourceChannel = null;

		File outputFile = new File(System.getenv("USERPROFILE")
				+ "/PlatyPix/Filters/"
				+ filterFileName.substring(
						filterFileName.lastIndexOf(File.separator) + 1,
						filterFileName.length()));
		try {
			sourceChannel = new FileInputStream(filter).getChannel();
			outputChannel = new FileOutputStream(outputFile).getChannel();

			try {
				outputChannel.transferFrom(sourceChannel, 0,
						sourceChannel.size());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (sourceChannel != null)
				try {
					sourceChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			if (outputChannel != null)
				try {
					outputChannel.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}


	public void setNewPreview(BatchImage preview) {
		Locator.getModel().setPreview(preview);
		previewChanged();
	}

	public ImageIcon getPreviewOriginal(int width, int height) {
		if (Locator.getModel().getPreview() == null) {
			return null;
		}
		return new ImageIcon(Locator.getModel().getPreview()
				.getThumbnail(width, height));
	}

	public ImageIcon getPreviewFiltered(int width, int height) {
		if (Locator.getModel().getPreview() == null) {
			return null;
		}
		BufferedImage original = Locator.getModel().getPreview()
				.getThumbnail(width, height);

		ImageIcon preview = new ImageIcon(RunBatch.getFilteredImage(original));
		return preview;
	}

	public void previewChanged() {
		PropertyChangeEvent pce = new PropertyChangeEvent(this,
				StateChanges.NEW_PREVIEW_IMAGE.toString(), null, null);
		ComBus.notifyListeners(pce);
		pce = new PropertyChangeEvent(this,
				StateChanges.PREVIEW_IMAGE_UPDATED.toString(), null, null);
		ComBus.notifyListeners(pce);
	}
	
	public ArrayList<Preset> getLoadedPresetList() {
		return Locator.getModel().getPresets();
	}

	public void loadPreset(Preset preset) {
		for (String name : preset.getFilters()) {
			IFilter filter = Locator.getModel().getFilterContainer().getFilter(name);
			filter.loadState(preset.getName());
			addFilterToBatch(filter);
		}
	}
	
	public void savePreset(String name) {
		Locator.getModel().getActiveFilters().savePreset(name);
	}
	


}
