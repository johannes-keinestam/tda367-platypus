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
	private Thread t;
	private String writePath;
	private String writeExtension;
	private ArrayList<IFilter> activeFilters = Locator.getModel().getActiveFilters().getList();
	
	public void start(String writePath, String writeExtension) {
		this.writePath = writePath;
		this.writeExtension = writeExtension;
		this.t = new Thread(this);
		t.start();
	}
	
	public void stop() {
		Thread.currentThread().interrupt();
	}
	
	public BufferedImage getFilteredImage(BufferedImage img) {
		return applyFilters(img);
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ArrayList<BatchImage> imageBatch = Locator.getModel().getImageBatch();
			for (BatchImage batchImage : imageBatch) {
				ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.PROCESSING_IMAGE.toString(), null, batchImage));
				BufferedImage currentImage = batchImage.getImage();
				currentImage = applyFilters(currentImage);
				
				File outputFile = new File(this.writePath + File.separatorChar
						+ batchImage.getFileName() + "_new." + this.writeExtension);
				try {
					ImageIO.write(currentImage, this.writeExtension, outputFile);
					ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.SAVED_IMAGE.toString(), null, outputFile));
				} catch (IOException e) {
					System.out.println("Failed to write image: " + outputFile.getName() + " to " + this.writePath);
					e.printStackTrace();
				}
			}
			
			Thread.currentThread().interrupt();
		}
		
		ComBus.notifyListeners(new PropertyChangeEvent(this, StateChanges.SAVE_OPERATION_FINISHED.toString(), null, null));
	}

	private BufferedImage applyFilters(BufferedImage img) {
		for (IFilter filter : activeFilters) {
			img = filter.applyFilter(img);
		}
		return img;
	}
}
