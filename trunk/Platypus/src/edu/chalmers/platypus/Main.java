package edu.chalmers.platypus;

import edu.chalmers.platypus.ctrl.PlatypusCtrl;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.view.PlatypusApp;
/**
 *
 * @author skoldator
 */
public class Main {
    public static void main(String[] args) {
        PlatypusModel model = PlatypusModel.getInstance();
        PlatypusCtrl ctrl = PlatypusCtrl.getInstance();
    	PlatypusApp.showGUI(args);
    	
    	Locator.setModel(model);
    	Locator.setCtrl(ctrl);
    }
}
