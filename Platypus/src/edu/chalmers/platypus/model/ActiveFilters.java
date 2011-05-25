
package edu.chalmers.platypus.model;

import java.util.ArrayList;


/**
 * Container for filters added to the batch of images.
 */
public class ActiveFilters {
        private static ActiveFilters activeFiltersObject;
        private ArrayList<IFilter> activeFilters = new ArrayList<IFilter>();

        /** Constructor */
        private ActiveFilters(){
        }

        /** ActiveFilters is a singleton */
        public static ActiveFilters getActiveFilters(){
                if (activeFiltersObject == null) {
                        activeFiltersObject = new ActiveFilters();
                }
                return activeFiltersObject;
        }

        /**
         * Getter for the list of filters currently added to the batch. Used by
         * Controller to manipulate its contents.
         *
         * @return the list of currently active filters
         */
        public ArrayList<IFilter> getList(){
                return activeFilters;
        }

}
