package edu.chalmers.platypus.view.gui;

import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.view.PlatypusGUI;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;

import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.border.TitledBorder;

/**
 * Panel, typically placed in a JDialog. Shows user a list of available presets
 * and lets them load it through the GUI.
 */
public class LoadPresetPanel extends javax.swing.JPanel {

    /** Constructor
     *
     * @param container dialog which this panel will be added to
     * @param parent main view
     */
    public LoadPresetPanel(JDialog container, PlatypusView parent) {
        initComponents();

        containerDialog = container;
        parentView = parent;

        //Initial population of list
        updateList();
    }

    /** Closes dialog containing this panel */
    private void closeContainerDialog() {
        containerDialog.setVisible(false);
    }

    /** Updates list of available presets */
    private void updateList() {
        //Fetch list from ctrl, update with it
    	loadedPresetList.setModel(PlatypusGUI.getInstance().getNewPresetList());
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        presetListScrollPane = new javax.swing.JScrollPane();
        loadedPresetList = new javax.swing.JList();
        presetInfoPanel = new javax.swing.JPanel();
        contentInfoLabel = new javax.swing.JLabel();
        creationInfoLabel = new javax.swing.JLabel();
        contentLabel = new javax.swing.JLabel();
        creationLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance().getContext().getResourceMap(LoadPresetPanel.class);
        setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("Form.border.title"))); // NOI18N
        setName("Form"); // NOI18N

        presetListScrollPane.setName("presetListScrollPane"); // NOI18N

        loadedPresetList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Förinställning 1", "Förinställning 2" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        loadedPresetList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        loadedPresetList.setFixedCellHeight(20);
        loadedPresetList.setName("loadedPresetList"); // NOI18N
        loadedPresetList.setSelectionBackground(resourceMap.getColor("loadedPresetList.selectionBackground")); // NOI18N
        loadedPresetList.setSelectionForeground(resourceMap.getColor("loadedPresetList.selectionForeground")); // NOI18N
        loadedPresetList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                loadedPresetListValueChanged(evt);
            }
        });
        presetListScrollPane.setViewportView(loadedPresetList);
        loadedPresetList.setCellRenderer(new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {

                DefaultListCellRenderer component = (DefaultListCellRenderer)super.
                getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

                if (value instanceof Preset) {
                    String text = ((Preset)value).getName();
                    component.setText(text);
                }
                return component;
            }
        });

        presetInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("presetInfoPanel.border.title"))); // NOI18N
        presetInfoPanel.setName("presetInfoPanel"); // NOI18N

        contentInfoLabel.setText(resourceMap.getString("contentInfoLabel.text")); // NOI18N
        contentInfoLabel.setName("contentInfoLabel"); // NOI18N

        creationInfoLabel.setText(resourceMap.getString("creationInfoLabel.text")); // NOI18N
        creationInfoLabel.setName("creationInfoLabel"); // NOI18N

        contentLabel.setText(resourceMap.getString("contentLabel.text")); // NOI18N
        contentLabel.setName("contentLabel"); // NOI18N

        creationLabel.setText(resourceMap.getString("creationLabel.text")); // NOI18N
        creationLabel.setName("creationLabel"); // NOI18N

        javax.swing.GroupLayout presetInfoPanelLayout = new javax.swing.GroupLayout(presetInfoPanel);
        presetInfoPanel.setLayout(presetInfoPanelLayout);
        presetInfoPanelLayout.setHorizontalGroup(
            presetInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, presetInfoPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(presetInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(contentLabel)
                    .addComponent(creationLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(creationInfoLabel)
                    .addComponent(contentInfoLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap())
        );
        presetInfoPanelLayout.setVerticalGroup(
            presetInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presetInfoPanelLayout.createSequentialGroup()
                .addGroup(presetInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(contentInfoLabel)
                    .addComponent(contentLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(presetInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(creationInfoLabel)
                    .addComponent(creationLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        okButton.setText(resourceMap.getString("okButton.text")); // NOI18N
        okButton.setName("okButton"); // NOI18N
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        cancelButton.setText(resourceMap.getString("cancelButton.text")); // NOI18N
        cancelButton.setName("cancelButton"); // NOI18N
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(presetListScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 350, Short.MAX_VALUE)
                    .addComponent(presetInfoPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cancelButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 238, Short.MAX_VALUE)
                        .addComponent(okButton)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(presetListScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(presetInfoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    /** */
    private void loadedPresetListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_loadedPresetListValueChanged
    	Object selection = loadedPresetList.getSelectedValue();
    	if (selection instanceof Preset) { //Should always be true
            Preset preset = (Preset)selection;
            String filters = "";
            for (String filter : preset.getFilters()) {
				filters = filters+filter+", ";
			}
            contentInfoLabel.setText(filters);
            creationInfoLabel.setText(preset.getDate().toLocaleString());
            ((TitledBorder)presetInfoPanel.getBorder()).setTitle(preset.getName());
            presetInfoPanel.repaint();
    	}
    	
    }//GEN-LAST:event_loadedPresetListValueChanged

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
    	closeContainerDialog();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
    	Object selection = loadedPresetList.getSelectedValue();
    	if (selection instanceof Preset) { //Should always be true
        	Preset preset = (Preset)selection;
        	PlatypusGUI.getInstance().loadPreset(preset);
    	}
        closeContainerDialog();
    }//GEN-LAST:event_okButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel contentInfoLabel;
    private javax.swing.JLabel contentLabel;
    private javax.swing.JLabel creationInfoLabel;
    private javax.swing.JLabel creationLabel;
    private javax.swing.JList loadedPresetList;
    private javax.swing.JButton okButton;
    private javax.swing.JPanel presetInfoPanel;
    private javax.swing.JScrollPane presetListScrollPane;
    // End of variables declaration//GEN-END:variables

    private ArrayList<String> presets;
    private final JDialog containerDialog;
    private final PlatypusView parentView;
}
