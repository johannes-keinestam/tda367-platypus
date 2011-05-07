/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ImageBrowserPanel.java
 *
 * Created on 2011-mar-24, 20:30:14
 */

package edu.chalmers.platypus.view.gui;

import java.awt.Color;
import java.awt.Component;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;

import org.jdesktop.application.ResourceMap;

import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.Locator;
import edu.chalmers.platypus.util.StateChanges;

/**
 *
 * @author skoldator
 */
public class BrowseViewPanel extends javax.swing.JPanel implements PropertyChangeListener {

    /** Creates new form ImageBrowserPanel */
    public BrowseViewPanel() {
        initComponents();
        ComBus.subscribe(this);
    }

    public BrowseViewPanel(PlatypusView parent) {
        this();
        this.parent = parent;
    }

    public void setButtonNext() {
        ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getResourceMap(BrowseViewPanel.class);
        nextViewButton.setText(resourceMap.getString("nextViewButton.alttext"));
    }
    
    public void setButtonAdd() {
        ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getResourceMap(BrowseViewPanel.class);
        nextViewButton.setText(resourceMap.getString("nextViewButton.text"));
    }
    
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String change = evt.getPropertyName();
		if (change.equals(StateChanges.MODEL_RESET.toString())) {
			setButtonAdd();
		}
	}
    
    
    /** 
     *  Below: autogenerated NetBeans code
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nextViewButton = new javax.swing.JButton();
        batchThumbScrollPane = new javax.swing.JScrollPane();
        batchThumbPanel = new edu.chalmers.platypus.view.gui.BatchThumbPanel();
        clearBatchButton = new javax.swing.JButton();
        loadPresetButton = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(BrowseViewPanel.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("Form.border.title"))); // NOI18N
        setName("Form"); // NOI18N

        nextViewButton.setText(resourceMap.getString("nextViewButton.text")); // NOI18N
        nextViewButton.setName("nextViewButton"); // NOI18N
        nextViewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextViewButtonActionPerformed(evt);
            }
        });

        batchThumbScrollPane.setBackground(resourceMap.getColor("batchThumbScrollPane.background")); // NOI18N
        batchThumbScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        batchThumbScrollPane.setName("batchThumbScrollPane"); // NOI18N

        batchThumbPanel.setName("batchThumbPanel"); // NOI18N
        batchThumbScrollPane.setViewportView(batchThumbPanel);

        clearBatchButton.setText(resourceMap.getString("clearBatchButton.text")); // NOI18N
        clearBatchButton.setName("clearBatchButton"); // NOI18N
        clearBatchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBatchButtonActionPerformed(evt);
            }
        });

        loadPresetButton.setText(resourceMap.getString("loadPresetButton.text")); // NOI18N
        loadPresetButton.setName("loadPresetButton"); // NOI18N
        loadPresetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadPresetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(batchThumbScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(clearBatchButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                        .addComponent(loadPresetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextViewButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(batchThumbScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextViewButton)
                    .addComponent(loadPresetButton)
                    .addComponent(clearBatchButton))
                .addContainerGap())
        );

        batchThumbScrollPane.getViewport().setBackground(Color.WHITE);
        batchThumbScrollPane.getVerticalScrollBar().setUnitIncrement(20);
    }// </editor-fold>//GEN-END:initComponents

    private void nextViewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextViewButtonActionPerformed
    	if (parent != null) {
        	if (parent.numberOfFilterPanels() == 0) {
            	parent.showAddFilterDialog();
        	}
        	if (parent.numberOfFilterPanels() > 0) {
                parent.showNextView();
        	}
        }
    }//GEN-LAST:event_nextViewButtonActionPerformed

    private void loadPresetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadPresetButtonActionPerformed
        if (parent != null) {
            parent.showLoadPresetDialog();
        }
    }//GEN-LAST:event_loadPresetButtonActionPerformed

    private void clearBatchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBatchButtonActionPerformed
        Locator.getCtrl().resetModel();
    }//GEN-LAST:event_clearBatchButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.chalmers.platypus.view.gui.BatchThumbPanel batchThumbPanel;
    private javax.swing.JScrollPane batchThumbScrollPane;
    private javax.swing.JButton clearBatchButton;
    private javax.swing.JButton loadPresetButton;
    private javax.swing.JButton nextViewButton;
    // End of variables declaration//GEN-END:variables

    private PlatypusView parent;
}
