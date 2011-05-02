package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;

public class RunBatch implements Runnable {
	String writePath;
	String writeExt;
	
	public RunBatch(String path, String ext) {
		this.writePath = path;
		this.writeExt = ext;
	}

	@Override
	public void run() {
		ArrayList<BatchImage> imgBatch = Locator.getModel().getImageBatch();
		ArrayList<IFilter> activeFilters = Locator.getModel().getActiveFilters().getList();
		for (BatchImage batchImg : imgBatch) {
			for (IFilter filter : activeFilters) {
				//BufferedImage filteredImg = filter.applyFilter(batchImg.getImage()); removed until valid filters are present
				BufferedImage filteredImg = batchImg.getImage();
				try {
					ImageIO.write(filteredImg, writeExt, new File(writePath + File.separatorChar + batchImg.getFileName() + "_new." + writeExt));
				} catch (IOException e) {
					System.out.println("Failed to write image: " + "test.jpg");
					e.printStackTrace();
				}
			}	
		}
	}

}
