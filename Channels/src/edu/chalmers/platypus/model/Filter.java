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

import com.jhlabs.image.ChannelMixFilter;


public class Filter extends Observable implements IFilter {
	CtrlPanel panel;
	
	public Filter(){
		panel = new CtrlPanel();
		ChangeListener cl = new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				setChanged();
				notifyObservers();
			}
		};
		panel.getSliderAmountB().addChangeListener(cl);
		panel.getSliderAmountG().addChangeListener(cl);
		panel.getSliderAmountR().addChangeListener(cl);
	}
	
	@Override
	public String getName() {
		return "Channels";
	}

	@Override
	public String getDescription() {
		return "Change amount of red, green and blue.";
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
		ChannelMixFilter cf = new ChannelMixFilter();
		cf.setIntoB(panel.getSliderAmountB().getValue());
		cf.setIntoG(panel.getSliderAmountG().getValue());
		cf.setIntoR(panel.getSliderAmountR().getValue());
		return cf.filter(image, image);
   }

	@Override
	public void saveState(String folder) {
		try {
			FileOutputStream fos = new FileOutputStream(System.getenv("USERPROFILE")+"/PlatyPix/Presets/"+folder+"/"+getName());
			ObjectOutputStream oos;
			try {
				 int[] state = {panel.getSliderAmountR().getValue(), panel.getSliderAmountG().getValue(),
						 panel.getSliderAmountB().getValue()};
				 
				 oos = new ObjectOutputStream(fos);
				 oos.writeObject(state);
		         oos.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
			catch (NullPointerException e){
				e.printStackTrace();
			}
		}
		catch (FileNotFoundException e) {	
		}
		
	}

	@Override
	public void loadState(String folder) {
		FileInputStream fis;

		try {
			fis = new FileInputStream(System.getenv("USERPROFILE")
					+ "/PlatyPix/Presets/" + folder + "/"
					+ getName());
			try {
				ObjectInputStream ois = new ObjectInputStream(fis);
				try {
					int[] state = (int[]) ois.readObject();
					panel.getSliderAmountR().setValue(state[0]);
					panel.getSliderAmountG().setValue(state[1]);
					panel.getSliderAmountB().setValue(state[2]);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

		
}
