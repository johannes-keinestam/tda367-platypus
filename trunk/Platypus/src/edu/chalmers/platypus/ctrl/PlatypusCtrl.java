package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.Locator;
import edu.chalmers.platypus.util.StateChanges;

public class PlatypusCtrl {
	private static PlatypusCtrl instance;
	private RunBatch runBatch = new RunBatch();

	private PlatypusCtrl() {}

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
	}

	public void addFilterToBatch(IFilter filter) {
		Locator.getModel().getActiveFilters().getList().add(filter);
		PropertyChangeEvent pce = new PropertyChangeEvent(this,
				StateChanges.NEW_FILTER_ADDED_TO_BATCH.toString(), null, filter);
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
	}

	public void loadPreset(Preset preset) {
		Locator.getModel().getActiveFilters().loadPreset(preset);
	}

	public void savePreset(String name) {
		Locator.getModel().getActiveFilters().savePreset(name);
	}

	public ArrayList<IFilter> getLoadedFilterList() {
		return Locator.getModel().getFilterContainer().getList();
	}

	public ArrayList<Preset> getLoadedPresetList() {
		return Locator.getModel().getPresets();
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

		ImageIcon preview = new ImageIcon(runBatch.getFilteredImage(original));
		return preview;
	}

	public void previewChanged() {
		PropertyChangeEvent pce = new PropertyChangeEvent(this,
				StateChanges.NEW_PREVIEW_IMAGE.toString(), null, null);
		ComBus.notifyListeners(pce);
		PropertyChangeEvent pce2 = new PropertyChangeEvent(this,
				StateChanges.PREVIEW_IMAGE_UPDATED.toString(), null, null);
		ComBus.notifyListeners(pce2);
	}

	public void saveImages(String path, String ext) {
		runBatch.start(path, ext);
	}

	public void abortSaveOperation() {
		runBatch.stop();
	}
	
	public void importNewFilter(URL filter){
		URL url[] = new URL[1];
		url[0] = filter;
		if(Locator.getModel().getFilterContainer().importFilter(url)){
			
		}else{
			PropertyChangeEvent pce = new PropertyChangeEvent(this,
					StateChanges.ERROR_OCCURED.toString(), null, "The specified file is not a valid filter");
			ComBus.notifyListeners(pce);
		}
	}
}
