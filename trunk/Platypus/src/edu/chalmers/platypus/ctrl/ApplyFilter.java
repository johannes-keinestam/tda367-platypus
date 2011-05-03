package edu.chalmers.platypus.ctrl;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.IFilter;

public class ApplyFilter implements Runnable {
	BufferedImage filteredImage;

	public ApplyFilter(BufferedImage originalImage) {
		this.filteredImage = originalImage;
	}

	@Override
	public void run() {
		ArrayList<IFilter> activeFilters = Locator.getModel().getActiveFilters().getList();
		for (IFilter filter : activeFilters) {
			this.filteredImage = filter.applyFilter(this.filteredImage);
		}

	}

}
