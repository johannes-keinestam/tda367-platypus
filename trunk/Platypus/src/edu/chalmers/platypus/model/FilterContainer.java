package edu.chalmers.platypus.model;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import edu.chalmers.platypus.Locator;

public class FilterContainer {
	
	private static FilterContainer filterContainer;
	private ArrayList<Filter> loadedFilters = new ArrayList<Filter>();
	
	private FilterContainer(){
		//scanForFilters();
	}
	
	public static  FilterContainer getFilerContainerObject(){
		if (filterContainer == null) {
			filterContainer = new FilterContainer();
		}
		return filterContainer;
	}
	
	private void addFilter(File file){
		//TODO
	}
	public Filter getFilter(String name){
		for (Filter filter : loadedFilters) {
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
				Filter f = (Filter) filter.newInstance();
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
				url[0] = new URL("file:///"+System.getenv("USERPROFILE")+"/Platypus/Filters"+listOfFiles[i].getName());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  		importFilter(url);
	      }
		
	    }
    }
}
