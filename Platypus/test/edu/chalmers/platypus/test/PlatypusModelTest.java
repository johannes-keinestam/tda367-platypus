package edu.chalmers.platypus.test;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.chalmers.platypus.model.ActiveFilters;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.test.DummyFilterFactory;
import edu.chalmers.platypus.test.DummyImageFactory;
import edu.chalmers.platypus.util.ModelLocator;

public class PlatypusModelTest {
	
	PlatypusModel model;
	
	@Before
	public void setUp() throws Exception {
		model = ModelLocator.getModel();
		
	}
	
	/** 
	 * Tests for PlatypusModel.java 
	 */
	@Test
	public void testGetImageBatch() {
		assertTrue(model.getImageBatch() != null);
	}

	@Test
	public void testGetBatchSize() {
		int batchSizeBefore = model.getBatchSize();
		ArrayList<BatchImage> imageBatch = model.getImageBatch();
		imageBatch.add(DummyImageFactory.getRandomDummyBatchImage());
		int batchSizeAfter = model.getBatchSize();
		
		assertTrue(batchSizeBefore+1 == batchSizeAfter);
	}
	
	@Test
	public void testGetFilterContainer() {
		assertTrue(model.getFilterContainer() != null);
	}
	
	@Test
	public void testGetActiveFilters() {
		assertTrue(model.getActiveFilters() != null);
	}
	
	@Test
	public void testPreview() {
		BatchImage preview = new BatchImage(new File("test/resources/testimage.jpg"));
		model.setPreview(preview);
		
		assertTrue(model.getPreview() == preview);
	}
	
	@Test
	public void testGetPresets() {
		assertTrue(model.getPresets() != null);
	}

	/** 
	 * Tests for ActiveFilters.java 
	 */
	@Test
	public void testGetList() {		
		assertTrue(ActiveFilters.getActiveFilters().getList() != null);
	}
	
	@Test
	public void testRemoveAll() {
		ActiveFilters activeFilters = ActiveFilters.getActiveFilters();
		ArrayList<IFilter> activeFiltersList = activeFilters.getList();
		activeFiltersList.add(DummyFilterFactory.getRandomDummyFilter());
		activeFiltersList.add(DummyFilterFactory.getRandomDummyFilter());
		activeFiltersList.add(DummyFilterFactory.getRandomDummyFilter());
		activeFilters.removeAll();
		
		assertTrue(activeFilters.getList().size() == 0);
	}

}
