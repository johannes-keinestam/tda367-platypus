package edu.chalmers.platypus.view;

import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.view.gui.FilterPanel;
import edu.chalmers.platypus.view.gui.PlatypusApp;

public class PlatypusGUI {
	private static PlatypusGUI instance;

	private PlatypusGUI() {	
		PlatypusApp.showGUI(null);
	}
	
	public static PlatypusGUI getInstance() {
		if (instance == null) {
			instance = new PlatypusGUI();
		}
		return instance;
	}

	public void addFilterToBatch(IFilter filter) {
    	Locator.getCtrl().addFilterToBatch(filter);
	}
	public void addImageToBatch(File f) {
    	Locator.getCtrl().addImageToBatch(f);
	}
	public void removeImageFromBatch(BatchImage img) {
    	Locator.getCtrl().removeImageFromBatch(img);
	}
	public void removeFilterFromBatch(IFilter filter) {
    	Locator.getCtrl().removeFilterFromBatch(filter);
	}
    public ListModel getNewFilterList() {
        //Fetch list from ctrl, update with it
    	DefaultListModel model = new DefaultListModel();
    	for (IFilter filter : Locator.getCtrl().getLoadedFilterList()) {
    		model.addElement(filter);
    	}
    	return model;
    }
    
    public ListModel getNewPresetList() {
        //Fetch list from ctrl, update with it
    	DefaultListModel model = new DefaultListModel();
    	for (IFilter filter : Locator.getCtrl().getLoadedFilterList()) {
    		model.addElement(filter);
    	}
    	return model;
    }

    public void saveImages(String path) {
    	Locator.getCtrl().saveImages(path);
    }
}
