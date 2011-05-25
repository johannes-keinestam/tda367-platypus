package edu.chalmers.platypus.util;

import edu.chalmers.platypus.model.PlatypusModel;

/**
 * Locator class for model, i.e. getters and setters. Used when classes wants a
 * Model class.
 */
public class ModelLocator {
    private static PlatypusModel model;

    public static PlatypusModel getModel() {
	return model;
    }

    public static void setModel(PlatypusModel setModel) {
	model = setModel;
    }
}
