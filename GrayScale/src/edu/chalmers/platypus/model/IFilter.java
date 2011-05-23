package edu.chalmers.platypus.model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public interface IFilter extends Serializable {

	public String getName();

	public String getDescription();

	public BufferedImage applyFilter(BufferedImage image);

	public ImageIcon getDescriptiveImage();

	public JPanel getPanel();
	
	public Object[] getState();
	
	public void setState (Object[] state);
}
