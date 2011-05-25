package edu.chalmers.platypus.test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.chalmers.platypus.model.ActiveFilters;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.FilterContainer;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.test.DummyFilterFactory;
import edu.chalmers.platypus.test.DummyImageFactory;
import edu.chalmers.platypus.util.ModelLocator;

public class PlatypusModelTest {
	
	PlatypusModel model;
	
	@Before
	public void setUp() throws Exception {
		model = PlatypusModel.getInstance();
		
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
		int batchSizeBefore = model.getImageBatch().size();
		ArrayList<BatchImage> imageBatch = model.getImageBatch();
		imageBatch.add(DummyImageFactory.getRandomDummyBatchImage());
		int batchSizeAfter = model.getImageBatch().size();
		
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
		activeFilters.getList().clear();
		
		assertTrue(activeFilters.getList().size() == 0);
	}
	
	/** 
	 * Tests for BatchImage.java 
	 */
	@Test
	public void testGetThumbnail() {
		BatchImage batchImage;
		BufferedImage thumbnail;
		int thumbnailWidth;
		int thumbnailHeight;
		
		//test landscape image
		batchImage = new BatchImage(new File("test/resources/testimage.jpg"));
		thumbnailWidth = batchImage.getImage().getWidth() - (new Random().nextInt(100)+1);
		thumbnailHeight = batchImage.getImage().getHeight() - (new Random().nextInt(100)+1);
		thumbnail = batchImage.getThumbnail(thumbnailWidth, thumbnailHeight);
		
		assertTrue(thumbnail.getWidth() == thumbnailWidth || thumbnail.getHeight() == thumbnailHeight);
		
		//Test portrait image
		batchImage = new BatchImage(new File("test/resources/testimage2.jpg"));
		thumbnailWidth = batchImage.getImage().getWidth() - (new Random().nextInt(100)+1);
		thumbnailHeight = batchImage.getImage().getHeight() - (new Random().nextInt(100)+1);
		thumbnail = batchImage.getThumbnail(thumbnailWidth, thumbnailHeight);
		
		assertTrue(thumbnail.getWidth() == thumbnailWidth || thumbnail.getHeight() == thumbnailHeight);
		
		//Test no scaling
		batchImage = new BatchImage(new File("test/resources/testimage.jpg"));
		thumbnail = batchImage.getThumbnail(-1, -1);
		BufferedImage bufferedImage = batchImage.getImage();
		
		assertTrue(thumbnail.getWidth() == bufferedImage.getWidth() && thumbnail.getHeight() == bufferedImage.getHeight());
	}

	@Test
	public void testGetImage() {
		BatchImage batchImage = new BatchImage(new File("test/resources/testimage.jpg"));

		assertTrue(batchImage.getImage() != null);
	}

	@Test
	public void testGetFileName() {
		BatchImage batchImage = new BatchImage(new File("test/resources/testimage.jpg"));
		
		assertTrue(batchImage.getFileName().equals("testimage"));
	}

	/** 
	 * Tests for FilterContainer.java 
	 */
	@Test
	public void testAddFilter() {
		FilterContainer filterContainer = FilterContainer.getFilterContainerObject();
		int filterContainerSizeBefore = filterContainer.getList().size();
		filterContainer.addFilter(DummyFilterFactory.getRandomDummyFilter());
		int filterContainerSizeAfter = filterContainer.getList().size();
		
		assertTrue(filterContainerSizeBefore+1 == filterContainerSizeAfter);
	}

	@Test
	public void testGetFilter() {
		String filterName = "testFilter";
		FilterContainer filterContainer = FilterContainer.getFilterContainerObject();
		filterContainer.addFilter(DummyFilterFactory.getNamedDummyFilter(filterName));
		
		assertTrue(filterContainer.getFilter(filterName).getName().equals(filterName));
	}

	/** 
	 * Tests for Preset.java 
	 */
	@Test
	public void testGetName() {
		String presetName = "test_preset";
		Preset preset = new Preset(presetName, new String[] {"dummyFilter1", "dummyFilter2"});
		
		assertTrue(preset.getName().equals(presetName));
	}

	@Test
	public void testGetDate() {
		Preset preset = new Preset("test_preset", new String[] {"dummyFilter1", "dummyFilter2"});
		
		assertTrue(preset.getDate().compareTo(new Date()) <= 0);
	}

	@Test
	public void testGetFilters() {
		String[] filters = {"dummyFilter1", "dummyFilter2"};
		Preset preset = new Preset("test_preset", filters);
		
		assertTrue(preset.getFilters() == filters);
	}
	
}
