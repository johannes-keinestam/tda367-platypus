package edu.chalmers.platypus.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.view.resources.StateChanges;

public class ActiveFilters {
	private static ActiveFilters activeFiltersObject;
	private ArrayList<IFilter> activeFilters = new ArrayList<IFilter>();
	
	private ActiveFilters(){

	}
	
	public static  ActiveFilters getActiveFilters(){
		if (activeFiltersObject == null) {
			activeFiltersObject = new ActiveFilters();
		}
		return activeFiltersObject;
	}
	public void addFilter(IFilter filter){
		activeFilters.add(filter);
		PropertyChangeEvent pce = new PropertyChangeEvent(this, 
				StateChanges.NEW_FILTER_ADDED_TO_BATCH.toString(), null, filter);
		ComBus.notifyListeners(pce);
	}
	
	public ArrayList<IFilter> getList(){
		return activeFilters;
	}
	public void removeAll(){
		activeFilters.clear();
	}
	public void loadPreset(ArrayList<IFilter> preset){
		//TODO
	}
	
}
