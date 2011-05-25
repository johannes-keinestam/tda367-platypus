
package edu.chalmers.platypus.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;



public class ActiveFilters {
        private static ActiveFilters activeFiltersObject;
        private ArrayList<IFilter> activeFilters = new ArrayList<IFilter>();

        /** Constructor */
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
        public void savePreset(final String name){
        	//Saves the preset file
        	File f = new File(System.getProperty("user.home")+"/PlatyPix/Presets/"+name+"/");
    		try{
    			f.mkdir();
    		}catch(Exception e){
    		}
    		
    		for (IFilter filter : activeFilters) {
    			try {
    				FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"/PlatyPix/Presets/"+name+"/"+filter.getName()+".preset");
    				ObjectOutputStream oos;
    				try {
    					 oos = new ObjectOutputStream(fos);
    					 oos.writeObject(filter.getState());
    			         oos.close();
    				} 
    				catch (IOException e) {
    					e.printStackTrace();
    				}
    				catch (NullPointerException e){
    					e.printStackTrace();
    				}
    			}
    			catch (FileNotFoundException e) {	
    				e.printStackTrace();
    			}
    		}
			//Saves the info file
			FileOutputStream fos;
    		try {
				fos = new FileOutputStream(System.getProperty("user.home")+"/PlatyPix/Presets/"+name+".info");
				ObjectOutputStream oos;
				try {
					 oos = new ObjectOutputStream(fos);
					 String[] filters = new String[activeFilters.size()];
					 for (int i = 0; i < activeFilters.size(); i++) {
						filters[i] = activeFilters.get(i).getName();
					}
					 oos.writeObject(new Preset(name, filters));
			         oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
	           
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
        }
        
}