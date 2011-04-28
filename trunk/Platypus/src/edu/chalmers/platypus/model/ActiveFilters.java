
package edu.chalmers.platypus.model;

import java.beans.PropertyChangeEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.util.StateChanges;

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
        public void loadPreset(String name){
        	FileInputStream fis;
			try {
				fis = new FileInputStream(System.getenv("USERPROFILE")+"/Platypus/Presets/"+name+".preset");
				ObjectInputStream ois;
				try {
					ois = new ObjectInputStream(fis);
					try {
						activeFilters = (ArrayList<IFilter>) ois.readObject();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		            ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		           
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }
        public void savePreset(String name){
        	//Saves the preset file
        	FileOutputStream fos;
			try {
				fos = new FileOutputStream(System.getenv("USERPROFILE")+"/Platypus/Presets/"+name+".preset");
				ObjectOutputStream oos;
				try {
					 oos = new ObjectOutputStream(fos);
					 oos.writeObject(activeFilters);
			         oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	           
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//Saves the info file
			
        }
}