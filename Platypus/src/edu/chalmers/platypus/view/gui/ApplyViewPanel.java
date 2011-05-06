/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ApplyViewPanel.java
 *
 * Created on 2011-apr-01, 20:02:46
 */

package edu.chalmers.platypus.view.gui;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.util.StateChanges;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author skoldator
 */
public class ApplyViewPanel extends javax.swing.JPanel implements PropertyChangeListener {

    /** Creates new form ApplyViewPanel */
    public ApplyViewPanel() {
        initComponents();
        ComBus.subscribe(this);
    }

    public ApplyViewPanel(PlatypusView parent) {
        this();
        this.parent = parent;
    }

    public void setFileName(String name) {
        savingFileLabel.setText(this.savingFileText + " " + name);
    }

    public void setFilterName(String name) {
        applyingFilterLabel.setText(this.applyFilterText + " " + name);
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        loaderPanel = new javax.swing.JLabel();
        savingFileLabel = new javax.swing.JLabel();
        applyingFilterLabel = new javax.swing.JLabel();
        abortOperationButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        loaderPanel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(ApplyViewPanel.class);
        loaderPanel.setIcon(resourceMap.getIcon("loaderPanel.icon")); // NOI18N
        loaderPanel.setText(resourceMap.getString("loaderPanel.text")); // NOI18N
        loaderPanel.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        loaderPanel.setName("loaderPanel"); // NOI18N

        this.savingFileText = resourceMap.getString("savingFileLabel.text");
        savingFileLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        savingFileLabel.setText(savingFileText);
        savingFileLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        savingFileLabel.setName("savingFileLabel"); // NOI18N

        applyFilterText = resourceMap.getString("applyingFilterLabel.text");
        applyingFilterLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        applyingFilterLabel.setText(applyFilterText);
        applyingFilterLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        applyingFilterLabel.setName("applyingFilterLabel"); // NOI18N
        applyingFilterLabel.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        abortOperationButton.setText(resourceMap.getString("abortOperationButton.text")); // NOI18N
        abortOperationButton.setName("abortOperationButton"); // NOI18N
        abortOperationButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abortOperationButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(savingFileLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
                    .addComponent(loaderPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(applyingFilterLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(160, Short.MAX_VALUE)
                .addComponent(abortOperationButton)
                .addContainerGap(159, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(loaderPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(savingFileLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(applyingFilterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(abortOperationButton)
                .addContainerGap(52, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void abortOperationButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abortOperationButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_abortOperationButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton abortOperationButton;
    private javax.swing.JLabel applyingFilterLabel;
    private javax.swing.JLabel loaderPanel;
    private javax.swing.JLabel savingFileLabel;
    // End of variables declaration//GEN-END:variables

    private PlatypusView parent;
    private String applyFilterText;
    private String savingFileText;


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
       	String change = evt.getPropertyName();
	if (change.equals(StateChanges.PROCESSING_IMAGE.toString())) {
            BatchImage b = (BatchImage) evt.getNewValue();
            setFileName(b.getFileName());
	} else if (change.equals(StateChanges.APPLYING_FILTER.toString())) {
            IFilter f = (IFilter) evt.getNewValue();
            setFilterName(f.getName());
        } else if (change.equals(StateChanges.SAVE_OPERATION_FINISHED.toString())) {
            parent.showNextView();
        }
    }
}
