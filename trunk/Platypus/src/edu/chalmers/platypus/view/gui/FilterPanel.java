/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FilterPanel.java
 *
 * Created on 2011-apr-01, 19:50:03
 */

package edu.chalmers.platypus.view.gui;

import edu.chalmers.platypus.view.FilterViewPanel;
import javax.swing.border.TitledBorder;

import edu.chalmers.platypus.model.IFilter;

/**
 *
 * @author skoldator
 */
public class FilterPanel extends javax.swing.JPanel {

    /** Creates new form FilterPanel */
    public FilterPanel() {
        initComponents();
    }
    public FilterPanel(IFilter filter, FilterViewPanel parent) {
        this();
        this.parent = parent;
        this.filter = filter;
        
        if (filter != null) {
            ((TitledBorder)getBorder()).setTitle(filter.getName());
            filterScrollPane.setViewportView(filter.getPanel());
        }
    }

    public IFilter getFilter() {
    	return filter;
    }
    public void setAddButtonToNext() {
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getResourceMap(FilterPanel.class);
        nextButton.setText(resourceMap.getString("nextButton.alttext"));
    }
    public void setAddButtonToAdd() {
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getResourceMap(FilterPanel.class);
        nextButton.setText(resourceMap.getString("nextButton.text"));
    }
    public void setFilterNumber(int number, int totalNumber) {
            ((TitledBorder)getBorder()).setTitle(filter.getName() + " (" + number + "/" + totalNumber + ")");
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        activateFilterCheckBox = new javax.swing.JCheckBox();
        filterScrollPane = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        nextButton = new javax.swing.JButton();
        finishButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();
        removeFilterButton = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(FilterPanel.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("Form.border.title"))); // NOI18N
        setName("Form"); // NOI18N

        activateFilterCheckBox.setSelected(true);
        activateFilterCheckBox.setText(resourceMap.getString("activateFilterCheckBox.text")); // NOI18N
        activateFilterCheckBox.setName("activateFilterCheckBox"); // NOI18N

        filterScrollPane.setBorder(null);
        filterScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        filterScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        filterScrollPane.setName("filterScrollPane"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 481, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 129, Short.MAX_VALUE)
        );

        filterScrollPane.setViewportView(jPanel1);

        nextButton.setText(resourceMap.getString("nextButton.text")); // NOI18N
        nextButton.setName("nextButton"); // NOI18N

        finishButton.setText(resourceMap.getString("finishButton.text")); // NOI18N
        finishButton.setName("finishButton"); // NOI18N
        finishButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                finishButtonActionPerformed(evt);
            }
        });

        previousButton.setText(resourceMap.getString("previousButton.text")); // NOI18N
        previousButton.setName("previousButton"); // NOI18N

        removeFilterButton.setText(resourceMap.getString("removeFilterButton.text")); // NOI18N
        removeFilterButton.setName("removeFilterButton"); // NOI18N
        removeFilterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeFilterButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 481, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(activateFilterCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 323, Short.MAX_VALUE)
                        .addComponent(removeFilterButton))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(previousButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 226, Short.MAX_VALUE)
                        .addComponent(finishButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nextButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeFilterButton)
                    .addComponent(activateFilterCheckBox))
                .addGap(6, 6, 6)
                .addComponent(filterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nextButton)
                    .addComponent(finishButton)
                    .addComponent(previousButton))
                .addContainerGap())
        );

        activateFilterCheckBox.setVisible(false);
        filterScrollPane.getVerticalScrollBar().setUnitIncrement(10);
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (parent != null) {
            parent.showPreviousFilterPanel();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void finishButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_finishButtonActionPerformed
        if (parent != null) {
            parent.getMainView().showNextView();
        }
    }//GEN-LAST:event_finishButtonActionPerformed
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (parent != null) {
            parent.showNextFilterPanel();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void removeFilterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeFilterButtonActionPerformed
        parent.getMainView().getGUICtrl().removeFilterFromBatch(filter);
    }//GEN-LAST:event_removeFilterButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activateFilterCheckBox;
    private javax.swing.JScrollPane filterScrollPane;
    private javax.swing.JButton finishButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton nextButton;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton removeFilterButton;
    // End of variables declaration//GEN-END:variables

    private FilterViewPanel parent;
    private IFilter filter;
}
