package edu.chalmers.platypus.model;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Component;
import java.util.HashMap;

public interface IFilter {

	public String getName();
	
	public String getDescription();
	
	public void applyFilter();
	
	public ImageIcon getDescriptiveImage();
	
	public JPanel getPanel();
}
