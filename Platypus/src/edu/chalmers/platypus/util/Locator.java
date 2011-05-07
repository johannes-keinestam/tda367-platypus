package edu.chalmers.platypus.util;

import edu.chalmers.platypus.ctrl.PlatypusCtrl;
import edu.chalmers.platypus.model.PlatypusModel;

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
