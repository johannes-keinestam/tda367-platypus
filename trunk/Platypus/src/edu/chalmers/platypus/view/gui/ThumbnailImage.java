package edu.chalmers.platypus.view.gui;

import java.awt.event.ActionEvent;

import javax.swing.ImageIcon;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.view.BatchThumbPanel;
import edu.chalmers.platypus.view.PlatypusGUI;

/**
 * Panel which shows a thumbnail image. Allows the user to delete it or set it
 * as preview.
 */
public class ThumbnailImage extends javax.swing.JPanel {

    /** Constructor */
    public ThumbnailImage() {
	initComponents();
    }

    /** Constructor */
    public ThumbnailImage(BatchImage img, BatchThumbPanel parent) {
	initComponents();
	this.img = img;
	this.parent = parent;
	nameLabel.setText(img.getFileName());
	thumbLabel.setIcon(new ImageIcon(img.getThumbnail(120, 90)));
    }

    /** Show controls, i.e. file name overlay, delete button, preview checkbox */
    public void showControls() {
	deleteButton.setVisible(true);
	nameLabel.setVisible(true);
	overlayLabel.setVisible(true);
	previewCheckBox.setVisible(true);
    }

    /** Hide controls */
    public void hideControls() {
	deleteButton.setVisible(false);
	nameLabel.setVisible(false);
	overlayLabel.setVisible(false);
	previewCheckBox.setVisible(false);
    }

    /** Get BatchImage associated with this preview panel */
    public BatchImage getBatchImage() {
	return img;
    }

    /** Selects the image associated with this thumbnail panel as preview */
    public void selectAsPreview() {
	previewCheckBox.setSelected(true);
	parent.setPreview(this);
    }

    /** Deselects the image associated with this thumbnail panel as preview */
    public void deselectAsPreview() {
	previewCheckBox.setSelected(false);
    }

    /**
     * Returns true if the image associated with this thumbnail panel is
     * currently set as preview
     */
    public boolean isPreview() {
	return previewCheckBox.isSelected();
    }

    private void formMouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseEntered
	showControls();
    }// GEN-LAST:event_formMouseEntered

    private void formMouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_formMouseExited
	hideControls();
    }// GEN-LAST:event_formMouseExited

    private void jButton1MouseEntered(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseEntered
	showControls();
    }// GEN-LAST:event_jButton1MouseEntered

    private void jButton1MouseExited(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_jButton1MouseExited
	hideControls();
    }// GEN-LAST:event_jButton1MouseExited

    /**
     * Below: All autogenerated NetBeans code
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

	jPanel1 = new javax.swing.JPanel();
	deleteButton = new javax.swing.JButton();
	previewCheckBox = new javax.swing.JCheckBox();
	nameLabel = new javax.swing.JLabel();
	overlayLabel = new javax.swing.JLabel();
	thumbLabel = new javax.swing.JLabel();

	setName("Form"); // NOI18N
	addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
		formMouseEntered(evt);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
		formMouseExited(evt);
	    }
	});

	jPanel1.setName("jPanel1"); // NOI18N
	jPanel1.setLayout(null);

	org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
		.getInstance().getContext()
		.getResourceMap(ThumbnailImage.class);
	deleteButton.setIcon(resourceMap.getIcon("deleteButton.icon")); // NOI18N
	deleteButton.setText(resourceMap.getString("deleteButton.text")); // NOI18N
	deleteButton.setBorderPainted(false);
	deleteButton.setContentAreaFilled(false);
	deleteButton.setFocusPainted(false);
	deleteButton.setFocusable(false);
	deleteButton.setName("deleteButton"); // NOI18N
	deleteButton.setPressedIcon(resourceMap
		.getIcon("deleteButton.pressedIcon")); // NOI18N
	deleteButton.setRequestFocusEnabled(false);
	deleteButton.setRolloverIcon(resourceMap
		.getIcon("deleteButton.rolloverIcon")); // NOI18N
	deleteButton.setRolloverSelectedIcon(resourceMap
		.getIcon("deleteButton.rolloverSelectedIcon")); // NOI18N
	deleteButton.setVerifyInputWhenFocusTarget(false);
	jPanel1.add(deleteButton);
	deleteButton.setBounds(96, 0, 24, 24);
	deleteButton.setVisible(false);
	deleteButton.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
		jButton1MouseEntered(evt);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
		jButton1MouseExited(evt);
	    }
	});
	deleteButton.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		jButton1ActionPerformed(evt);
	    }
	});

	previewCheckBox.setText(resourceMap.getString("previewCheckBox.text")); // NOI18N
	previewCheckBox.setToolTipText(resourceMap
		.getString("previewCheckBox.toolTipText")); // NOI18N
	previewCheckBox.setName("previewCheckBox"); // NOI18N
	previewCheckBox.setOpaque(false);
	previewCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseClicked(java.awt.event.MouseEvent evt) {
		previewCheckBoxMouseClicked(evt);
	    }
	});
	jPanel1.add(previewCheckBox);
	previewCheckBox.setBounds(0, 0, 20, 21);
	previewCheckBox.setVisible(false);
	previewCheckBox.setOpaque(false);
	previewCheckBox.addMouseListener(new java.awt.event.MouseAdapter() {
	    public void mouseEntered(java.awt.event.MouseEvent evt) {
		jButton1MouseEntered(evt);
	    }

	    public void mouseExited(java.awt.event.MouseEvent evt) {
		jButton1MouseExited(evt);
	    }
	});

	nameLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	nameLabel.setText(resourceMap.getString("nameLabel.text")); // NOI18N
	nameLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
	nameLabel.setName("nameLabel"); // NOI18N
	jPanel1.add(nameLabel);
	nameLabel.setBounds(0, 70, 120, 20);
	nameLabel.setVisible(false);

	overlayLabel.setIcon(resourceMap.getIcon("overlayLabel.icon")); // NOI18N
	overlayLabel.setText(resourceMap.getString("overlayLabel.text")); // NOI18N
	overlayLabel.setName("overlayLabel"); // NOI18N
	jPanel1.add(overlayLabel);
	overlayLabel.setBounds(0, 0, 120, 90);
	overlayLabel.setVisible(false);

	thumbLabel.setBackground(resourceMap.getColor("thumbLabel.background")); // NOI18N
	thumbLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	thumbLabel.setIcon(resourceMap.getIcon("thumbLabel.icon")); // NOI18N
	thumbLabel.setText(resourceMap.getString("thumbLabel.text")); // NOI18N
	thumbLabel.setAlignmentX(0.5F);
	thumbLabel.setName("thumbLabel"); // NOI18N
	thumbLabel.setOpaque(true);
	jPanel1.add(thumbLabel);
	thumbLabel.setBounds(0, 0, 120, 90);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	this.setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120,
		javax.swing.GroupLayout.PREFERRED_SIZE));
	layout.setVerticalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90,
		javax.swing.GroupLayout.PREFERRED_SIZE));
    }// </editor-fold>//GEN-END:initComponents

    private void previewCheckBoxMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_previewCheckBoxMouseClicked
	if (previewCheckBox.isSelected()) {
	    selectAsPreview();
	} else {
	    parent.setFirstAsPreview();
	}
    }// GEN-LAST:event_previewCheckBoxMouseClicked

    private void jButton1ActionPerformed(ActionEvent evt) {
	PlatypusGUI.getInstance().removeImageFromBatch(img);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel overlayLabel;
    private javax.swing.JCheckBox previewCheckBox;
    private javax.swing.JLabel thumbLabel;
    // End of variables declaration//GEN-END:variables

    private BatchImage img;
    private BatchThumbPanel parent;
}
