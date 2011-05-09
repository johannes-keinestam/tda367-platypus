package edu.chalmers.platypus.ctrl;

import java.beans.PropertyChangeEvent;
import java.util.Observable;
import java.util.Observer;

import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.StateChanges;

public class FilterListener implements Observer{

	@Override
	public void update(Observable o, Object arg) {
		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.PREVIEW_IMAGE_UPDATED.toString(), null, o));
	}

}
