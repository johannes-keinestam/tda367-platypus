package edu.chalmers.platypus.model;

import java.beans.PropertyChangeEvent;
import java.util.ArrayList;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.util.StateChanges;

public class ActiveFilters {
	private static ActiveFilters activeFiltersObject;
	private ArrayList<IFilter> activeFilters = new ArrayList<IFilter>();
	
	private ActiveFilters(){

	}
	
	public static ActiveFilters getActiveFilters(){
		if (activeFiltersObject == null) {
			activeFiltersObject = new ActiveFilters();
		}
		return activeFiltersObject;
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
