package edu.chalmers.platypus.ctrl;

import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.ArrayList;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.util.interfaces.IImageCtrl;

public class ImageCtrl implements IImageCtrl {

    private static ImageCtrl instance;

    /**
     * Constructor
     */
    private ImageCtrl() {
    }

    /**
     * Returns the singleton instance of this Class, if none exist the
     * constructor is run and the instance is set.
     * 
     * @return the singleton instance of the ImageCtrl.
     */
    public static ImageCtrl getInstance() {
	if (instance == null) {
	    instance = new ImageCtrl();
	}
	return instance;
    }

    /**
     * Adds the specified image to the imagebatch. Also informs all Listeners on
     * the ComBus of the NEW_IMAGE_IN_BATCH event.
     * 
     * @param File
     *            representing the image to be added.
     */
    public void addImageToBatch(File file) {
	BatchImage newImage = new BatchImage(file);
	ModelLocator.getModel().getImageBatch().add(newImage);

	PropertyChangeEvent pce = new PropertyChangeEvent(this,
		StateChanges.NEW_IMAGE_IN_BATCH.toString(), null, newImage);
	ComBus.notifyListeners(pce);
    }

    /**
     * If the ImageBatch contains the specified image it is removed and all
     * listeners on the ComBus is notified of the IMAGE_REMOVED_FROM_BATCH
     * event.
     * 
     * @param the
     *            BatchImage to be removed from the batch.
     */
    public void removeImageFromBatch(BatchImage img) {
	ArrayList<BatchImage> list = ModelLocator.getModel().getImageBatch();
	for (int i = 0; i < list.size(); i++) {
	    if (list.get(i) == img) {
		list.remove(i);
		PropertyChangeEvent pce = new PropertyChangeEvent(this,
			StateChanges.IMAGE_REMOVED_FROM_BATCH.toString(), img,
			null);
		ComBus.notifyListeners(pce);
		break;
	    }
	}
	if (list.isEmpty()) {
	    PropertyChangeEvent pce = new PropertyChangeEvent(this,
		    StateChanges.IMAGE_BATCH_EMPTY.toString(), img, null);
	    ComBus.notifyListeners(pce);
	}
    }

    /**
     * Clears the ImageBatch of all content, notifies listeners on the ComBus of
     * the IMAGE_BATCH_CLEARED event.
     */
    public void clearImageBatch() {
	ModelLocator.getModel().getImageBatch().clear();
	PropertyChangeEvent pce = new PropertyChangeEvent(this,
		StateChanges.IMAGE_BATCH_CLEARED.toString(), null, null);
	ComBus.notifyListeners(pce);
    }

    /**
     * Runs the saveImages operation by starting the runnable class RunBatch,
     */
    public void saveImages(String path, String ext) {
	RunBatch.start(path, ext);
    }

    /**
     * Sends the abort operation to the RunBatch class,
     */
    public void abortSaveOperation() {
	RunBatch.stop();
    }
}
