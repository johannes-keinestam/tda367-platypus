package edu.chalmers.platypus.model;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.view.resources.StateChanges;

public class FilterContainer {
	
	private static FilterContainer filterContainer;
	private ArrayList<IFilter> loadedFilters = new ArrayList<IFilter>();
	
	private FilterContainer(){
		//scanForFilters();
	}
	
	public static  FilterContainer getFilterContainerObject(){
		if (filterContainer == null) {
			filterContainer = new FilterContainer();
		}
		return filterContainer;
	}
	
	public void addFilter(IFilter filter){
		loadedFilters.add(filter);
		PropertyChangeEvent pce = new PropertyChangeEvent(this, 
				StateChanges.NEW_FILTER_ADDED_TO_APPLICATION.toString(), null, filter);
		ComBus.notifyListeners(pce);

	}
	
	public ArrayList<IFilter> getList() {
		return new ArrayList<IFilter>(loadedFilters);
	}
	
	public IFilter getFilter(String name){
		for (IFilter filter : loadedFilters) {
			if(filter.getName().equals(name)){
				return filter;
			}
		}
		return null;
	}
	
	public void importFilter(URL[] url){
		URLClassLoader loader = new URLClassLoader(url); 
		try {
			Class filter = loader.loadClass("edu.chalmers.platypus.model.FilterClass");
			try {
				IFilter f = (IFilter) filter.newInstance();
				loadedFilters.add(f);
				System.out.println(f.getName()+ " loaded");
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	public void scanForFilters(){
		File folder = new File(System.getenv("USERPROFILE")+"/Platypus/Filters");
		
	    File[] listOfFiles = folder.listFiles();
		            for (int i = 0; i < listOfFiles.length; i++) {
              if (listOfFiles[i].isFile()) {
                URL url[] = new URL[1]; 
                        try {
                                url[0] = new URL("file:///"+System.getenv("USERPROFILE")+"/Platypus/Filters/"+listOfFiles[i].getName());
                        } catch (MalformedURLException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                        }
                        System.out.println(url[0]);
                        importFilter(url);
              }
                
            }
    }
}