package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;

import javax.swing.ImageIcon;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.util.interfaces.IPreviewCtrl;

public class PreviewCtrl implements IPreviewCtrl{
	
	private static PreviewCtrl instance;

	/**
	 * Constructor
	 */
	private PreviewCtrl() {
	}
	
	/**
	 * Returns the singleton instance of this Class, if none exist
	 * the constructor is run and the instance is set.
	 * 
	 * @return the singleton instance of the PreviewCtrl.
	 */
	public static PreviewCtrl getInstance() {
		if (instance == null) {
			instance = new PreviewCtrl();
		}
		return instance;
	}

	
	
	/**
	 * Sets the specified image as the new preview and calls the
	 * previewChanged method
	 * 
	 * @param the new preview
	 */
	public void setNewPreview(BatchImage preview) {
		ModelLocator.getModel().setPreview(preview);
		previewChanged();
	}

	/**
	 * Returns a scaled but unfiltered version of the currently 
	 * selected preview.
	 * 
	 * @return an unfiltered version of the preview.
	 */
	public ImageIcon getPreviewOriginal(int width, int height) {
		if (ModelLocator.getModel().getPreview() == null) {
			return null;
		}
		return new ImageIcon(ModelLocator.getModel().getPreview()
				.getThumbnail(width, height));
	}

	/**
	 * Returns a scaled and filtered version of the currently
	 * selected preview.
	 * 
	 * @return a filtered version of the preview.
	 */
	public ImageIcon getPreviewFiltered(int width, int height) {
		if (ModelLocator.getModel().getPreview() == null) {
			return null;
		}
		BufferedImage original = ModelLocator.getModel().getPreview()
				.getThumbnail(width, height);

		ImageIcon preview = new ImageIcon(RunBatch.applyFilters(original));
		return preview;
	}

	/**
	 * Called when the GUI has changed any values on filters to provide an
	 * updated version of the preview or a new preview has been selected.
	 * 
	 */
	public void previewChanged() {
		PropertyChangeEvent pce = new PropertyChangeEvent(this,
				StateChanges.NEW_PREVIEW_IMAGE.toString(), null, null);
		ComBus.notifyListeners(pce);
		pce = new PropertyChangeEvent(this,
				StateChanges.PREVIEW_IMAGE_UPDATED.toString(), null, null);
		ComBus.notifyListeners(pce);
	}
}
