package edu.chalmers.platypus.model;


import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import com.jhlabs.image.GaussianFilter;

public class Filter extends Observable implements IFilter {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2223555253792480696L;
	CtrlPanel panel;
	
	public Filter(){
		panel = new CtrlPanel();
		panel.getSliderAmount().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				setChanged();
				notifyObservers();
				
			}
		});
		
	}
	
	@Override
	public String getName() {
		return "Blur";
	}

	@Override
	public String getDescription() {
		return "Blurs image.";
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
		GaussianFilter gf = new GaussianFilter(panel.getSliderAmount().getValue());
		return gf.filter(image, image);

   }
	@Override
	public Object[] getState() {
		Integer[] state = new Integer[1];
		state[0] =  panel.getSliderAmount().getValue();
		return state;
	}

	@Override
	public void setState(Object[] state) {
		Integer[] value = Arrays.copyOf(state, state.length, Integer[].class);
		panel.getSliderAmount().setValue(value[0]);
	}





		
}
