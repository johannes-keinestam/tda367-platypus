
package edu.chalmers.platypus.model;

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
}
