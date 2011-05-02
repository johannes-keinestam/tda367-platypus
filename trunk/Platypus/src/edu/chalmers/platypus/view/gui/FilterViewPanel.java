/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * FilterViewPanel.java
 *
 * Created on 2011-apr-01, 18:24:29
 */

package edu.chalmers.platypus.view.gui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.PlatypusGUI;

/**
 *
 * @author skoldator
 */
public class FilterViewPanel extends javax.swing.JPanel implements PropertyChangeListener {

    /** Creates new form FilterViewPanel */
    public FilterViewPanel() {
        initComponents();
        ComBus.subscribe(this);
    }

    public FilterViewPanel(PlatypusView parent) {
        this();
        this.parent = parent;
    }

    public PlatypusView getMainView() {
        return parent;
    }
    
    public int numberOfActiveFilters() {
    	return addedFilterPanels.size();
    }
    
    public void setPreviewOriginal(ImageIcon img) {
    	previewPanel1.setPreviewOriginal(img);
    }
    
    public void setPreviewFiltered(ImageIcon img) {
    	previewPanel1.setPreviewFiltered(img);
    }
    
    public void addFilterPanel(IFilter filter) {
    	addedFilterPanels.add(new FilterPanel(filter, this));
    	currentPanelIndex++;
    	updateFilterPanelButtons();
    	showFilterPanel(addedFilterPanels.get(addedFilterPanels.size()-1));
    	parent.setBrowseButtonNext();
    }
    
    public void removeFilterPanel(IFilter filter) {
    	for (int i = 0; i < addedFilterPanels.size(); i++) {
    		if (addedFilterPanels.get(i).getFilter() == filter) {
    	    	addedFilterPanels.remove(i);
    			if (addedFilterPanels.size() == 0) {
    				currentPanelIndex = -1;
    				parent.setBrowseButtonAdd();
    				showPreviousFilterPanel();
    			} else {
    				currentPanelIndex = addedFilterPanels.size()-1;
    				showFilterPanel(addedFilterPanels.get(addedFilterPanels.size()-1));
    			}
    	    	updateFilterPanelButtons();
    	    	break;
    		}
    	}
    }
    private void showFilterPanel(FilterPanel panel) {
    	jSplitPane1.setRightComponent(panel);
        setDivider();
    	System.out.println("Showing filter: "+(currentPanelIndex+1)+"/"+(addedFilterPanels.size()));
    }
    
    public void showNextFilterPanel() {
    	if (currentPanelIndex+1 < addedFilterPanels.size()) {
        	showFilterPanel(addedFilterPanels.get(++currentPanelIndex));
    	} else {
    		parent.showAddFilterDialog();
    	}
    }
    
    public void showPreviousFilterPanel() {
    	if (currentPanelIndex > 0) {
    		showFilterPanel(addedFilterPanels.get(--currentPanelIndex));
    	} else {
    		parent.showPreviousView();
    	}
    }
    
    private void updateFilterPanelButtons() {
    	for (int i = 0; i <= addedFilterPanels.size()-2; i++) {
    		addedFilterPanels.get(i).setAddButtonToNext();
    	}
    }
    
    public void setDivider() {
        if (jSplitPane1 != null) {
        	jSplitPane1.setDividerLocation(0.6);
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

        jSplitPane1 = new javax.swing.JSplitPane();
        previewPanel1 = new edu.chalmers.platypus.view.gui.PreviewPanel();
        //filterPanel1 = new edu.chalmers.platypus.view.FilterPanel(null, this);

        setName("Form"); // NOI18N

        jSplitPane1.setDividerLocation(180);
        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        jSplitPane1.setName("jSplitPane1"); // NOI18N
        jSplitPane1.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                jSplitPane1ComponentResized(evt);
            }
        });

        previewPanel1.setName("previewPanel1"); // NOI18N
        jSplitPane1.setTopComponent(previewPanel1);

        //filterPanel1.setName("filterPanel1"); // NOI18N
        //jSplitPane1.setRightComponent(filterPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 463, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jSplitPane1ComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jSplitPane1ComponentResized
    	setDivider();
    }//GEN-LAST:event_jSplitPane1ComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private edu.chalmers.platypus.view.gui.FilterPanel filterPanel1;
    private javax.swing.JSplitPane jSplitPane1;
    private edu.chalmers.platypus.view.gui.PreviewPanel previewPanel1;
    // End of variables declaration//GEN-END:variables

    private PlatypusView parent;
    private ArrayList<FilterPanel> addedFilterPanels = new ArrayList<FilterPanel>();
    private int currentPanelIndex = -1;
    
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String change = evt.getPropertyName();
		if (change.equals(StateChanges.NEW_FILTER_ADDED_TO_BATCH.toString())) {
			addFilterPanel((IFilter)evt.getNewValue());
		}
		else if (change.equals(StateChanges.FILTER_REMOVED_FROM_BATCH.toString())) {
			removeFilterPanel((IFilter)evt.getOldValue());
		}
	}

}
