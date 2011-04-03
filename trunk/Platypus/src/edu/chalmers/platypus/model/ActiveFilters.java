package edu.chalmers.platypus.model;

import java.util.ArrayList;

public class ActiveFilters {
	private static ActiveFilters activeFiltersObject;
	private ArrayList<Filter> activeFilters = new ArrayList<Filter>();
	
	private ActiveFilters(){

	}
	
	public static  ActiveFilters getActiveFilters(){
		if (activeFiltersObject == null) {
			activeFiltersObject = new ActiveFilters();
		}
		return activeFiltersObject;
	}
	public void addFilter(Filter filter){
		activeFilters.add(filter);
	}
	public ArrayList<Filter> getList(){
		return activeFilters;
	}
	public void removeAll(){
		activeFilters.clear();
	}
	public void loadPreset(ArrayList<Filter> preset){
		//TODO
	}
	
}
