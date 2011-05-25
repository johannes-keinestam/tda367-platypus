package edu.chalmers.platypus.view;

import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.Preset;

import edu.chalmers.platypus.util.CtrlLocator;
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
    	CtrlLocator.getFilterCtrl().addFilterToBatch(filter);
	}
	public void addImageToBatch(File f) {
    	CtrlLocator.getImageCtrl().addImageToBatch(f);
	}
	public void removeImageFromBatch(BatchImage img) {
    	CtrlLocator.getImageCtrl().removeImageFromBatch(img);
	}
	public void removeFilterFromBatch(IFilter filter) {
    	CtrlLocator.getFilterCtrl().removeFilterFromBatch(filter);
	}
        public void loadPreset(Preset preset) {
            CtrlLocator.getPresetCtrl().loadPreset(preset);
        }
        public void savePreset(String name) {
            CtrlLocator.getPresetCtrl().savePreset(name);
        }
        public void importFilter(File filter) {
            CtrlLocator.getFilterCtrl().importNewFilter(filter);
        }
        public void resetModel() {
            CtrlLocator.getMiscCtrl().resetModel();
        }
        public void clearBatch() {
            CtrlLocator.getImageCtrl().clearImageBatch();
        }
        public void setPreview(BatchImage newPreview) {
            CtrlLocator.getPreviewCtrl().setNewPreview(newPreview);
        }
        public void abortSaveOperation() {
            CtrlLocator.getImageCtrl().abortSaveOperation();
        }
        public ImageIcon getPreviewOriginal(int width, int height) {
            return CtrlLocator.getPreviewCtrl().getPreviewOriginal(width, height);
        }
        public ImageIcon getPreviewFiltered(int width, int height) {
            return CtrlLocator.getPreviewCtrl().getPreviewFiltered(width, height);
        }
    public ListModel getNewFilterList() {
        //Fetch list from ctrl, update with it
    	DefaultListModel model = new DefaultListModel();
    	for (IFilter filter : CtrlLocator.getFilterCtrl().getLoadedFilterList()) {
    		model.addElement(filter);
    	}
    	return model;
    }
    
    public ListModel getNewPresetList() {
        //Fetch list from ctrl, update with it
    	DefaultListModel model = new DefaultListModel();
    	for (Preset preset : CtrlLocator.getPresetCtrl().getLoadedPresetList()) {
    		model.addElement(preset);
    	}
    	return model;
    }

    public void saveImages(String path, String ext) {
    	CtrlLocator.getImageCtrl().saveImages(path, ext);
        this.path = new File(path);
    }

    public File getLastSavePath() {
        return this.path;
    }

}
