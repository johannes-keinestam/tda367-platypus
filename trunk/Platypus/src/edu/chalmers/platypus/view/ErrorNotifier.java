package edu.chalmers.platypus.view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JOptionPane;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.view.gui.PlatypusView;

public class ErrorNotifier implements PropertyChangeListener{
	
    private PlatypusView parent;
	
	public ErrorNotifier(){
		ComBus.subscribe(this);
	}
	
	public ErrorNotifier(PlatypusView parent) {
        this();
        this.parent = parent;
    }
	
	public void ErrorOccured(String message){
		JOptionPane.showMessageDialog(parent.getComponent(),message);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("Stuff happens");
       	String change = evt.getPropertyName();
		if (change.equals(StateChanges.ERROR_OCCURED.toString())) {
            String message = (String) evt.getNewValue();
            ErrorOccured(message);
		}
	}
}
