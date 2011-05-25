package edu.chalmers.platypus.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.gui.FilterPanel;
import edu.chalmers.platypus.view.gui.PlatypusView;

/**
 * Panel that shows a PreviewPanel (i.e. two preview panels) and a FilterPanel
 * (panel containing filter controls). Used by the user to make applying of
 * filters easier.
 */
public class FilterViewPanel extends javax.swing.JPanel implements
	PropertyChangeListener {

    /** Constructor */
    public FilterViewPanel() {
	initComponents();

	// Listens for communication from backend
	ComBus.subscribe(this);
    }

    /**
     * Constructor
     * 
     * @param parent
     *            main view
     */
    public FilterViewPanel(PlatypusView parent) {
	this();
	this.parent = parent;
    }

    /**
     * Returns the main view. For use by child panels if needed.
     * 
     * @return main view
     */
    public PlatypusView getMainView() {
	return parent;
    }

    /**
     * The number of added filter panels. Used for numbering of added filter
     * panels as well as updating button text correctly.
     * 
     * @return current number of added filter panels
     */
    public int numberOfActiveFilters() {
	return addedFilterPanels.size();
    }

    /**
     * Creates and adds FilterPanel for specified filter. Used when filters are
     * chosen from AddFilterDialog, for representing them with their own panel.
     * 
     * @param filter
     *            instance of filter to add
     */
    public void addFilterPanel(IFilter filter) {
	addedFilterPanels.add(new FilterPanel(filter, this));
	currentPanelIndex++;

	updateFilterPanels();
	showFilterPanel(addedFilterPanels.get(addedFilterPanels.size() - 1));

	// Allows navigation from previous view
	parent.setBrowseButtonNext();
    }

    /**
     * Removes panel of specified filter. Used when the user clicks the Remove
     * filter button.
     * 
     * @param filter
     *            instance of filter to remove
     */
    public void removeFilterPanel(IFilter filter) {
	for (int i = 0; i < addedFilterPanels.size(); i++) {
	    if (addedFilterPanels.get(i).getFilter() == filter) {
		addedFilterPanels.remove(i);
		if (addedFilterPanels.isEmpty()) {
		    // Disallow navigation to filter view and show
		    // BrowseViewPanel
		    currentPanelIndex = -1;
		    parent.setBrowseButtonAdd();
		    showPreviousFilterPanel();
		} else {
		    // If any filters left, show last one
		    currentPanelIndex = addedFilterPanels.size() - 1;
		    showFilterPanel(addedFilterPanels.get(addedFilterPanels
			    .size() - 1));
		}
		// Update filter panels left
		updateFilterPanels();
		break;
	    }
	}
    }

    /**
     * Show a specific FilterPanel. Used to show a filter when it is added, or
     * when a filter is removed and it has to show another one.
     * 
     * @param panel
     *            FilterPanel to show
     */
    private void showFilterPanel(FilterPanel panel) {
	filterViewSplitPane.setRightComponent(panel);

	setDivider();
    }

    /**
     * Show next filter. If no next filter available, show Add Filter dialog.
     * Used when the user clicks Next (or Add Filter) button.
     */
    public void showNextFilterPanel() {
	if (currentPanelIndex + 1 < addedFilterPanels.size()) {
	    showFilterPanel(addedFilterPanels.get(++currentPanelIndex));
	} else {
	    parent.showAddFilterDialog();
	}
    }

    /**
     * Show previous filter. If no previous filter available, show
     * BrowseViewPanel. Used when user clicks Previous button.
     */
    public void showPreviousFilterPanel() {
	if (currentPanelIndex > 0) {
	    showFilterPanel(addedFilterPanels.get(--currentPanelIndex));
	} else {
	    parent.showPreviousView();
	}
    }

    /**
     * Update buttons and title of FilterPanels. Last panel Next button is set
     * to Add Filter, and the other panels' button to Next. Updates the filter
     * numbering as well. Used when a new filter is added.
     */
    private void updateFilterPanels() {
	for (int i = 0; i <= addedFilterPanels.size() - 1; i++) {
	    if (i != addedFilterPanels.size() - 1) {
		addedFilterPanels.get(i).setAddButtonToNext();
	    } else {
		addedFilterPanels.get(i).setAddButtonToAdd();
	    }
	    addedFilterPanels.get(i).setFilterNumber(i + 1,
		    addedFilterPanels.size());
	}
    }

    /**
     * Sets divider of splitpanel, and used for keeping a good ratio between the
     * preview area (PreviewPanel) and the filter area (FilterPanel) on the
     * screen.
     */
    public void setDivider() {
	if (filterViewSplitPane != null) {
	    filterViewSplitPane.setDividerLocation(0.6);
	}
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

	filterViewSplitPane = new javax.swing.JSplitPane();
	previewPanel = new edu.chalmers.platypus.view.gui.PreviewPanel();

	setName("Form"); // NOI18N

	filterViewSplitPane.setDividerLocation(180);
	filterViewSplitPane
		.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
	filterViewSplitPane.setName("filterViewSplitPane"); // NOI18N
	filterViewSplitPane
		.addComponentListener(new java.awt.event.ComponentAdapter() {
		    public void componentResized(
			    java.awt.event.ComponentEvent evt) {
			filterViewSplitPaneComponentResized(evt);
		    }
		});

	previewPanel.setName("previewPanel"); // NOI18N
	filterViewSplitPane.setTopComponent(previewPanel);

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	this.setLayout(layout);
	layout.setHorizontalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		filterViewSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 463,
		Short.MAX_VALUE));
	layout.setVerticalGroup(layout.createParallelGroup(
		javax.swing.GroupLayout.Alignment.LEADING).addComponent(
		filterViewSplitPane, javax.swing.GroupLayout.DEFAULT_SIZE, 320,
		Short.MAX_VALUE));
    }// </editor-fold>//GEN-END:initComponents

    private void filterViewSplitPaneComponentResized(
	    java.awt.event.ComponentEvent evt) {// GEN-FIRST:event_filterViewSplitPaneComponentResized
	setDivider();
    }// GEN-LAST:event_filterViewSplitPaneComponentResized

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSplitPane filterViewSplitPane;
    private edu.chalmers.platypus.view.gui.PreviewPanel previewPanel;
    // End of variables declaration//GEN-END:variables

    /** Reference to parent (main view). Used by child panels */
    private PlatypusView parent;
    /** List of all added filter panels. Used for navigating between them. */
    private ArrayList<FilterPanel> addedFilterPanels = new ArrayList<FilterPanel>();
    /** Index of currently shown filter panel. Used to keep of navigation. */
    private int currentPanelIndex = -1;

    /**
     * Recieves info about operations from backend.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
	String change = evt.getPropertyName();
	if (change.equals(StateChanges.NEW_FILTER_ADDED_TO_BATCH.toString())) {
	    // New filter added to batch. Creates new panel for it.
	    addFilterPanel((IFilter) evt.getNewValue());
	} else if (change.equals(StateChanges.FILTER_REMOVED_FROM_BATCH
		.toString())) {
	    // Filter removed from batch. Removes associated panel.
	    removeFilterPanel((IFilter) evt.getOldValue());
	} else if (change.equals(StateChanges.MODEL_RESET.toString())) {
	    // Filters and images removed. Removes from GUI.
	    currentPanelIndex = -1;
	    addedFilterPanels.clear();
	}
    }

}
