/*
 * PlatypusView.java
 */

package edu.chalmers.platypus.view.gui;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JDialog;
import javax.swing.JFrame;

import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;

import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.ErrorNotifier;

import edu.chalmers.platypus.view.PlatypusGUI;

/**
 * The application's main frame.
 */
public class PlatypusView extends FrameView implements PropertyChangeListener {

    public PlatypusView(SingleFrameApplication app) {
        super(app);

        initComponents();
        
        ComBus.subscribe(this);
        
        parent = PlatypusGUI.getInstance();
    }
    
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = PlatypusApp.getApplication().getMainFrame();
            aboutBox = new PlatypusAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        PlatypusApp.getApplication().show(aboutBox);
    }

    public void showAddFilterDialog() {
        ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getResourceMap(PlatypusView.class);
        addFilterDialog = new JDialog(this.getFrame(), resourceMap.getString("filterDialogTitle.text"), true);
        addFilterDialog.add(new AddFilterPanel(addFilterDialog, this));
        addFilterDialog.pack();
        addFilterDialog.setLocationRelativeTo(this.getComponent());
    	addFilterDialog.setVisible(true);
    }
    
    public void showLoadPresetDialog() {
        ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getResourceMap(PlatypusView.class);
        loadPresetDialog = new JDialog(this.getFrame(), resourceMap.getString("presetDialogTitle.text"), true);
        loadPresetDialog.add(new LoadPresetPanel(loadPresetDialog, this));
        loadPresetDialog.pack();
        loadPresetDialog.setLocationRelativeTo(this.getComponent());
    	loadPresetDialog.setVisible(true);
    }
    
    public int numberOfFilterPanels() {
    	return filterViewPanel1.numberOfActiveFilters();
    }
    
    public PlatypusGUI getGUICtrl() {
    	return parent;
    }
    
    public void setBrowseButtonNext() {
    	imageBrowserPanel1.setButtonNext();
    }
    
    public void setBrowseButtonAdd() {
    	imageBrowserPanel1.setButtonAdd();
    }
    
    public void showNextView() {
        ((CardLayout)jPanel1.getLayout()).next(jPanel1);
    }
    
    public void showPreviousView() {
        ((CardLayout)jPanel1.getLayout()).previous(jPanel1);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        startViewPanel1 = new edu.chalmers.platypus.view.gui.StartViewPanel(this);
        imageBrowserPanel1 = new edu.chalmers.platypus.view.gui.BrowseViewPanel(this);
        filterViewPanel1 = new edu.chalmers.platypus.view.FilterViewPanel(this);
        saveViewPanel1 = new edu.chalmers.platypus.view.gui.SaveViewPanel(this);
        applyViewPanel1 = new edu.chalmers.platypus.view.gui.ApplyViewPanel(this);
        finishedViewPanel1 = new edu.chalmers.platypus.view.gui.FinishedViewPanel(this);
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setLayout(new java.awt.CardLayout());

        startViewPanel1.setName("startViewPanel1"); // NOI18N
        jPanel1.add(startViewPanel1, "card7");

        imageBrowserPanel1.setName("imageBrowserPanel1"); // NOI18N
        jPanel1.add(imageBrowserPanel1, "browseViewPanel");

        filterViewPanel1.setName("filterViewPanel1"); // NOI18N
        jPanel1.add(filterViewPanel1, "card4");

        saveViewPanel1.setName("saveViewPanel1"); // NOI18N
        jPanel1.add(saveViewPanel1, "card6");

        applyViewPanel1.setName("applyViewPanel1"); // NOI18N
        jPanel1.add(applyViewPanel1, "card3");

        finishedViewPanel1.setName("finishedViewPanel1"); // NOI18N
        jPanel1.add(finishedViewPanel1, "card5");

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 332, Short.MAX_VALUE)
        );

        menuBar.setName("menuBar"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getResourceMap(PlatypusView.class);
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(edu.chalmers.platypus.view.gui.PlatypusApp.class).getContext().getActionMap(PlatypusView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        menuBar.setVisible(false);

        setComponent(mainPanel);
        setMenuBar(menuBar);
		errorNotifier = new ErrorNotifier(this);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.chalmers.platypus.view.gui.ApplyViewPanel applyViewPanel1;
    private edu.chalmers.platypus.view.FilterViewPanel filterViewPanel1;
    private edu.chalmers.platypus.view.gui.FinishedViewPanel finishedViewPanel1;
    private edu.chalmers.platypus.view.gui.BrowseViewPanel imageBrowserPanel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private edu.chalmers.platypus.view.gui.SaveViewPanel saveViewPanel1;
    private edu.chalmers.platypus.view.gui.StartViewPanel startViewPanel1;
    private edu.chalmers.platypus.view.ErrorNotifier errorNotifier;
    // End of variables declaration//GEN-END:variables


    private PlatypusGUI parent;
    private JDialog aboutBox;
    private JDialog addFilterDialog;
    private JDialog loadPresetDialog;
    public void propertyChange(PropertyChangeEvent evt) {
       	String change = evt.getPropertyName();
		if (change.equals(StateChanges.DUPLICATE_FILTER_SELECTED.toString())) {
			showAddFilterDialog();
		}
    }

}
