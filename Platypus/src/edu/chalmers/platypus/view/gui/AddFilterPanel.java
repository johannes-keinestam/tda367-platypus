package edu.chalmers.platypus.view.gui;

import java.awt.Component;
import java.io.File;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.view.PlatypusGUI;

/**
 * Panel, typically placed in a JDialog. Shows the user a list of loaded filters
 * and allows them to add them to the batch.
 * 
 * Also allows the user to import filters from their harddrive.
 * 
 */
public class AddFilterPanel extends javax.swing.JPanel {

    /** Constructor */
    public AddFilterPanel(JDialog container, PlatypusView parent) {
	initComponents();
	containerDialog = container;

	// Initial population of list
	updateList();
    }

    /** Closes the dialog in which it is placed */
    public void closeContainerDialog() {
	containerDialog.setVisible(false);
    }

    /** Updates list by requesting a new copy from the controller */
    public void updateList() {
	loadedFiltersList
		.setModel(PlatypusGUI.getInstance().getNewFilterList());
    }

    /** Opens file chooser dialog for choosing a filter to import */
    public void openFileChooser() {
	JFileChooser browser = new JFileChooser();

	// Only allow single files with the extension .jar
	browser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	browser.setFileFilter(filter);
	browser.setAcceptAllFileFilterUsed(false);
	browser.setMultiSelectionEnabled(false);

	// Gets chosen file and calls the controller to import if OK pressed
	int result = browser.showOpenDialog(this);
	if (result == JFileChooser.APPROVE_OPTION) {
	    importNewFilter(browser.getSelectedFile());
	}
    }

