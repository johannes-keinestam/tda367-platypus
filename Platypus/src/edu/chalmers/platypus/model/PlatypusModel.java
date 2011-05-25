package edu.chalmers.platypus.model;

import java.util.ArrayList;

/**
 * The anemic model for PlatyPix. Contains little to no logic. Represents the
 * data in the program.
 */
public class PlatypusModel {
	private final ActiveFilters activeFilters;
	private final FilterContainer filterContainer;
	private final ArrayList<BatchImage> imageBatch = new ArrayList<BatchImage>();
	private static PlatypusModel instance;
	private BatchImage preview;

        /** Constructor */
	private PlatypusModel() {
		activeFilters = ActiveFilters.getActiveFilters();
		filterContainer = FilterContainer.getFilterContainerObject();
	}

        /** Singleton */
	public static PlatypusModel getInstance() {
		if (instance == null) {
			instance = new PlatypusModel();
		}
		return instance;
	}

        /** Returns the list of images currently added to the application. */
	public ArrayList<BatchImage> getImageBatch() {
		return imageBatch;
	}

        /** Returns the filter container, of filters loaded into the program */
	public FilterContainer getFilterContainer() {
		return filterContainer;
	}

        /** Returns the container of filters currently active */
	public ActiveFilters getActiveFilters() {
		return activeFilters;
	}

        /** Sets a preview image */
	public void setPreview(BatchImage preview) {
		this.preview = preview;
	}

        /** Returns the currently set previe */
	public BatchImage getPreview() {
		return preview;
	}
}
