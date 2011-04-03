package model;

import javax.swing.ImageIcon;
import java.awt.Component;
import java.util.HashMap;

public interface Filter {

	public String getName();
	
	public String getDescription();
	
	public void applyFilter();
	
	public ImageIcon getDescriptiveImage();
	
	public HashMap<String,Component> getFilterComponents();
}
