package edu.chalmers.platypus.test;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.util.Locator;

public class PlatypusModelTest {
	
	PlatypusModel model;
	BatchImage preview;
	BatchImage batchImage;
	
	@Before
	public void setUp() throws Exception {
		model = Locator.getModel();
		File testImageFile = new File("test/resources/testimage.jpg");
		preview = new BatchImage(testImageFile);
		
		batchImage = new BatchImage(testImageFile);
	}

	@Test
	public void testGetImageBatch() {
		assertTrue(model.getImageBatch() != null);
	}

	@Test
	public void testGetBatchSize() {
		int batchSizeBefore = model.getBatchSize();
		ArrayList<BatchImage> imageBatch = model.getImageBatch();
		imageBatch.add(new BatchImage(null));
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
		model.setPreview(preview);
		
		assertTrue(model.getPreview() == preview);
	}
	
	@Test
	public void testGetPresets() {
		assertTrue(model.getPresets() != null);
	}

	/** 
	 * Tests for BatchImage.java 
	 */
	@Test
	public void testGetThumbnail() {
		
		BufferedImage thumbnail = batchImage.getThumbnail();
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	@Test
	public void test() {
		assertTrue();
	}

	
}
