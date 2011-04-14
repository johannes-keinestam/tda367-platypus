package edu.chalmers.platypus;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.LinkedList;
import java.util.List;

public class ComBus {

	private static final List<PropertyChangeListener> listeners = new LinkedList<PropertyChangeListener>();

	public static void notifyListeners(PropertyChangeEvent pce) {
		for (PropertyChangeListener pcl : listeners) {
			pcl.propertyChange(pce);
		}
	}
	
	public static void subscribe(PropertyChangeListener pcl) {
		listeners.add(pcl);
	}

}
