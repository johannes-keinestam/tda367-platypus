package edu.chalmers.platypus.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnit4.class)
public class PlatypusCtrlTest {

	PlatypusCtrl ctrl;
	PlatypusModel model;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		ctrl = PlatypusCtrl.getInstance();
		model = PlatypusModel.getInstance();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddImageToBatch() {
		int nrOfImages = model.getBatchSize();
		ctrl.addImageToBatch(new File());
		assertTrue("AddImage doesn't work!", model.getBatchSize() == nrOfImages + 1);
	}

}
