package edu.chalmers.platypus;

import java.io.File;

import edu.chalmers.platypus.ctrl.impl.FilterCtrl;
import edu.chalmers.platypus.ctrl.impl.ImageCtrl;
import edu.chalmers.platypus.ctrl.impl.MiscCtrl;
import edu.chalmers.platypus.ctrl.impl.PresetCtrl;
import edu.chalmers.platypus.ctrl.impl.PreviewCtrl;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.util.CtrlLocator;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.view.PlatypusGUI;
import edu.chalmers.platypus.view.splash.SplashScreenHandler;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 
 * 
 */
public class Main {
    private static SplashScreenHandler sch = null;

    /**
     * Main method, used for starting and initializing the program.
     */
    public static void main(String[] args) {
        sch = new SplashScreenHandler();
        
        initializeDirectories();

        sch.splashText("Creating model...");
        PlatypusModel model = PlatypusModel.getInstance();
        
		sch.splashText("Setting up");
		ModelLocator.setModel(model);
		
        sch.splashText("Creating miscallenous controller...");
        MiscCtrl miscCtrl = MiscCtrl.getInstance();
        sch.splashText("Creating filter controller...");
        FilterCtrl filterCtrl = FilterCtrl.getInstance();
        sch.splashText("Creating image controller...");
        ImageCtrl imageCtrl = ImageCtrl.getInstance();
        sch.splashText("Creating preset controller...");
        PresetCtrl presetCtrl = PresetCtrl.getInstance();
        sch.splashText("Creating preview controller...");
        PreviewCtrl previewCtrl = PreviewCtrl.getInstance();
        
        sch.splashText("Creating GUI...");
        PlatypusGUI gui = PlatypusGUI.getInstance();


		sch.splashText("Setting up");
		CtrlLocator.setFilterCtrl(filterCtrl);
		CtrlLocator.setImageCtrl(imageCtrl);
		CtrlLocator.setPresetCtrl(presetCtrl);
		CtrlLocator.setPreviewCtrl(previewCtrl);
		CtrlLocator.setMiscCtrl(miscCtrl);
		
    }

    /**
     * Makes sure that all needed directories are available. If not, they are
     * created and the default filters are installed.
     */
    private static void initializeDirectories() {
        String writeDirectory = System.getProperty("user.home")
                        + File.separatorChar + "PlatyPix" + File.separatorChar;

        String[] directoryNames = {"Pictures","Filters","Presets"};

        boolean shouldInstall = false;
        for (String dir : directoryNames) {
            if (new File(writeDirectory + dir).mkdirs()) {
                System.out.println("Created directory: \"" + writeDirectory
                        + dir + "\"");
                shouldInstall = true;
            } else {
                    System.out.println("Directory: \"" + writeDirectory
                            + dir + "\" already exists");
            }
        }
        if (shouldInstall) {
            try {
                installDefaultFilters();
            } catch (Exception ex) {
                //Skips install
            }
        }
    }

    private static void installDefaultFilters() throws Exception {
        sch.splashText("Installing filters...");
        File filterLibrary = new File("filters/");
        if (filterLibrary.exists()) {
            File[] filters = filterLibrary.listFiles();
            for (File filter : filters) {
		File outputFile = new File(System.getProperty("user.home")
				+ "/PlatyPix/Filters/"
				+ filter.getName());

		FileChannel outputChannel = new FileOutputStream(outputFile).getChannel();
		FileChannel sourceChannel = new FileInputStream(filter).getChannel();

                outputChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
		sourceChannel.close();
                outputChannel.close();
            }
        }
    }

}
