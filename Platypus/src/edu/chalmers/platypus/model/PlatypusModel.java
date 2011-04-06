package edu.chalmers.platypus.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.chalmers.platypus.view.resources.StateChanges;

public class PlatypusModel {
	private final ActiveFilters activeFilters;
	private final FilterContainer filterContainer;
	private final ArrayList<BatchImage> imageBatch = new ArrayList<BatchImage>();
	private static PlatypusModel instance;
	private final List<PropertyChangeListener> listeners = new LinkedList<PropertyChangeListener>();
	
	private PlatypusModel() {
		activeFilters = ActiveFilters.getActiveFilters();
		filterContainer = FilterContainer.getFilerContainerObject();
	}
	
	public static PlatypusModel getInstance() {
		if (instance == null) {
			instance = new PlatypusModel();
		}
		return instance;
	}
	
	public void addImageToBatch(URL pathToImage) {
		BatchImage newImage = new BatchImage();
		imageBatch.add(newImage);
		PropertyChangeEvent pce = new PropertyChangeEvent(this, StateChanges.NEW_IMAGE_IN_BATCH.toString(), null, newImage);
		notifyListeners(pce);
	}
	
	public void addListener(PropertyChangeListener pcl) {
		listeners.add(pcl);
	}
	
	private void notifyListeners(PropertyChangeEvent pce) {
		for (PropertyChangeListener pcl : listeners) {
			pcl.propertyChange(pce);
		}
	}
	
}
