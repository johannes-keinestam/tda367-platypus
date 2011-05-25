package edu.chalmers.platypus;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import edu.chalmers.platypus.ctrl.FilterCtrl;
import edu.chalmers.platypus.ctrl.ImageCtrl;
import edu.chalmers.platypus.ctrl.MiscCtrl;
import edu.chalmers.platypus.ctrl.PresetCtrl;
import edu.chalmers.platypus.ctrl.PreviewCtrl;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.util.CtrlLocator;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.view.PlatypusGUI;
import edu.chalmers.platypus.view.splash.SplashScreenHandler;

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
	ModelLocator.setModel(model);

	sch.splashText("Creating controllers...");
	MiscCtrl miscCtrl = MiscCtrl.getInstance();
	FilterCtrl filterCtrl = FilterCtrl.getInstance();
	ImageCtrl imageCtrl = ImageCtrl.getInstance();
	PresetCtrl presetCtrl = PresetCtrl.getInstance();
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

	String[] directoryNames = { "Pictures", "Filters", "Presets" };

	boolean shouldInstall = false;
	for (String dir : directoryNames) {
	    if (new File(writeDirectory + dir).mkdirs()) {
		System.out.println("Created directory: \"" + writeDirectory
			+ dir + "\"");
		shouldInstall = true;
	    } else {
		System.out.println("Directory: \"" + writeDirectory + dir
			+ "\" already exists");
	    }
	}
	if (shouldInstall) {
	    try {
		installDefaultFilters();
	    } catch (Exception ex) {
		// Skips install
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
			+ "/PlatyPix/Filters/" + filter.getName());

		FileChannel outputChannel = new FileOutputStream(outputFile)
			.getChannel();
		FileChannel sourceChannel = new FileInputStream(filter)
			.getChannel();

		outputChannel.transferFrom(sourceChannel, 0,
			sourceChannel.size());
		sourceChannel.close();
		outputChannel.close();
	    }
	}
    }

}
