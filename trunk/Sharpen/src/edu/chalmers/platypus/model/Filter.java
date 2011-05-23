package edu.chalmers.platypus.model;


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jhlabs.image.ConvolveFilter;
import com.jhlabs.image.GaussianFilter;
import com.jhlabs.image.GrayFilter;
import com.jhlabs.image.GrayscaleColormap;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.SharpenFilter;

public class Filter extends Observable implements IFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2223555253792480696L;
	CtrlPanel panel;
	
	public Filter(){
		panel = new CtrlPanel();
		
	}
	
	@Override
	public String getName() {
		return "Sharpen";
	}

	@Override
	public String getDescription() {
		return "Sharpens the image.";
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
		SharpenFilter sf = new SharpenFilter();
		return sf.filter(image, null);

   }
	@Override
	public Object[] getState() {
		return null;
	}

	@Override
	public void setState(Object[] state) {
		
	}



		
}
