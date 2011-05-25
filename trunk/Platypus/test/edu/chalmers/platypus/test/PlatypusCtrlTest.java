package edu.chalmers.platypus.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;

import javax.swing.ImageIcon;

import org.junit.Before;
import org.junit.Test;

import edu.chalmers.platypus.ctrl.impl.FilterCtrl;
import edu.chalmers.platypus.ctrl.impl.ImageCtrl;
import edu.chalmers.platypus.ctrl.impl.MiscCtrl;
import edu.chalmers.platypus.ctrl.impl.PresetCtrl;
import edu.chalmers.platypus.ctrl.impl.PreviewCtrl;
import edu.chalmers.platypus.model.BatchImage;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.CtrlLocator;
import edu.chalmers.platypus.util.ModelLocator;

public class PlatypusCtrlTest {

	FilterCtrl filterCtrl;
	MiscCtrl miscCtrl;
	ImageCtrl imageCtrl;
	PresetCtrl presetCtrl;
	PreviewCtrl previewCtrl;
	
	PlatypusModel model;
	
	@Before
	public void setUp() throws Exception {
		model = PlatypusModel.getInstance();
		
		ModelLocator.setModel(model);
		
		miscCtrl = MiscCtrl.getInstance();
        filterCtrl = FilterCtrl.getInstance();
        imageCtrl = ImageCtrl.getInstance();
        presetCtrl = PresetCtrl.getInstance();
        previewCtrl = PreviewCtrl.getInstance();

		CtrlLocator.setFilterCtrl(filterCtrl);
		CtrlLocator.setImageCtrl(imageCtrl);
		CtrlLocator.setPresetCtrl(presetCtrl);
		CtrlLocator.setPreviewCtrl(previewCtrl);
		CtrlLocator.setMiscCtrl(miscCtrl);
	}

	@Test
	public void testGetFilterInstance() {
		assertTrue(FilterCtrl.getInstance() == filterCtrl);
	}
	
	@Test
	public void testGetMiscInstance() {
		assertTrue(MiscCtrl.getInstance() == miscCtrl);
	}
	
	@Test
	public void testGetImageInstance() {
		assertTrue(ImageCtrl.getInstance() == imageCtrl);
	}
	
	@Test
	public void testGetPreviewInstance() {
		assertTrue(PreviewCtrl.getInstance() == previewCtrl);
	}
	
	@Test
	public void testGetPresetInstance() {
		assertTrue(PresetCtrl.getInstance() == presetCtrl);
	}

	@Test
	public void testResetModel() {
		filterCtrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		filterCtrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		imageCtrl.addImageToBatch(new File(""));
		imageCtrl.addImageToBatch(new File(""));

		miscCtrl.resetModel();
		assertTrue(model.getBatchSize() == 0 && model.getActiveFilters().getList().size() == 0);
	}

	@Test
	public void testAddImageToBatch() {
		int nrOfImagesBefore = model.getBatchSize();
		imageCtrl.addImageToBatch(new File(""));
		int nrOfImagesAfter = model.getBatchSize();

		assertTrue(nrOfImagesBefore+1 == nrOfImagesAfter);
	}

	@Test
	public void testRemoveImageFromBatch() {
		int nrOfImagesBefore = model.getBatchSize();
		
		BatchImage img = DummyImageFactory.getRandomDummyBatchImage();
		model.getImageBatch().add(img);
		imageCtrl.removeImageFromBatch(img);
		
		int nrOfImagesAfter = model.getBatchSize();

		assertTrue(nrOfImagesBefore == nrOfImagesAfter);
	}

	@Test
	public void testClearImageBatch() {
		imageCtrl.addImageToBatch(new File(""));
		imageCtrl.addImageToBatch(new File(""));

		imageCtrl.clearImageBatch();
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
		
		imageCtrl.saveImages(folder.toString(), "jpg");
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
		
		imageCtrl.saveImages(folder.getPath(), "jpg");
		
		int sizeBefore = folder.listFiles().length;
		imageCtrl.abortSaveOperation();
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
		int sizeBefore = filterCtrl.getLoadedFilterList().size();
		model.getFilterContainer().addFilter(DummyFilterFactory.getRandomDummyFilter());
		int sizeAfter = filterCtrl.getLoadedFilterList().size();
		
		assertTrue(sizeBefore+1 == sizeAfter);
	}

	@Test
	public void testAddFilterToBatch() {
		int sizeBefore = model.getActiveFilters().getList().size();
		filterCtrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		
		int sizeAfter = model.getActiveFilters().getList().size();

		assertTrue(sizeBefore+1 == sizeAfter);
	}

	@Test
	public void testRemoveFilterFromBatch() {
		IFilter f = DummyFilterFactory.getRandomDummyFilter();
		filterCtrl.addFilterToBatch(f);
		filterCtrl.addFilterToBatch(DummyFilterFactory.getRandomDummyFilter());
		filterCtrl.removeFilterFromBatch(f);

		assertFalse(model.getActiveFilters().getList().contains(f));
	}

	@Test
	public void testImportNewFilter() {
		File f = new File("test/resources/DummyFilter.jar");
		filterCtrl.importNewFilter(f);
		
		File filterFile = new File(System.getProperty("user.home")+File.separator+"PlatyPix"+File.separator+"Filters"+File.separator+"DummyFilter.jar");
		
		assertTrue(filterFile.exists());
		
		filterFile.deleteOnExit();
	}

	@Test
	public void testSetNewPreview() {
		BatchImage previewBefore = model.getPreview();
		
		BatchImage previewToAdd = DummyImageFactory.getRandomDummyBatchImage();
		previewCtrl.setNewPreview(previewToAdd);
		BatchImage previewAfter = model.getPreview();

		assertTrue(previewBefore != previewAfter && previewToAdd == previewAfter);
	}

	@Test
	public void testGetPreviewOriginal() {
		File f = new File("test/resources/testimage.jpg");
		BatchImage testImg = new BatchImage(f);
		previewCtrl.setNewPreview(testImg);
		
		int width = (int)(Math.random()*100)+1;
		int height = (int)(Math.random()*100)+1;
		
		ImageIcon preview = previewCtrl.getPreviewOriginal(width, height);
		assertTrue(preview.getIconWidth() == width || preview.getIconHeight() == height);
	}

	@Test
	public void testGetPreviewFiltered() {
		File f = new File("test/resources/testimage.jpg");
		BatchImage testImg = new BatchImage(f);
		previewCtrl.setNewPreview(testImg);
		
		int width = (int)(Math.random()*100)+1;
		int height = (int)(Math.random()*100)+1;
		
		ImageIcon preview = previewCtrl.getPreviewOriginal(width, height);
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
		
		assertTrue(presetCtrl.getLoadedPresetList().size() == numberOfPresetFiles);
	}

	@Test
	public void testLoadPreset() {
		if (presetCtrl.getLoadedPresetList().size() == 0) {
			fail("Create a preset manually from the application.");
		} else {
			model.getActiveFilters().getList().clear();
			Preset p = presetCtrl.getLoadedPresetList().get(0);
			int numberOfFilters = p.getFilters().length;
			
			presetCtrl.loadPreset(p);
			assertTrue(model.getActiveFilters().getList().size() == numberOfFilters);
		}
	}

	@Test
	public void testSavePreset() {
		for (IFilter filter : model.getFilterContainer().getList()) {
			model.getActiveFilters().getList().add(filter);
		}
		final String name = "TestPreset ID"+(int)(Math.random()*10);
		presetCtrl.savePreset(name);
		
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
