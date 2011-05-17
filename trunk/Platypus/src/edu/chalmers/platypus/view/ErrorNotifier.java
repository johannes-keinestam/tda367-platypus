package edu.chalmers.platypus.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.gui.PlatypusView;

/**
 * Class for intercepting errors from backend, and showing a popup dialog
 * message if needed. Used only for errors that needs user interaction.
 * 
 */
public class ErrorNotifier implements PropertyChangeListener{
    /**  Reference to main view panel */
    private PlatypusView parent;

    /** Constructor */
    private ErrorNotifier(){
        //Subscribes for interception of backend messages
        ComBus.subscribe(this);
    }

    /** Constructor */
    public ErrorNotifier(PlatypusView parent) {
        this();
        this.parent = parent;
    }

    /**
     * Shows modal error message with the specified text.
     *
     * @param message the descriptive error text to be shown.
     */
    public void ErrorOccured(String message){
        JOptionPane.showMessageDialog(parent.getComponent(),message);
    }

    /**
     * Recieves info about operations from backend.
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        String change = evt.getPropertyName();
        if (change.equals(StateChanges.ERROR_OCCURED.toString())) {
            //An error reported by the backend. Shows message dialog.
            String message = (String) evt.getNewValue();
            ErrorOccured(message);
        }
    }
}
