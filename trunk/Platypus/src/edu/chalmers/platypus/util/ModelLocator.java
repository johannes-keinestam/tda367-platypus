package edu.chalmers.platypus.util;

import edu.chalmers.platypus.model.PlatypusModel;

public class ModelLocator {
	private static PlatypusModel model;
	
	public static PlatypusModel getModel() {
		return model;
	}
	
	public static void setModel(PlatypusModel setModel) {
		model = setModel;
	}
}