    /** Import given filter */
    private void importNewFilter(File filter) {
	PlatypusGUI.getInstance().importFilter(filter);
	updateList();
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

	filterListScrollPane = new javax.swing.JScrollPane();
	loadedFiltersList = new javax.swing.JList();
	filterAreaPanel = new javax.swing.JPanel();
	filterDescriptionScrollPane = new javax.swing.JScrollPane();
	filterDescriptionTextArea = new javax.swing.JTextArea();
	filterImageLabel = new javax.swing.JLabel();
	okButton = new javax.swing.JButton();
	cancelButton = new javax.swing.JButton();
	importFilterButton = new javax.swing.JButton();

	org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application
		.getInstance().getContext()
		.getResourceMap(AddFilterPanel.class);
	setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap
		.getString("Form.border.title"))); // NOI18N
	setName("Form"); // NOI18N

	filterListScrollPane.setName("filterListScrollPane"); // NOI18N

	loadedFiltersList.setModel(new javax.swing.AbstractListModel() {
	    String[] strings = { "Black/White", "Blur", "Scale", "Sharpen" };

	    public int getSize() {
		return strings.length;
	    }

	    public Object getElementAt(int i) {
		return strings[i];
	    }
	});
	loadedFiltersList
		.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
	loadedFiltersList.setFixedCellHeight(20);
	loadedFiltersList.setName("loadedFiltersList"); // NOI18N
	loadedFiltersList.setSelectionBackground(resourceMap
		.getColor("loadedFiltersList.selectionBackground")); // NOI18N
	loadedFiltersList.setSelectionForeground(resourceMap
		.getColor("loadedFiltersList.selectionForeground")); // NOI18N
	loadedFiltersList
		.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
		    public void valueChanged(
			    javax.swing.event.ListSelectionEvent evt) {
			loadedFiltersListValueChanged(evt);
		    }
		});
	filterListScrollPane.setViewportView(loadedFiltersList);
	loadedFiltersList.setCellRenderer(new DefaultListCellRenderer() {
	    @Override
	    public Component getListCellRendererComponent(JList list,
		    Object value, int index, boolean isSelected,
		    boolean cellHasFocus) {

		DefaultListCellRenderer component = (DefaultListCellRenderer) super
			.getListCellRendererComponent(list, value, index,
				isSelected, cellHasFocus);

		if (value instanceof IFilter) {
		    String text = ((IFilter) value).getName();
		    component.setText(text);
		}
		return component;
	    }
	});

	filterAreaPanel.setBorder(javax.swing.BorderFactory
		.createTitledBorder(resourceMap
			.getString("filterAreaPanel.border.title"))); // NOI18N
	filterAreaPanel.setName("filterAreaPanel"); // NOI18N

	filterDescriptionScrollPane.setBorder(null);
	filterDescriptionScrollPane.setName("filterDescriptionScrollPane"); // NOI18N

	filterDescriptionTextArea.setColumns(20);
	filterDescriptionTextArea.setEditable(false);
	filterDescriptionTextArea.setFont(resourceMap
		.getFont("filterDescriptionTextArea.font")); // NOI18N
	filterDescriptionTextArea.setLineWrap(true);
	filterDescriptionTextArea.setRows(5);
	filterDescriptionTextArea.setText(resourceMap
		.getString("filterDescriptionTextArea.text")); // NOI18N
	filterDescriptionTextArea.setBorder(null);
	filterDescriptionTextArea.setName("filterDescriptionTextArea"); // NOI18N
	filterDescriptionTextArea.setOpaque(false);
	filterDescriptionScrollPane.setViewportView(filterDescriptionTextArea);
	filterDescriptionScrollPane.setViewportBorder(null);
	filterDescriptionScrollPane.getViewport().setOpaque(false);

	filterImageLabel
		.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	filterImageLabel
		.setText(resourceMap.getString("filterImageLabel.text")); // NOI18N
	filterImageLabel.setName("filterImageLabel"); // NOI18N

	javax.swing.GroupLayout filterAreaPanelLayout = new javax.swing.GroupLayout(
		filterAreaPanel);
	filterAreaPanel.setLayout(filterAreaPanelLayout);
	filterAreaPanelLayout
		.setHorizontalGroup(filterAreaPanelLayout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				filterAreaPanelLayout
					.createSequentialGroup()
					.addContainerGap()
					.addComponent(
						filterDescriptionScrollPane,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						232, Short.MAX_VALUE)
					.addPreferredGap(
						javax.swing.LayoutStyle.ComponentPlacement.RELATED)
					.addComponent(
						filterImageLabel,
						javax.swing.GroupLayout.PREFERRED_SIZE,
						82,
						javax.swing.GroupLayout.PREFERRED_SIZE)
					.addContainerGap()));
	filterAreaPanelLayout
		.setVerticalGroup(filterAreaPanelLayout
			.createParallelGroup(
				javax.swing.GroupLayout.Alignment.LEADING)
			.addGroup(
				javax.swing.GroupLayout.Alignment.TRAILING,
				filterAreaPanelLayout
					.createSequentialGroup()
					.addGroup(
						filterAreaPanelLayout
							.createParallelGroup(
								javax.swing.GroupLayout.Alignment.TRAILING)
							.addComponent(
								filterImageLabel,
								javax.swing.GroupLayout.Alignment.LEADING,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								96,
								Short.MAX_VALUE)
							.addComponent(
								filterDescriptionScrollPane,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								96,
								Short.MAX_VALUE))
					.addContainerGap()));

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

	importFilterButton.setText(resourceMap
		.getString("importFilterButton.text")); // NOI18N
	importFilterButton.setName("importFilterButton"); // NOI18N
	importFilterButton
		.addActionListener(new java.awt.event.ActionListener() {
		    public void actionPerformed(java.awt.event.ActionEvent evt) {
			importFilterButtonActionPerformed(evt);
		    }
		});

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	this.setLayout(layout);
	layout.setHorizontalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(
							filterAreaPanel,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							Short.MAX_VALUE)
						.addComponent(
							filterListScrollPane,
							javax.swing.GroupLayout.DEFAULT_SIZE,
							350, Short.MAX_VALUE)
						.addGroup(
							javax.swing.GroupLayout.Alignment.TRAILING,
							layout.createSequentialGroup()
								.addComponent(
									importFilterButton)
								.addPreferredGap(
									javax.swing.LayoutStyle.ComponentPlacement.RELATED,
									141,
									Short.MAX_VALUE)
								.addComponent(
									cancelButton)
								.addPreferredGap(
									javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(
									okButton)))
				.addContainerGap()));
	layout.setVerticalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addContainerGap()
				.addComponent(filterListScrollPane,
					javax.swing.GroupLayout.DEFAULT_SIZE,
					190, Short.MAX_VALUE)
				.addPreferredGap(
					javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addComponent(filterAreaPanel,
					javax.swing.GroupLayout.PREFERRED_SIZE,
					javax.swing.GroupLayout.DEFAULT_SIZE,
					javax.swing.GroupLayout.PREFERRED_SIZE)
				.addPreferredGap(
					javax.swing.LayoutStyle.ComponentPlacement.RELATED)
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.BASELINE)
						.addComponent(okButton)
						.addComponent(cancelButton)
						.addComponent(
							importFilterButton))
				.addContainerGap()));

	filterAreaPanel
		.getAccessibleContext()
		.setAccessibleName(
			resourceMap
				.getString("filterAreaPanel.AccessibleContext.accessibleName")); // NOI18N
	okButton.setEnabled(false);
    }// </editor-fold>//GEN-END:initComponents

    /** Updates lower panel to show info about currently chosen filter */
    private void loadedFiltersListValueChanged(
	    javax.swing.event.ListSelectionEvent evt) {// GEN-FIRST:event_loadedFiltersListValueChanged
	// Gets selected item in the list
	Object selection = loadedFiltersList.getSelectedValue();

	if (selection instanceof IFilter) { // Should always be true
	    IFilter filter = (IFilter) selection;

	    if (filter.getDescriptiveImage() != null) {
		// Set image and show its panel only if there is one
		filterImageLabel.setIcon(filter.getDescriptiveImage());
		filterImageLabel.setVisible(true);
	    } else {
		filterImageLabel.setIcon(null);
		filterImageLabel.setVisible(false);
	    }

	    // Set description text and title of the panel
	    filterDescriptionTextArea.setText(filter.getDescription());
	    ((TitledBorder) filterAreaPanel.getBorder()).setTitle(filter
		    .getName());
	    filterAreaPanel.repaint();

	    // Enable OK button if filter is actually selected
	    okButton.setEnabled(true);
	} else if (selection == null) {
	    // Disable OK button if no filter is selected
	    okButton.setEnabled(false);
	}
    }// GEN-LAST:event_loadedFiltersListValueChanged

    /** Close dialog if cancel is pressed */
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cancelButtonActionPerformed
	closeContainerDialog();
    }// GEN-LAST:event_cancelButtonActionPerformed

    /** Load filter if OK pressed */
    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
	// Creates thread so loading cannot freeze GUI
	Thread t = new Thread(new Runnable() {
	    @Override
	    public void run() {
		Object selection = loadedFiltersList.getSelectedValue();
		if (selection instanceof IFilter) { // Should always be true
		    IFilter filter = (IFilter) selection;
		    PlatypusGUI.getInstance().addFilterToBatch(filter);
		}
	    }
	});

	// Starts Load Filter thread and closes dialog
	t.start();
	closeContainerDialog();
    }// GEN-LAST:event_okButtonActionPerformed

    /** Opens file chooser if Import Filter is pressed */
    private void importFilterButtonActionPerformed(
	    java.awt.event.ActionEvent evt) {// GEN-FIRST:event_importFilterButtonActionPerformed
	openFileChooser();
    }// GEN-LAST:event_importFilterButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JPanel filterAreaPanel;
    private javax.swing.JScrollPane filterDescriptionScrollPane;
    private javax.swing.JTextArea filterDescriptionTextArea;
    private javax.swing.JLabel filterImageLabel;
    private javax.swing.JScrollPane filterListScrollPane;
    private javax.swing.JButton importFilterButton;
    private javax.swing.JList loadedFiltersList;
    private javax.swing.JButton okButton;
    // End of variables declaration//GEN-END:variables

    /** File filter allowing only .JAR files */
    private final FileFilter filter = new FileNameExtensionFilter(
	    "Supported Files " + "(*.jar)", "jar", "JAR");
    /** Reference to the dialog which this panel is placed within */
    private final JDialog containerDialog;
}
