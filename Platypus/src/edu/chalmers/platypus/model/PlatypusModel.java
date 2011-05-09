package edu.chalmers.platypus.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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
	public ArrayList<Preset> getPresets(){
		 File folder = new File(System.getenv("USERPROFILE")+"/Platypus/Presets");
		 File[] listOfFiles = folder.listFiles();
		 FileInputStream fis;
		 ArrayList<Preset> presets = new  ArrayList<Preset>();
		 for (int i = 0; i < listOfFiles.length; i++) {
	    	 if (listOfFiles[i].isFile()) {
	    		 Preset pe;	
	    		 try {
	    			 fis = new FileInputStream(listOfFiles[i]);
	    			 ObjectInputStream ois;
	    			 try {
	    				 ois = new ObjectInputStream(fis);
	    				 try {
	    					 pe = (Preset) ois.readObject();
	    					 presets.add(pe);
     					
	    				 } catch (ClassCastException e) {
	    					 // TODO Auto-generated catch block
	    					 //e.printStackTrace();
	    				 } catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
	    				 }
     	             ois.close();
	    			 } 
	    			 catch (IOException e) {
	    				 // TODO Auto-generated catch block
	    				 e.printStackTrace();
     			}
     	           
     		} 
	    	catch (FileNotFoundException e) {
     			e.printStackTrace();
     		}
                   
                   
         }
           
       }
		
		return presets;	
	}
}
