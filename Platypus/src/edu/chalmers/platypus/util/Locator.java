package edu.chalmers.platypus.util;

import edu.chalmers.platypus.ctrl.PlatypusCtrl;
import edu.chalmers.platypus.model.PlatypusModel;

/**
 * Locator class, for getting a reference to the model and/or controller easier.
 * These are set when the controller and model are created in the Main class.
 */
public class Locator {
	private static PlatypusModel model;
	private static PlatypusCtrl ctrl;
	
	public static PlatypusModel getModel() {
		return model;
	}

	public static PlatypusCtrl getCtrl() {
		return ctrl;
	}

	public static void setModel(PlatypusModel setModel) {
		model = setModel;
	}
        
	public static void setCtrl(PlatypusCtrl setCtrl) {
		ctrl = setCtrl;
	}

}
