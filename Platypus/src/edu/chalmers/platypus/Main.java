package edu.chalmers.platypus;

import java.io.File;

import edu.chalmers.platypus.ctrl.PlatypusCtrl;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.util.Locator;
import edu.chalmers.platypus.view.PlatypusGUI;
import edu.chalmers.platypus.view.splash.SplashScreenHandler;

/**
 * 
 * @author skoldator
 */
public class Main {
    private static enum Directories {
            FILTERS, PICTURES, PRESETS
    }

    public static void main(String[] args) {
        SplashScreenHandler sch = new SplashScreenHandler();

        String writeDirectory = System.getProperty("user.home")
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

        sch.splashText("Creating model...");
        PlatypusModel model = PlatypusModel.getInstance();
        sch.splashText("Creating controller...");
        PlatypusCtrl ctrl = PlatypusCtrl.getInstance();
        sch.splashText("Creating GUI...");
        PlatypusGUI gui = PlatypusGUI.getInstance();

        sch.splashText("Setting up");
        Locator.setModel(model);
        Locator.setCtrl(ctrl);

    }

}
