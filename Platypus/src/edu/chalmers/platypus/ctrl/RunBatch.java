package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.Locator;
import edu.chalmers.platypus.util.StateChanges;

public class RunBatch implements Runnable {
	private static String writePath;
	private static String writeExtension;
	private static RunBatch hej;

	public static void start(String writePath, String writeExtension) {
		RunBatch.writePath = writePath;
		RunBatch.writeExtension = writeExtension;
		new Thread(new RunBatch()).start();
	}

	public static void stop() {
		Thread.currentThread().interrupt();
	}

	public static BufferedImage getFilteredImage(BufferedImage img) {
		return applyFilters(img);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			RunBatch.hej = this;
			ArrayList<BatchImage> imageBatch = Locator.getModel()
					.getImageBatch();
			for (BatchImage batchImage : imageBatch) {
				ComBus.notifyListeners(new PropertyChangeEvent(this,
						StateChanges.PROCESSING_IMAGE.toString(), null,
						batchImage));
				BufferedImage currentImage = batchImage.getImage();
				currentImage = applyFilters(currentImage);

				File outputFile = new File(RunBatch.writePath
						+ File.separatorChar + batchImage.getFileName()
						+ "_new." + RunBatch.writeExtension);
				try {
					ImageIO.write(currentImage, RunBatch.writeExtension,
							outputFile);
					ComBus.notifyListeners(new PropertyChangeEvent(this,
							StateChanges.SAVED_IMAGE.toString(), null,
							outputFile));
				} catch (IOException e) {
					System.out.println("Failed to write image: "
							+ outputFile.getName() + " to "
							+ RunBatch.writePath);
					e.printStackTrace();
				}
			}

			Thread.currentThread().interrupt();
		}

		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.SAVE_OPERATION_FINISHED.toString(), null, null));
	}

	private static BufferedImage applyFilters(BufferedImage img) {
		for (IFilter filter : Locator.getModel().getActiveFilters().getList()) {
			ComBus.notifyListeners(new PropertyChangeEvent(null,
					StateChanges.APPLYING_FILTER.toString(), null, filter));
			img = filter.applyFilter(img);
		}
		return img;
	}
}
