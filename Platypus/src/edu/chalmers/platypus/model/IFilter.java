package edu.chalmers.platypus.model;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public interface IFilter {

	public String getName();
	
	public String getDescription();
	
	public BufferedImage applyFilter(BufferedImage image);
	
	public ImageIcon getDescriptiveImage();
	
	public JPanel getPanel();
}
