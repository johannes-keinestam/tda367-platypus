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
import edu.chalmers.platypus.util.StateChanges;

public class PlatypusCtrl {
	private static PlatypusCtrl instance;
	
	private PlatypusCtrl() {}
	
	public static PlatypusCtrl getInstance() {
		if (instance == null) {
			instance = new PlatypusCtrl();
		}
		return instance;
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
	
	public ArrayList<IFilter> getLoadedFilterList() {
		return Locator.getModel().getFilterContainer().getList();
	}
	
	public void setNewPreview(BatchImage preview){
		Locator.getModel().setPreview(preview);
	}
	
	public ImageIcon getPreviewOriginal(){
		return Locator.getModel().getPreview().getThumbnail();
	}
	
//	public ImageIcon getPreviewFiltered(){
//		BufferedImage qwer = Locator.getModel().getPreview().getImage();
//		applyFilter(qwer);
//		ImageIcon asdf = buffered image to image icon conversion 
//		return asdf;
//	}
	
	public void saveImages(String path) {
		
		ArrayList<BatchImage> imgBatch = Locator.getModel().getImageBatch();
		ArrayList<IFilter> activeFilters = Locator.getModel().getActiveFilters().getList();
		for (BatchImage batchImg : imgBatch) {
			for (IFilter filter : activeFilters) {
				//BufferedImage filteredImg = filter.applyFilter(batchImg.getImage()); removed until valid filters are present
				BufferedImage filteredImg = batchImg.getImage();
				try {
					ImageIO.write(filteredImg, "jpg", new File(path + File.separatorChar + batchImg.getFileName() + "_new." + "jpg"));
				} catch (IOException e) {
					System.out.println("Failed to write image: " + "test.jpg");
					e.printStackTrace();
				}
			}	
		}
	}
	
}
