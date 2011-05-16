package edu.chalmers.platypus.ctrl;

import java.io.File;
import edu.chalmers.platypus.model.BatchImage;

public interface IImageCtrl {
	public void addImageToBatch(File file);

	public void removeImageFromBatch(BatchImage img);

	public void saveImages(String path, String ext);

	public void abortSaveOperation();
}
