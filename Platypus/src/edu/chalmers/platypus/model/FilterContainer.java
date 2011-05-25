package edu.chalmers.platypus.model;

import java.util.ArrayList;

public class FilterContainer {

    private static FilterContainer filterContainer;
    private ArrayList<IFilter> loadedFilters = new ArrayList<IFilter>();

    /**
     * Constructor.
     */
    private FilterContainer() {
    }

    /**
     * Singleton.
     * 
     * @return the instance of this object
     */
    public static FilterContainer getFilterContainerObject() {
	if (filterContainer == null) {
	    filterContainer = new FilterContainer();
	}
	return filterContainer;
    }

    /**
     * Adds a filter to the list of filters loaded into the program.
     * 
     * @param filter
     *            the filter
     */
    public void addFilter(IFilter filter) {
	loadedFilters.add(filter);
    }

    /**
     * Getter for a copy of the list of filters loaded into the program.
     * 
     * @return copy of the list
     */
    public ArrayList<IFilter> getList() {
	return new ArrayList<IFilter>(loadedFilters);
    }

    /**
     * Getter for a filter with the specified name. Returns null if not found.
     * 
     * @param name
     *            name of the filter
     * @return the filter, or null if not found.
     */
    public IFilter getFilter(String name) {
	for (IFilter filter : loadedFilters) {
	    if (filter.getName().equals(name)) {
		return filter;
	    }
	}
	return null;
    }
}