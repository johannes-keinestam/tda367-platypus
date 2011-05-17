package edu.chalmers.platypus;

import java.io.File;

import edu.chalmers.platypus.ctrl.PlatypusCtrl;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.util.Locator;
import edu.chalmers.platypus.view.PlatypusGUI;

/**
 * 
 * @author skoldator
 */
public class Main {
	private static enum Directories {
		FILTERS, PICTURES, PRESETS
	}

	public static void main(String[] args) {

		String writeDirectory = System.getenv("USERPROFILE")
				+ File.separatorChar + "PlatyPix" + File.separatorChar;
		for (Directories dir : Directories.values()) {
			writeDirectory = writeDirectory.substring(0,
					writeDirectory.lastIndexOf(File.separatorChar) + 1)
					+ dir.toString().substring(0, 1)
					+ dir.toString().substring(1).toLowerCase();
			if (new File(writeDirectory).mkdirs()) {
				System.out.println("Created directory: \"" + writeDirectory
						+ "\"");
                        } else {
				System.out.println("Directory: \"" + writeDirectory
						+ "\" already exists");
                        }
		}

		PlatypusModel model = PlatypusModel.getInstance();
		PlatypusCtrl ctrl = PlatypusCtrl.getInstance();
		PlatypusGUI gui = PlatypusGUI.getInstance();

		Locator.setModel(model);
		Locator.setCtrl(ctrl);

	}

}
