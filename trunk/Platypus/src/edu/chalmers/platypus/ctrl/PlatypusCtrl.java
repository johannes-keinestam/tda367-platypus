package edu.chalmers.platypus.ctrl;

import java.beans.PropertyChangeListener;
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
	
	public void addImageToBatch(URL pathToImage) {
		Locator.getModel().addImageToBatch(pathToImage);
	}
	
	public void subscribeAsModelListener(PropertyChangeListener pcl) {
		Locator.getModel().addListener(pcl);
	}
}
