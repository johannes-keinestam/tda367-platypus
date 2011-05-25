package edu.chalmers.platypus.ctrl;

import java.beans.PropertyChangeEvent;

import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.util.interfaces.IMiscCtrl;

public class MiscCtrl implements IMiscCtrl{
	private static MiscCtrl instance;

	/**
	 * Constructor
	 */
	private MiscCtrl() {
	}
	
	/**
	 * Returns the singleton instance of this Class, if none exist
	 * the constructor is run and the instance is set.
	 * 
	 * @return the singleton instance of the MiscCtrl.
	 */
	public static MiscCtrl getInstance() {
		if (instance == null) {
			instance = new MiscCtrl();
		}
		return instance;
	}

	
	/**
	 * Resets the PlatypusModel, clearing both ActiveFilters and ImageBatch 
	 * of content. 
	 * 
	 */
	public void resetModel() {
		ModelLocator.getModel().getActiveFilters().getList().clear();

		ModelLocator.getModel().getImageBatch().clear();

		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.MODEL_RESET.toString(), null, null));

	}
}
