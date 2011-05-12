
package edu.chalmers.platypus.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;

import org.apache.commons.io.input.ClassLoaderObjectInputStream;

import edu.chalmers.platypus.util.ComBus;

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
        
        public void savePreset(final String name){
        	//Saves the preset file
        	File f = new File(System.getenv("USERPROFILE")+"/PlatyPix/Presets/"+name+"/");
    		try{
    			f.mkdir();
    		}catch(Exception e){
    		//e.printStacktrace();
    		}
    		
    		for (IFilter filter : activeFilters) {
    			filter.saveState(name);
    		}
			//Saves the info file
			FileOutputStream fos;
    		try {
				fos = new FileOutputStream(System.getenv("USERPROFILE")+"/PlatyPix/Presets/"+name+".info");
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        
}