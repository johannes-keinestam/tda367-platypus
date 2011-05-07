package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.chalmers.platypus.ComBus;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.util.StateChanges;

public class RunBatch implements Runnable {
	private ArrayList<IFilter> activeFilters = Locator.getModel().getActiveFilters().getList();
	private BufferedImage currentImage;
	private String writePath;
	private String writeExtension;

	public RunBatch(String writePath, String writeExtension) {
		this.writePath = writePath;
		this.writeExtension = writeExtension;
	}

	public RunBatch(BufferedImage originalImage) {
		this.currentImage = originalImage;
	}

	@Override
	public void run() {
		if (this.currentImage != null) {
			applyFilters();
			return;
		}
		
		ArrayList<BatchImage> imageBatch = Locator.getModel().getImageBatch();
		for (BatchImage batchImage : imageBatch) {
			ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.PROCESSING_IMAGE.toString(), batchImage, null));
			this.currentImage = batchImage.getImage();
			applyFilters();
			
			File outputFile = new File(this.writePath + File.separatorChar + batchImage.getFileName() + "_new." + this.writeExtension);
			try {
				ImageIO.write(this.currentImage, this.writeExtension, outputFile);
				ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.SAVED_IMAGE.toString(), outputFile, null));
			} catch (IOException e) {
				System.out.println("Failed to write image: " + outputFile.getName());
				e.printStackTrace();
			}
		}
		
		ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.SAVE_OPERATION_FINISHED.toString(), null, null));
	}

	private void applyFilters() {
		for (IFilter filter : activeFilters) {
			this.currentImage = filter.applyFilter(this.currentImage);
		}
	}

}
