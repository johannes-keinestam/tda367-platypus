/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BatchThumbPanel.java
 *
 * Created on 2011-maj-02, 22:28:42
 */

package edu.chalmers.platypus.view.gui;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.PlatypusGUI;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDropEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.TransferHandler;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author skoldator
 */
public class BatchThumbPanel extends javax.swing.JPanel implements PropertyChangeListener {

    /** Creates new form BatchThumbPanel */
    public BatchThumbPanel() {
        initComponents();
        WrapLayout l = new WrapLayout();
        l.setAlignment(WrapLayout.LEFT);
        setLayout(l);

        enableDragDrop();

        addPanel(addImageButton);
        ComBus.subscribe(this);
    }

    private void addPanel(JPanel addToList) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.add(addToList);
        panel.setOpaque(true);
        add(addToList);
        revalidate();
    }

    public void setFirstAsPreview() {
        if (getComponent(1) instanceof ThumbnailImage) {
            ((ThumbnailImage)getComponent(1)).selectAsPreview();
        }
    }
    private void removePanel(BatchImage img) {
    	for (Component c : getComponents()) {
    		if (c instanceof ThumbnailImage) {
    			if (((ThumbnailImage)c).getBatchImage() == img) {
                            remove(c);
                            revalidate();
                            repaint();
                            if (((ThumbnailImage)c).isPreview()) {
                                setFirstAsPreview();
                            }
    			}
    		}
    	}
    }

    public void openFileChooser() {
        JFileChooser browser = new JFileChooser();
        browser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        browser.setFileFilter(filter);
        browser.setAcceptAllFileFilterUsed(false);
        browser.setMultiSelectionEnabled(true);
        int result = browser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            addImagesToBatch(browser.getSelectedFiles());
        }
    }

    public void enableDragDrop() {
        this.setDropTarget(dropTarget);
    }

    public void disableDragDrop() {
        this.setDropTarget(null);
    }

    private void addImagesToBatch(final Object[] array) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                addImageButton.setDisabled();
                disableDragDrop();
                for (Object o : array) {
                    if (o instanceof File) {
                        File current = (File)o;
                        if (filter.accept(current)) {
                            PlatypusGUI.getInstance().addImageToBatch(current);
                        }
                    }
                }
                addImageButton.setEnabled();
                enableDragDrop();
            }
        });
        t.start();
    }

    public void setPreview(ThumbnailImage img) {
    	for (Component c : getComponents()) {
    		if (c instanceof ThumbnailImage) {
    			ThumbnailImage thumb = (ThumbnailImage)c;
    			if (thumb != img) {
    				thumb.deselectAsPreview();
    			}
    		}
    	}
    	Locator.getCtrl().setNewPreview(img.getBatchImage());
        previewSet = true;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
	String change = evt.getPropertyName();
	if (change.equals(StateChanges.NEW_IMAGE_IN_BATCH.toString())) {
            BatchImage addedImage = (BatchImage)evt.getNewValue();
            ThumbnailImage thumb = new ThumbnailImage(addedImage, this);
            if (previewSet == false) {
                thumb.selectAsPreview();
            }
            addPanel(thumb);
	} else if (change.equals(StateChanges.IMAGE_REMOVED_FROM_BATCH.toString())) {
            BatchImage removedImage = (BatchImage)evt.getOldValue();
            removePanel(removedImage);
        } else if (change.equals(StateChanges.MODEL_RESET.toString())) {
            removeAll();
            addPanel(addImageButton);
            repaint();
            revalidate();
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 438, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 238, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    private final AddImagePanel addImageButton = new AddImagePanel(this);
    private boolean previewSet = false;
    private final FileFilter filter = new FileNameExtensionFilter("Supported pictures " +
                "(*.jpg, *.png, *.gif)",
                "jpeg","JPEG","JPG","jpg","png","PNG","gif","GIF");
    private final DropTarget dropTarget = new DropTarget() {
            @Override
            public synchronized void drop(DropTargetDropEvent dtde) {
                dtde.acceptDrop(DnDConstants.ACTION_COPY);
                Transferable t = dtde.getTransferable();
                List fileList;
                try {
                    fileList = (List) t.getTransferData(DataFlavor.javaFileListFlavor);
                } catch (Exception e) {
                    return;
                }
                addImagesToBatch(fileList.toArray());
            }
        };
}
