package edu.chalmers.platypus.util.interfaces;

import java.io.File;

import edu.chalmers.platypus.model.BatchImage;

public interface IImageCtrl {
    public void addImageToBatch(File file);

    public void removeImageFromBatch(BatchImage img);

    public void clearImageBatch();

    public void saveImages(String path, String ext);

    public void abortSaveOperation();
}
