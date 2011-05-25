package edu.chalmers.platypus.model;

import java.util.ArrayList;

public class FilterContainer {

	private static FilterContainer filterContainer;
	private ArrayList<IFilter> loadedFilters = new ArrayList<IFilter>();

	private FilterContainer() {
		//scanForFilters();
	}

	public static FilterContainer getFilterContainerObject() {
		if (filterContainer == null) {
			filterContainer = new FilterContainer();
		}
		return filterContainer;
	}

	public void addFilter(IFilter filter) {
		loadedFilters.add(filter);
	}

	public ArrayList<IFilter> getList() {
		return new ArrayList<IFilter>(loadedFilters);
	}

	public IFilter getFilter(String name) {
		for (IFilter filter : loadedFilters) {
			if (filter.getName().equals(name)) {
				return filter;
			}
		}
		return null;
	}
}