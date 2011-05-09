/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SavePanel.java
 *
 * Created on 2011-apr-01, 17:55:38
 */

package edu.chalmers.platypus.view.gui;

import edu.chalmers.platypus.util.Locator;
import java.awt.event.ItemEvent;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author skoldator
 */
public class SaveViewPanel extends javax.swing.JPanel {

    /** Creates new form SavePanel */
	public SaveViewPanel() {
		initComponents();

		pathTextField.setText(System.getenv("USERPROFILE") + File.separator
				+ "PlatyPix" + File.separator + "Pictures");
	}

    public SaveViewPanel(PlatypusView parent) {
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

        pictureSavePanel = new javax.swing.JPanel();
        pathDescLabel = new javax.swing.JLabel();
        formatDescLabel = new javax.swing.JLabel();
        pathTextField = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        formatComboBox = new javax.swing.JComboBox();
        presetSavePanel = new javax.swing.JPanel();
        savePresetCheckBox = new javax.swing.JCheckBox();
        presetNameDescLabel = new javax.swing.JLabel();
        presetNameTextField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        previousButton = new javax.swing.JButton();

        setName("Form"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(SaveViewPanel.class);
        pictureSavePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("pictureSavePanel.border.title"))); // NOI18N
        pictureSavePanel.setName("pictureSavePanel"); // NOI18N

        pathDescLabel.setText(resourceMap.getString("pathDescLabel.text")); // NOI18N
        pathDescLabel.setName("pathDescLabel"); // NOI18N

        formatDescLabel.setText(resourceMap.getString("formatDescLabel.text")); // NOI18N
        formatDescLabel.setName("formatDescLabel"); // NOI18N

        pathTextField.setText(resourceMap.getString("pathTextField.text")); // NOI18N
        pathTextField.setName("pathTextField"); // NOI18N

        browseButton.setText(resourceMap.getString("browseButton.text")); // NOI18N
        browseButton.setName("browseButton"); // NOI18N
        browseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browseButtonActionPerformed(evt);
            }
        });

        formatComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { ".JPG", ".PNG" , ".GIF" }));
        formatComboBox.setName("formatComboBox"); // NOI18N

        javax.swing.GroupLayout pictureSavePanelLayout = new javax.swing.GroupLayout(pictureSavePanel);
        pictureSavePanel.setLayout(pictureSavePanelLayout);
        pictureSavePanelLayout.setHorizontalGroup(
            pictureSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pictureSavePanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(pictureSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(formatDescLabel)
                    .addComponent(pathDescLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pictureSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pictureSavePanelLayout.createSequentialGroup()
                        .addComponent(pathTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(browseButton))
                    .addComponent(formatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        pictureSavePanelLayout.setVerticalGroup(
            pictureSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pictureSavePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pictureSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pathDescLabel)
                    .addComponent(pathTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(browseButton))
                .addGap(18, 18, 18)
                .addGroup(pictureSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(formatDescLabel)
                    .addComponent(formatComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pathTextField.setEditable(false);

        presetSavePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("presetSavePanel.border.title"))); // NOI18N
        presetSavePanel.setName("presetSavePanel"); // NOI18N

        savePresetCheckBox.setText(resourceMap.getString("savePresetCheckBox.text")); // NOI18N
        savePresetCheckBox.setName("savePresetCheckBox"); // NOI18N
        savePresetCheckBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                savePresetCheckBoxItemStateChanged(evt);
            }
        });

        presetNameDescLabel.setText(resourceMap.getString("presetNameDescLabel.text")); // NOI18N
        presetNameDescLabel.setName("presetNameDescLabel"); // NOI18N

        presetNameTextField.setText(resourceMap.getString("presetNameTextField.text")); // NOI18N
        presetNameTextField.setName("presetNameTextField"); // NOI18N

        javax.swing.GroupLayout presetSavePanelLayout = new javax.swing.GroupLayout(presetSavePanel);
        presetSavePanel.setLayout(presetSavePanelLayout);
        presetSavePanelLayout.setHorizontalGroup(
            presetSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetSavePanelLayout.createSequentialGroup()
                .addGroup(presetSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(presetSavePanelLayout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(savePresetCheckBox))
                    .addGroup(presetSavePanelLayout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(presetNameDescLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presetNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        presetSavePanelLayout.setVerticalGroup(
            presetSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetSavePanelLayout.createSequentialGroup()
                .addComponent(savePresetCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(presetSavePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(presetNameDescLabel)
                    .addComponent(presetNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        presetNameTextField.setEnabled(false);

        saveButton.setText(resourceMap.getString("saveButton.text")); // NOI18N
        saveButton.setName("saveButton"); // NOI18N
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        previousButton.setText(resourceMap.getString("previousButton.text")); // NOI18N
        previousButton.setName("previousButton"); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pictureSavePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(presetSavePanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(previousButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 279, Short.MAX_VALUE)
                        .addComponent(saveButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pictureSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(presetSavePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(previousButton))
                .addContainerGap())
        );

        previousButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
    }// </editor-fold>//GEN-END:initComponents

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {
    	parent.getGUICtrl().saveImages(pathTextField.getText(), formatComboBox.getSelectedItem().toString().toLowerCase().substring(1));
        if (savePresetCheckBox.isSelected()) {
            Locator.getCtrl().savePreset(presetNameTextField.getText());
        }
        parent.showNextView();
    }                                        

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        parent.showPreviousView();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        JFileChooser browser = new JFileChooser();
        browser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        browser.setMultiSelectionEnabled(false);
        browser.showSaveDialog(this);
        pathTextField.setText(browser.getSelectedFile().getPath());
    }//GEN-LAST:event_browseButtonActionPerformed

    private void savePresetCheckBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_savePresetCheckBoxItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            presetNameTextField.setEnabled(true);
        } else {
            presetNameTextField.setText("");
            presetNameTextField.setEnabled(false);
        }
    }//GEN-LAST:event_savePresetCheckBoxItemStateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton browseButton;
    private javax.swing.JComboBox formatComboBox;
    private javax.swing.JLabel formatDescLabel;
    private javax.swing.JLabel pathDescLabel;
    private javax.swing.JTextField pathTextField;
    private javax.swing.JPanel pictureSavePanel;
    private javax.swing.JLabel presetNameDescLabel;
    private javax.swing.JTextField presetNameTextField;
    private javax.swing.JPanel presetSavePanel;
    private javax.swing.JButton previousButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JCheckBox savePresetCheckBox;
    // End of variables declaration//GEN-END:variables

    private PlatypusView parent;

}
