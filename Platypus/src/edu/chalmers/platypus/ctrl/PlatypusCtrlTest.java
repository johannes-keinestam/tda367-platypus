package edu.chalmers.platypus.ctrl;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import edu.chalmers.platypus.Locator;
import edu.chalmers.platypus.model.PlatypusModel;

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
		Locator.setCtrl(ctrl);
		Locator.setModel(model);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddImageToBatch() {
		int nrOfImages = model.getBatchSize();
		ctrl.addImageToBatch(new File(""));
		assertTrue("AddImage doesn't work!", model.getBatchSize() == nrOfImages + 1);
	}

}

