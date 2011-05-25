package edu.chalmers.platypus.ctrl.impl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ImageIcon;

import edu.chalmers.platypus.ctrl.IFilterCtrl;
import edu.chalmers.platypus.ctrl.IImageCtrl;
import edu.chalmers.platypus.ctrl.IMiscCtrl;
import edu.chalmers.platypus.ctrl.IPresetCtrl;
import edu.chalmers.platypus.ctrl.IPreviewCtrl;
import edu.chalmers.platypus.ctrl.RunBatch;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.util.StateChanges;


/**
 * Class responsible for all logic in the program.  
 * Implements the interfaces IFilterCtrl, IImageCtrl, IPresetCtrl,
 * IMiscCtrl and IPreviewCtrl
 *
 */
public class PlatypusCtrl implements IImageCtrl, IFilterCtrl, IPreviewCtrl, 
									 IPresetCtrl, IMiscCtrl {
	private static PlatypusCtrl instance;

	/**
	 * Constructor
	 */
	private PlatypusCtrl() {
	}
	
	/**
	 * Returns the singleton instance of this Class, if none exist
	 * the constructor is run and the instance is set.
	 * 
	 * @return the singleton instance of the PlatypusCtrl.
	 */
	public static PlatypusCtrl getInstance() {
		if (instance == null) {
			instance = new PlatypusCtrl();
		}
		return instance;
	}

	/**
	 * Resets the PlatypusModel, clearing both ActiveFilters and ImageBatch 
	 * of content. 
	 * 
	 */
	public void resetModel() {
		ModelLocator.getModel().getActiveFilters().getList().clear();

		ModelLocator.getModel().getImageBatch().clear();

		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.MODEL_RESET.toString(), null, null));

	}

	/**
 	* Adds the specified image to the imagebatch. Also informs all Listeners on 
 	* the ComBus of the NEW_IMAGE_IN_BATCH event.
 	* 
 	* @param File representing the image to be added.
 	*/
	public void addImageToBatch(File file) {
		BatchImage newImage = new BatchImage(file);
		ModelLocator.getModel().getImageBatch().add(newImage);

		PropertyChangeEvent pce = new PropertyChangeEvent(this,
				StateChanges.NEW_IMAGE_IN_BATCH.toString(), null, newImage);
		ComBus.notifyListeners(pce);
	}

	/**
	 * If the ImageBatch contains the specified image it is removed and all 
	 * listeners on the ComBus is notified of the IMAGE_REMOVED_FROM_BATCH event.
	 * 
	 * @param the BatchImage to be removed from the batch.
	 */
	public void removeImageFromBatch(BatchImage img) {
		ArrayList<BatchImage> list = ModelLocator.getModel().getImageBatch();
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
		/**
		 * Clears the ImageBatch of all content, notifies listeners on the
		 * ComBus of the IMAGE_BATCH_CLEARED event.
		 */
        public void clearImageBatch() {
            ModelLocator.getModel().getImageBatch().clear();
            PropertyChangeEvent pce = new PropertyChangeEvent(this,
					StateChanges.IMAGE_BATCH_CLEARED.toString(), null, null);
            ComBus.notifyListeners(pce);
        }

        /**
         * Runs the saveImages operation by starting the runnable class
         * RunBatch, 
         * 
         */
	public void saveImages(String path, String ext) {
		RunBatch.start(path, ext);
	}

	/**
	 * Sends the abort operation to the RunBatch class, 
	 * 
	 */
	public void abortSaveOperation() {
		RunBatch.stop();
	}
	
	/**
	 * Returns a list of filters available for use in the program.
	 * 
	 * @return the list of filters.
	 */
	public ArrayList<IFilter> getLoadedFilterList() {
		return ModelLocator.getModel().getFilterContainer().getList();
	}
	
	/**
	 * Adds the specified filter to the batch of active filters.
	 * 
	 * @param the filter to be added to the batch.
	 */
	public void addFilterToBatch(IFilter filter) {
		PropertyChangeEvent pce;
		if (!ModelLocator.getModel().getActiveFilters().getList().contains(filter)) {
			ModelLocator.getModel().getActiveFilters().getList().add(filter);
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

	/**
	 * Removes the specified filter from the batch of active filters.
	 * 
	 * @param the filter to be removed from the batch.
	 */
	public void removeFilterFromBatch(IFilter filter) {
		ArrayList<IFilter> list = ModelLocator.getModel().getActiveFilters()
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

	
	/**
	 * Adds the filter to the program and notifies listeners on the ComBus 
	 * of the NEW_FILTER_ADDED_TO_APPLICATION event.
	 * 
	 * @param the filter to be added
	 */
	public void addFilter(IFilter filter) {
		ModelLocator.getModel().getFilterContainer().addFilter(filter);
		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.NEW_FILTER_ADDED_TO_APPLICATION.toString(), null,
				filter));
	}

	
	/**
	 * Adds an instance if the file is a filter and proceeds to copy the file
	 * to the PlatyPix/Filters directory.
	 * 
	 * @param a file representing the filter to be imported
	 */
	public void importNewFilter(File filter) {
		URL filterURL = null;
		URL url[] = new URL[1];

		try {
			filterURL = filter.toURI().toURL();
			url[0] = filterURL;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (ModelLocator.getModel().getFilterContainer().importFilter(url)) {
			copyNewFilter(filter);
		} else {
			PropertyChangeEvent pce = new PropertyChangeEvent(this,
					StateChanges.ERROR_OCCURED.toString(), null,
					"The specified file is not a valid filter");
			ComBus.notifyListeners(pce);
		}
	}

	/**
	 * Copies the file to the PlatyPix/Filters directory. The file is assumed
	 * to be a filter.
	 * 
	 * @param the file to be copied
	 */
	public void copyNewFilter(File filter) {
		String filterFileName = filter.getPath();

		FileChannel outputChannel = null;
		FileChannel sourceChannel = null;

		File outputFile = new File(System.getProperty("user.home")
				+ "/PlatyPix/Filters/"
				+ filterFileName.substring(
						filterFileName.lastIndexOf(File.separator) + 1,
						filterFileName.length()));
		
		if(!filter.equals(outputFile)){
			
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

	}


	/**
	 * Sets the specified image as the new preview and calls the
	 * previewChanged method
	 * 
	 * @param the new preview
	 */
	public void setNewPreview(BatchImage preview) {
		ModelLocator.getModel().setPreview(preview);
		previewChanged();
	}

	/**
	 * Returns a scaled but unfiltered version of the currently 
	 * selected preview.
	 * 
	 * @return an unfiltered version of the preview.
	 */
	public ImageIcon getPreviewOriginal(int width, int height) {
		if (ModelLocator.getModel().getPreview() == null) {
			return null;
		}
		return new ImageIcon(ModelLocator.getModel().getPreview()
				.getThumbnail(width, height));
	}

	/**
	 * Returns a scaled and filtered version of the currently
	 * selected preview.
	 * 
	 * @return a filtered version of the preview.
	 */
	public ImageIcon getPreviewFiltered(int width, int height) {
		if (ModelLocator.getModel().getPreview() == null) {
			return null;
		}
		BufferedImage original = ModelLocator.getModel().getPreview()
				.getThumbnail(width, height);

		ImageIcon preview = new ImageIcon(RunBatch.getFilteredImage(original));
		return preview;
	}

	/**
	 * Called when the GUI has changed any values on filters to provide an
	 * updated version of the preview or a new preview has been selected.
	 * 
	 */
	public void previewChanged() {
		PropertyChangeEvent pce = new PropertyChangeEvent(this,
				StateChanges.NEW_PREVIEW_IMAGE.toString(), null, null);
		ComBus.notifyListeners(pce);
		pce = new PropertyChangeEvent(this,
				StateChanges.PREVIEW_IMAGE_UPDATED.toString(), null, null);
		ComBus.notifyListeners(pce);
	}
	
	/**
	 * Returns all the available presets in the PlatyPix/Presets directory
	 * 
	 * @return returns all the available presets
	 */
	public ArrayList<Preset> getLoadedPresetList() {
		return ModelLocator.getModel().getPresets();
	}

	/**
	 * Loads the preset into the program, all filters contained in the preset is 
	 * added to the filter batch.
	 * 
	 * @param the preset to be loaded into the program
	 */
	public void loadPreset(Preset preset) {
		for (String name : preset.getFilters()) {
			IFilter filter = ModelLocator.getModel().getFilterContainer().getFilter(name);
			FileInputStream fis;

			try {
				fis = new FileInputStream(System.getProperty("user.home")+"/PlatyPix/Presets/"+preset.getName()+"/"+filter.getName()+".preset");
				try {
					ObjectInputStream ois = new ObjectInputStream(fis);
					try {
						filter.setState((Object[]) ois.readObject());
					
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			addFilterToBatch(filter);	
		}

		
	}
	
	/**
	 * Saves the current batch of filters as a preset to the
	 * PlatyPix/Presets directory.
	 * 
	 * @param the name of the preset
	 */
	public void savePreset(String name) {
		ModelLocator.getModel().getActiveFilters().savePreset(name);
	}
	


}
