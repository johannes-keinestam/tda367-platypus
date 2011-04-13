package edu.chalmers.platypus.model;


import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FilterClass implements IFilter{
	JPanel panel;
	
	public FilterClass(){
		panel = new CtrlPanel();
	}
	
	@Override
	public String getName() {
		return "Black and White filter";
	}

	@Override
	public String getDescription() {
		return "Make image black and white.";
	}

	@Override
	public ImageIcon getDescriptiveImage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public BufferedImage applyFilter(BufferedImage image) {
		//remove later
		System.out.println("AplliedFilter");
		ColorConvertOp cco = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);
		return cco.filter(image, null);
   }
 
		
}
