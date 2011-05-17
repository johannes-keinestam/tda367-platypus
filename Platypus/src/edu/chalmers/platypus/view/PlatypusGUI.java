package edu.chalmers.platypus.view;

import java.io.File;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.Locator;
import edu.chalmers.platypus.view.gui.FilterPanel;
import edu.chalmers.platypus.view.gui.PlatypusApp;
import javax.swing.ImageIcon;

public class PlatypusGUI {
	private static PlatypusGUI instance;
        private File path;

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
        public void loadPreset(Preset preset) {
            Locator.getCtrl().loadPreset(preset);
        }
        public void savePreset(String name) {
            Locator.getCtrl().savePreset(name);
        }
        public void importFilter(File filter) {
            Locator.getCtrl().importNewFilter(filter);
        }
        public void resetModel() {
            Locator.getCtrl().resetModel();
        }
        public void clearBatch() {
            Locator.getCtrl().clearImageBatch();
        }
        public void setPreview(BatchImage newPreview) {
            Locator.getCtrl().setNewPreview(newPreview);
        }
        public void abortSaveOperation() {
            Locator.getCtrl().abortSaveOperation();
        }
        public ImageIcon getPreviewOriginal(int width, int height) {
            return Locator.getCtrl().getPreviewOriginal(width, height);
        }
        public ImageIcon getPreviewFiltered(int width, int height) {
            return Locator.getCtrl().getPreviewFiltered(width, height);
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
    	for (Preset preset : Locator.getCtrl().getLoadedPresetList()) {
    		model.addElement(preset);
    	}
    	return model;
    }

    public void saveImages(String path, String ext) {
    	Locator.getCtrl().saveImages(path, ext);
        this.path = new File(path);
    }

    public File getLastSavePath() {
        return this.path;
    }

}
