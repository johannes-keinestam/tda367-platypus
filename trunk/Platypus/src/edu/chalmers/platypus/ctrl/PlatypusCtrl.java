package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.StateChanges;
import java.awt.Image;
import java.util.List;

public class PlatypusCtrl {
	private static PlatypusCtrl instance;
	
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

            ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.MODEL_RESET.toString(), null, null));

        }
	
	public void addImageToBatch(File file) {
		BatchImage newImage = new BatchImage(file);
		Locator.getModel().getImageBatch().add(newImage);
		
		PropertyChangeEvent pce = new PropertyChangeEvent(this, StateChanges.NEW_IMAGE_IN_BATCH.toString(), null, newImage);
		ComBus.notifyListeners(pce);
	}
	
	public void removeImageFromBatch(BatchImage img) {
		ArrayList<BatchImage> list = Locator.getModel().getImageBatch();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == img) {
				list.remove(i);
				PropertyChangeEvent pce = new PropertyChangeEvent(this, StateChanges.IMAGE_REMOVED_FROM_BATCH.toString(), img, null);
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
		ArrayList<IFilter> list = Locator.getModel().getActiveFilters().getList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == filter) {
				list.remove(i);
				PropertyChangeEvent pce = new PropertyChangeEvent(this, StateChanges.FILTER_REMOVED_FROM_BATCH.toString(), filter, null);
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
	
	public void setNewPreview(BatchImage preview){
		Locator.getModel().setPreview(preview);
		previewChanged();
	}
	
	public ImageIcon getPreviewOriginal(int width, int height){
                if (Locator.getModel().getPreview() == null) {
                    return null;
                }
		return new ImageIcon(Locator.getModel().getPreview().getThumbnail(width, height));
	}
	
	public ImageIcon getPreviewFiltered(int width, int height){
                if (Locator.getModel().getPreview() == null) {
                    return null;
                }
		BufferedImage original = Locator.getModel().getPreview().getThumbnail(width, height);
		
		new Thread(new ApplyFilter(original));
		
		ImageIcon preview = new ImageIcon(original);
		return preview;
	}
	
	public void previewChanged(){
		PropertyChangeEvent pce = new PropertyChangeEvent(this, StateChanges.NEW_PREVIEW_IMAGE.toString(), null, null);
		ComBus.notifyListeners(pce);
		PropertyChangeEvent pce2 = new PropertyChangeEvent(this, StateChanges.PREVIEW_IMAGE_UPDATED.toString(), null, null);
		ComBus.notifyListeners(pce2);
	}
	
	public void saveImages(String path, String ext) {
		ArrayList<BatchImage> imageBatch = Locator.getModel().getImageBatch();
		for (BatchImage batchImage : imageBatch) {
			ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.PROCESSING_IMAGE.toString(), batchImage, batchImage));
			BufferedImage filteredImage = batchImage.getImage();
			
			new Thread(new ApplyFilter(filteredImage)).start();
			
			File outputFile = new File(path + File.separatorChar + batchImage.getFileName() + "_new." + ext);
			try {				
				ImageIO.write(filteredImage, ext, outputFile);
				ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.SAVED_IMAGE.toString(), outputFile, outputFile));
			} catch (IOException e) {
				System.out.println("Failed to write image: " + outputFile.getName());
				e.printStackTrace();
			}
		}
                ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.SAVE_OPERATION_FINISHED.toString(), null, null));
	
	}

        public void abortSaveOperation() {
            // TODO add code
        }
}
