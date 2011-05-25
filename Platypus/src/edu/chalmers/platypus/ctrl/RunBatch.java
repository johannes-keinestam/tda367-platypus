package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.util.CtrlLocator;
import edu.chalmers.platypus.util.StateChanges;

public class RunBatch implements Runnable {
	private static String writePath;
	private static String writeExtension;
	private static Thread t;

	public static void start(String writePath, String writeExtension) {
		RunBatch.writePath = writePath;
		RunBatch.writeExtension = writeExtension;
		t = new Thread(new RunBatch());
		t.start();
	}

	public static void stop() {
		t.stop();
		System.gc();
		ComBus.notifyListeners(new PropertyChangeEvent(t,
				StateChanges.SAVE_OPERATION_ABORTED.toString(), null,
				null));
	}

	@Override
	public void run() {
		for (BatchImage batchImage : ModelLocator.getModel().getImageBatch()) {
			ComBus.notifyListeners(new PropertyChangeEvent(this,
					StateChanges.PROCESSING_IMAGE.toString(), null, batchImage));

			BufferedImage currentImage = applyFilters(batchImage.getImage());

			File outputFile = new File(RunBatch.writePath + File.separatorChar
					+ batchImage.getFileName() + "_new."
					+ RunBatch.writeExtension);
			try {
				Thread.sleep(10);
				ImageIO.write(currentImage, RunBatch.writeExtension, outputFile);
				ComBus.notifyListeners(new PropertyChangeEvent(this,
						StateChanges.SAVED_IMAGE.toString(), null, outputFile));
			} catch (IOException ioe) {
				System.out.println("Failed to write image: "
						+ outputFile.getName() + " to " + RunBatch.writePath);
			} catch (InterruptedException ie) {
				ComBus.notifyListeners(new PropertyChangeEvent(this,
						StateChanges.SAVE_OPERATION_ABORTED.toString(), null,
						null));
				return;
			}
		}
		System.gc();
		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.SAVE_OPERATION_FINISHED.toString(), null, null));
	}

	public static BufferedImage applyFilters(BufferedImage img) {
		for (IFilter filter : ModelLocator.getModel().getActiveFilters().getList()) {
			ComBus.notifyListeners(new PropertyChangeEvent(filter,
					StateChanges.APPLYING_FILTER.toString(), null, filter));
			img = filter.applyFilter(img);
			System.gc();
		}
		return img;
	}
}
