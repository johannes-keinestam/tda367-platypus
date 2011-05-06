/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FinishedViewPanel.java
 *
 * Created on 2011-apr-01, 19:59:21
 */

package edu.chalmers.platypus.view.gui;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.PlatypusGUI;
import java.awt.Desktop;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author skoldator
 */
public class FinishedViewPanel extends javax.swing.JPanel implements PropertyChangeListener {

    /** Creates new form FinishedViewPanel */
    public FinishedViewPanel() {
        initComponents();

        ComBus.subscribe(this);
    }

    public FinishedViewPanel(PlatypusView parent) {
        this();
        this.parent = parent;
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        operationFinishedLabel = new javax.swing.JLabel();
        openPathButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FinishedViewPanel.class);
        operationFinishedLabel.setFont(resourceMap.getFont("operationFinishedLabel.font")); // NOI18N
        operationFinishedLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        operationFinishedLabel.setText(resourceMap.getString("operationFinishedLabel.text")); // NOI18N
        operationFinishedLabel.setName("operationFinishedLabel"); // NOI18N

        openPathButton.setText(resourceMap.getString("openPathButton.text")); // NOI18N
        openPathButton.setName("openPathButton"); // NOI18N
        openPathButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openPathButtonActionPerformed(evt);
            }
        });

        restartButton.setText(resourceMap.getString("restartButton.text")); // NOI18N
        restartButton.setName("restartButton"); // NOI18N
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });

        exitButton.setText(resourceMap.getString("exitButton.text")); // NOI18N
        exitButton.setName("exitButton"); // NOI18N
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(operationFinishedLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(openPathButton, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(restartButton, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitButton, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(operationFinishedLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(openPathButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        Locator.getCtrl().resetModel();
        parent.showNextView();
    }//GEN-LAST:event_restartButtonActionPerformed

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void openPathButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openPathButtonActionPerformed
        File savePath = PlatypusGUI.getInstance().getLastSavePath();
        if (savePath.isDirectory()) {
            try {
                Desktop.getDesktop().open(savePath);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_openPathButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton openPathButton;
    private javax.swing.JLabel operationFinishedLabel;
    private javax.swing.JButton restartButton;
    // End of variables declaration//GEN-END:variables

    private PlatypusView parent;

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String change = evt.getPropertyName();
        if (change.equals(StateChanges.SAVE_OPERATION_FINISHED.toString())) {
            org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FinishedViewPanel.class);
            operationFinishedLabel.setText(resourceMap.getString("operationFinishedLabel.text"));
        } else if (change.equals(StateChanges.SAVE_OPERATION_ABORTED.toString())) {
            org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FinishedViewPanel.class);
            operationFinishedLabel.setText(resourceMap.getString("operationFinishedLabel.alttext"));
        }
    }

}
