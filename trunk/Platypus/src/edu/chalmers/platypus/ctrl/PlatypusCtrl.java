package edu.chalmers.platypus.ctrl;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;

import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.PlatypusModel;

public class PlatypusCtrl {
	private static PlatypusCtrl instance;
	
	private PlatypusCtrl() {}
	
	public static PlatypusCtrl getInstance() {
		if (instance == null) {
			instance = new PlatypusCtrl();
		}
		return instance;
	}
	
	public void addImageToBatch(File file) {
		Locator.getModel().addImageToBatch(file);
	}
	
	public void subscribeAsModelListener(PropertyChangeListener pcl) {
		Locator.getModel().addListener(pcl);
	}
}
