package edu.chalmers.platypus.util.interfaces;

import javax.swing.ImageIcon;
import edu.chalmers.platypus.model.BatchImage;

public interface IPreviewCtrl {
	public void setNewPreview(BatchImage preview);

	public ImageIcon getPreviewOriginal(int width, int height);

	public ImageIcon getPreviewFiltered(int width, int height);

	public void previewChanged();
}
