package edu.chalmers.platypus.util;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

/**
 * Communication bus for sending StateChanges from ctrl/model to GUI.
 */
public class ComBus {
	private static final List<PropertyChangeListener> listeners = new LinkedList<PropertyChangeListener>();

        /**
         * Sends a message (PropertyChangeEvent) to subscribed listeners. Used
         * when something has changed in the model, and the GUI (or whomever)
         * has to be notified.
         */
	public static void notifyListeners(PropertyChangeEvent pce) {
		for (PropertyChangeListener pcl : listeners) {
			pcl.propertyChange(pce);
		}
	}

        /**
         * Subscribes a class implementing PropertyChangeListener. This class
         * will thus listen for changes made in the model.
         */
	public static void subscribe(PropertyChangeListener pcl) {
		listeners.add(pcl);
	}

}
