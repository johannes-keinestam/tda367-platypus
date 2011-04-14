package edu.chalmers.platypus.ctrl;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.ArrayList;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.view.resources.StateChanges;

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
	
	public void addFilterToBatch(IFilter filter) {
		Locator.getModel().addActiveFilter(filter);
	}
	
	public void addFilter(IFilter filter) {
		Locator.getModel().getFilterContainer().addFilter(filter);
	}
	
	public ArrayList<IFilter> getLoadedFilterList() {
		return Locator.getModel().getFilterContainer().getList();
	}
	
}
