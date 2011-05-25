package edu.chalmers.platypus.util.interfaces;

import java.io.File;
import java.util.ArrayList;

import edu.chalmers.platypus.model.IFilter;

public interface IFilterCtrl {
    public ArrayList<IFilter> getLoadedFilterList();

    public void addFilterToBatch(IFilter filter);

    public void removeFilterFromBatch(IFilter filter);

    public void addFilter(IFilter filter);

    public void importNewFilter(File filter);

    public void copyNewFilter(File filter);

    public void scanForFilters();
}
