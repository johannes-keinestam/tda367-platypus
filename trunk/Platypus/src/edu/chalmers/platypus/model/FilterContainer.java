package edu.chalmers.platypus.model;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

public class FilterContainer {
	
	private static FilterContainer filterContainer;
	private ArrayList<Filter> loadedFilters = new ArrayList<Filter>();
	
	private FilterContainer(){
		scanForFilters();
	}
	
	public static  FilterContainer getFilerConatinerObject(){
		if (filterContainer == null) {
			filterContainer = new FilterContainer();
		}
		return filterContainer;
	}
	
	private void addFilter(File file){
		
	}
	public Filter getFilter(String name){
		for (ArrayList<Filter> loadedFilters : f) {
			if(f.getName.equals(name)){
				return f;
			}
		}
			
		}
	
	public void importFilter(URL[] url){
		URLClassLoader loader = new URLClassLoader(url); 
		Class filter;
		try {
			filter = loader.loadClass("filter.Filter");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			loadedFilters.add((Filter) filter.newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void scanForFilters(){
		File folder = new File(System.getenv("USERPROFILE")+"/Platypus/Filters");
	    File[] listOfFiles = folder.listFiles();

	    for (int i = 0; i < listOfFiles.length; i++) {
	      if (listOfFiles[i].isFile()) {
	    	URL url[] = new URL[1]; 
	  		url[0] = new URL("file:///"+System.getenv("USERPROFILE")+"/Platypus/Filters"+listOfFiles[i].getName());
	  		importFilter(url);
	      }
		
	    }

    }
}
