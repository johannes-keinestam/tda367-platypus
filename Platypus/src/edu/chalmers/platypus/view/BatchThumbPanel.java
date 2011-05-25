package edu.chalmers.platypus.view;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.gui.AddImagePanel;
import edu.chalmers.platypus.view.gui.ThumbnailImage;

/**
 * Panel used for representing the images in the backend in the GUI, showing
 * small thumbnail. Also used for letting users adding image from the GUI.
 * Displays them in rows dynamically using external class WrapLayout. Supports
 * drag and drop.
 * 
 * 
 */
public class BatchThumbPanel extends javax.swing.JPanel implements
	PropertyChangeListener {

    /** Constructor */
    public BatchThumbPanel() {
	initComponents();

	WrapLayout l = new WrapLayout();
	l.setAlignment(WrapLayout.LEFT);
	setLayout(l);

	enableDragDrop();

	addPanel(addImageButton);

	// Listens for changes from backend
	ComBus.subscribe(this);
    }

    /**
     * Adds specified panel at the end of the list of image thumbnails. Used for
     * adding ThumbnailImages to the list, as well as the initial "Browse for
     * images" button.
     * 
     * @param addToList
     *            panel to add
     */
    private void addPanel(JPanel addToList) {
	JPanel panel = new JPanel();
	panel.setLayout(new FlowLayout());
	panel.add(addToList);
	panel.setOpaque(true);
	add(addToList);
	revalidate();
    }

    /**
     * Sets the first image thumbnail in the list as chosen preview. Used when
     * the first image is added, as well as when an image set as preview is
     * deleted from batch.
     */
    public void setFirstAsPreview() {
	if (getComponents().length != 1
		&& getComponent(1) instanceof ThumbnailImage) {
	    ((ThumbnailImage) getComponent(1)).selectAsPreview();
	}
    }

    /**
     * Removes the thumbnail associated with the specified BatchImage. Used when
     * the backend reports that an image was removed from the batch.
     * 
     * @param img
     *            the BatchImage to remove
     */
    private void removePanel(BatchImage img) {
	for (Component c : getComponents()) {
	    if (c instanceof ThumbnailImage) {
		if (((ThumbnailImage) c).getBatchImage() == img) {
		    remove(c);
		    revalidate();
		    repaint();
		    if (((ThumbnailImage) c).isPreview()) {
			setFirstAsPreview();
		    }
		}
	    }
	}
    }

    /**
     * Opens a file chooser allowing the user to add multiple files with
     * supported file extensions (see instance variable "filter"). Used for
     * adding images to batch from GUI.
     */
    public void openFileChooser() {
	JFileChooser browser = new JFileChooser();

	// Only files using image extensions allowed
	browser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	browser.setFileFilter(filter);
	browser.setAcceptAllFileFilterUsed(false);
	browser.setMultiSelectionEnabled(true);

	// Calls controller to add chosen files to image batch
	int result = browser.showOpenDialog(this);
	if (result == JFileChooser.APPROVE_OPTION) {
	    addImagesToBatch(browser.getSelectedFiles());
	}
    }

    /**
     * Enables drag and drop support for panel. Will accept the same kind of
     * files as the file chooser (see instance variable "filter").
     */
    public void enableDragDrop() {
	this.setDropTarget(dropTarget);
    }

    /**
     * Disables drag and drop support for panel. Used for disabling adding of
     * images when the application is already loading images, to prevent
     * conflicts.
     */
    public void disableDragDrop() {
	this.setDropTarget(null);
    }

    /**
     * Adds list of image files to backend batch (non-GUI related) in a new
     * thread (to prevent freezing). Called when the user chooses files from the
     * file chooser or drag and drop.
     * 
     * @param array
     *            list of files to add
     */
    private void addImagesToBatch(final Object[] array) {
	Thread t = new Thread(new Runnable() {
	    @Override
	    public void run() {
		// Disables adding of new images during job
		addImageButton.setDisabled();
		disableDragDrop();
		for (Object o : array) {
		    if (o instanceof File) {
			File current = (File) o;
			if (filter.accept(current)) {
			    PlatypusGUI.getInstance().addImageToBatch(current);
			}
		    }
		}

		// Enables adding of new images when job is finished
		addImageButton.setEnabled();
		enableDragDrop();
	    }
	});

	t.start();
    }

    /**
     * Sets specified image as thumbnail, deselecting all others. Always called
     * when new preview is chosen, so that the user cannot select more than one
     * preview at a time.
     * 
     * @param img
     *            the ThumbnailImage to set as preview
     */
    public void setPreview(ThumbnailImage img) {
	for (Component c : getComponents()) {
	    if (c instanceof ThumbnailImage) {
		ThumbnailImage thumb = (ThumbnailImage) c;
		if (thumb != img) {
		    // Deselects all images but the one selected
		    thumb.deselectAsPreview();
		}
	    }
	}

	PlatypusGUI.getInstance().setPreview(img.getBatchImage());
	previewSet = true;
    }

    /**
     * Recieves info about operations from backend.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
	String change = evt.getPropertyName();

	if (change.equals(StateChanges.NEW_IMAGE_IN_BATCH.toString())) {
	    // New image in batch. Creates GUI representation of it
	    BatchImage addedImage = (BatchImage) evt.getNewValue();
	    ThumbnailImage thumb = new ThumbnailImage(addedImage, this);
	    if (previewSet == false) {
		thumb.selectAsPreview();
	    }
	    addPanel(thumb);
	} else if (change.equals(StateChanges.IMAGE_REMOVED_FROM_BATCH
		.toString())) {
	    // Image removed from batch. Removes from GUI.
	    BatchImage removedImage = (BatchImage) evt.getOldValue();
	    removePanel(removedImage);
	} else if (change.equals(StateChanges.MODEL_RESET.toString())) {
	    // Filters and images removed. Removes all images in GUI
	    previewSet = false;
	    removeAll();
	    addPanel(addImageButton);
	    repaint();
	    revalidate();
	} else if (change.equals(StateChanges.IMAGE_BATCH_CLEARED.toString())) {
	    // All images removed. Removes from GUI.
	    previewSet = false;
	    removeAll();
	    addPanel(addImageButton);
	    repaint();
	    revalidate();
	} else if (change.equals(StateChanges.IMAGE_BATCH_EMPTY.toString())) {
	    previewSet = false;
	}
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

	setBackground(new java.awt.Color(255, 255, 255));

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	this.setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 438,
		Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addGap(0, 238,
		Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    /** Reference to the clickable panel for adding images */
    private final AddImagePanel addImageButton = new AddImagePanel(this);
    /** Returns whether the preview is currently set */
    private boolean previewSet = false;
    /** File filter for supported image formats */
    private final FileFilter filter = new FileNameExtensionFilter(
	    "Supported pictures " + "(*.jpg, *.png, *.gif)", "jpeg", "JPEG",
	    "JPG", "jpg", "png", "PNG", "gif", "GIF");
    /** Drag and drop support for files */
    private final DropTarget dropTarget = new DropTarget() {
	@Override
	public synchronized void drop(DropTargetDropEvent dtde) {
	    dtde.acceptDrop(DnDConstants.ACTION_COPY);
	    Transferable t = dtde.getTransferable();
	    List fileList;
	    try {
		fileList = (List) t
			.getTransferData(DataFlavor.javaFileListFlavor);
	    } catch (Exception e) {
		return;
	    }
	    addImagesToBatch(fileList.toArray());
	}
    };
}
