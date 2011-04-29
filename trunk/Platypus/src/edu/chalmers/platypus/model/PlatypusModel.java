package edu.chalmers.platypus.model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.util.StateChanges;

public class PlatypusModel {
	private final ActiveFilters activeFilters;
	private final FilterContainer filterContainer;
	private final ArrayList<BatchImage> imageBatch = new ArrayList<BatchImage>();
	private static PlatypusModel instance;
	private BatchImage preview;
	
	private PlatypusModel() {
		activeFilters = ActiveFilters.getActiveFilters();
		filterContainer = FilterContainer.getFilterContainerObject();
	}
	
	public static PlatypusModel getInstance() {
		if (instance == null) {
			instance = new PlatypusModel();
		}
		return instance;
	}
	
	public ArrayList<BatchImage> getImageBatch() {
		return imageBatch;
	}
	
	public int getBatchSize() {
		return imageBatch.size();
	}
		
	public FilterContainer getFilterContainer() {
		return filterContainer;
	}
	
	public ActiveFilters getActiveFilters() {
		return activeFilters;
	}
	
	public void setPreview(BatchImage preview){
		this.preview = preview;
	}
	
	public BatchImage getPreview(){
		return preview;
	}
}
