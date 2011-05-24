package edu.chalmers.platypus.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.List;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import edu.chalmers.platypus.ctrl.PlatypusCtrl;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.Locator;

public class PlatypusCtrlTest {

	PlatypusCtrl ctrl;
	PlatypusModel model;
	
	@Before
	public void setUp() throws Exception {
		ctrl = PlatypusCtrl.getInstance();
		model = PlatypusModel.getInstance();
		Locator.setCtrl(ctrl);
		Locator.setModel(model);
	}

	@Test
	public void testGetInstance() {
		assertTrue(PlatypusCtrl.getInstance() == ctrl);
	}

	@Test
	public void testResetModel() {
		ctrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		ctrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		ctrl.addImageToBatch(new File(""));
		ctrl.addImageToBatch(new File(""));

		ctrl.resetModel();
		assertTrue(model.getBatchSize() == 0 && model.getActiveFilters().getList().size() == 0);
	}

	@Test
	public void testAddImageToBatch() {
		int nrOfImagesBefore = model.getBatchSize();
		ctrl.addImageToBatch(new File(""));
		int nrOfImagesAfter = model.getBatchSize();

		assertTrue(nrOfImagesBefore+1 == nrOfImagesAfter);
	}

	@Test
	public void testRemoveImageFromBatch() {
		int nrOfImagesBefore = model.getBatchSize();
		
		BatchImage img = DummyImageFactory.getRandomDummyBatchImage();
		model.getImageBatch().add(img);
		ctrl.removeImageFromBatch(img);
		
		int nrOfImagesAfter = model.getBatchSize();

		assertTrue(nrOfImagesBefore == nrOfImagesAfter);
	}

	@Test
	public void testClearImageBatch() {
		ctrl.addImageToBatch(new File(""));
		ctrl.addImageToBatch(new File(""));

		ctrl.clearImageBatch();
		assertTrue(model.getBatchSize() == 0);
	}

	@Test
	public void testSaveImages() {
		File folder = new File(System.getProperty("user.home")+File.separator+"PlatyPix"+File.separator+"Pictures");
		File img = new File("test/resources/testimage.jpg");
		
		File existingImg = new File(folder.getAbsolutePath()+File.separator+"testimage_new.jpg");
		if (existingImg.exists()) {
			existingImg.delete();
		}
		
		model.getImageBatch().clear();
		model.getImageBatch().add(new BatchImage(img));
		model.getActiveFilters().removeAll();
		
		int sizeBefore = folder.listFiles().length;
		
		ctrl.saveImages(folder.toString(), "jpg");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		int sizeAfter = folder.listFiles().length;
		
		if (!(sizeBefore+1 == sizeAfter)) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		assertTrue(sizeBefore+1 == sizeAfter);
	}

	@Test
	public void testAbortSaveOperation() {
		File folder = new File(System.getProperty("user.home")+File.separator+"PlatyPix"+File.separator+"Pictures");
		File[] imgList = new File("test/resources/").listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.toString().contains(".jpg");
			}
		});
		
		for (File f : folder.listFiles()) {
			f.delete();
		}

		model.getImageBatch().clear();
		for (File f : imgList) {
			model.getImageBatch().add(new BatchImage(f));
		}
		model.getActiveFilters().getList().add(model.getFilterContainer().getList().get(0));
		
		ctrl.saveImages(folder.getPath(), "jpg");
		
		int sizeBefore = folder.listFiles().length;
		ctrl.abortSaveOperation();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		int sizeAfter = folder.listFiles().length;
		
		if (!(sizeBefore == sizeAfter)) {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		assertTrue(sizeBefore == sizeAfter);
	}

	@Test
	public void testGetLoadedFilterList() {
		int sizeBefore = ctrl.getLoadedFilterList().size();
		model.getFilterContainer().addFilter(DummyFilterFactory.getRandomDummyFilter());
		int sizeAfter = ctrl.getLoadedFilterList().size();
		
		assertTrue(sizeBefore+1 == sizeAfter);
	}

	@Test
	public void testAddFilterToBatch() {
		int sizeBefore = model.getActiveFilters().getList().size();
		ctrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		
		int sizeAfter = model.getActiveFilters().getList().size();

		assertTrue(sizeBefore+1 == sizeAfter);
	}

	@Test
	public void testRemoveFilterFromBatch() {
		IFilter f = DummyFilterFactory.getRandomDummyFilter();
		ctrl.addFilterToBatch(f);
		ctrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		ctrl.removeFilterFromBatch(f);

		assertFalse(model.getActiveFilters().getList().contains(f));
	}

	@Test
	public void testImportNewFilter() {
		System.out.println("Not yet implemented");
	}

	@Test
	public void testSetNewPreview() {
		BatchImage previewBefore = model.getPreview();
		
		BatchImage previewToAdd = DummyImageFactory.getRandomDummyBatchImage();
		ctrl.setNewPreview(previewToAdd);
		BatchImage previewAfter = model.getPreview();

		assertTrue(previewBefore != previewAfter && previewToAdd == previewAfter);
	}

	@Test
	public void testGetPreviewOriginal() {
		File f = new File("test/resources/testimage.jpg");
		BatchImage testImg = new BatchImage(f);
		ctrl.setNewPreview(testImg);
		
		int width = (int)(Math.random()*100)+1;
		int height = (int)(Math.random()*100)+1;
		
		ImageIcon preview = ctrl.getPreviewOriginal(width, height);
		assertTrue(preview.getIconWidth() == width || preview.getIconHeight() == height);
	}

	@Test
	public void testGetPreviewFiltered() {
		File f = new File("test/resources/testimage.jpg");
		BatchImage testImg = new BatchImage(f);
		ctrl.setNewPreview(testImg);
		
		int width = (int)(Math.random()*100)+1;
		int height = (int)(Math.random()*100)+1;
		
		ImageIcon preview = ctrl.getPreviewOriginal(width, height);
		assertTrue(preview.getIconWidth() == width || preview.getIconHeight() == height);
	}

	@Test
	public void testGetLoadedPresetList() {
		File folder = new File(System.getProperty("user.home")+File.separator+"PlatyPix"+File.separator+"Presets");
		File[] presetFiles = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().contains(".info");
			}
		});
		int numberOfPresetFiles = presetFiles.length;
		
		assertTrue(ctrl.getLoadedPresetList().size() == numberOfPresetFiles);
	}

	@Test
	public void testLoadPreset() {
		model.getActiveFilters().getList().clear();
		Preset p = ctrl.getLoadedPresetList().get(0);
		int numberOfFilters = p.getFilters().length;
		
		ctrl.loadPreset(p);
		assertTrue(model.getActiveFilters().getList().size() == numberOfFilters);
	}

	@Test
	public void testSavePreset() {
		int numberOfFilters = model.getFilterContainer().getList().size();
		for (IFilter filter : model.getFilterContainer().getList()) {
			model.getActiveFilters().getList().add(filter);
		}
		final String name = "TestPreset ID"+(int)(Math.random()*10);
		ctrl.savePreset(name);
		
		File[] presetFolder = new File(System.getProperty("user.home")+File.separator+"PlatyPix"+File.separator+"Presets").listFiles(new FileFilter() {
			
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().contains(name);
			}
		});
				
		assertTrue(presetFolder.length == 2);
		
		for (File f : presetFolder) {
		    if (f.isDirectory()) {
		        File[] children = f.listFiles();
		        for (File child : children) {
		            child.delete();
		        }
		    }
			f.delete();
		}
	}

}
