package edu.chalmers.platypus.model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import com.jhlabs.image.CropFilter;


public class Filter extends Observable implements IFilter {
	private CtrlPanel panel;
	
	public Filter(){
		panel = new CtrlPanel();
		ActionListener al = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers();
				
			}
		};
		panel.getTextFieldH().addActionListener(al);
		panel.getTextFieldW().addActionListener(al);
		panel.getTextFieldX().addActionListener(al);
		panel.getTextFieldY().addActionListener(al);
	}
	
	@Override
	public String getName() {
		return "Crop";
	}

	@Override
	public String getDescription() {
		return "Crops image";
	}

	@Override
	public ImageIcon getDescriptiveImage() {
		return null;
	}

	@Override
	public JPanel getPanel() {
		return panel;
	}

	@Override
	public BufferedImage applyFilter(BufferedImage image) {
		int x = checkX(panel.getTextFieldX().getText(), image);
		int y = checkY(panel.getTextFieldY().getText(), image);
		CropFilter cf = new CropFilter(x, y, 
				checkW(panel.getTextFieldW().getText(),x , image),checkH(panel.getTextFieldH().getText(),y , image));
		return cf.filter(image, null);
   }
	
	private int checkH(String str, int y, BufferedImage image){
		try {
			int size = (Integer.parseInt(str));
			if(size > 0 && size < 100 && image.getHeight()*size/100<image.getHeight()-y){
				return image.getHeight()*size/100;
			}
			else{
				return image.getHeight()-y;
			}
		} catch (Exception e) {
			return image.getHeight();
		}	
			
		}
	private int checkY(String str, BufferedImage image){
		try {
			int size = (Integer.parseInt(str));
			if(size >= 0 && size<101){
				return image.getHeight()*size/100;
			}
			else{
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}	
			
		}
	private int checkW(String str, int x, BufferedImage image){
		try {
			int size = (Integer.parseInt(str));
			if(size >= 0 && size<101 &&  image.getWidth()*size/100 < image.getWidth()-x){
				return image.getWidth()*size/100;
			}
			else{
				return image.getWidth()-x;
			}
		} catch (Exception e) {
			return image.getWidth()-x;
		}	
			
		}
	private int checkX(String str, BufferedImage image){
		try {
			int size = (Integer.parseInt(str));
			if(size >= 0 && size<100){
				return image.getWidth()*size/100;
			}
			else{
				return 0;
			}
		} catch (Exception e) {
			return 0;
		}	
			
		}

	@Override
	public Object[] getState() {
		 String[] state = new String[4];
		 state[0] = panel.getTextFieldX().getText();
		 state[1] = panel.getTextFieldY().getText();
		 state[2] = panel.getTextFieldW().getText();
		 state[3] = panel.getTextFieldH().getText();
		return state;
	}

	@Override
	public void setState(Object[] state) {
		 String[] values = Arrays.copyOf(state, state.length, String[].class);
		 panel.getTextFieldX().setText(values[0]);
		 panel.getTextFieldY().setText(values[1]);
		 panel.getTextFieldW().setText(values[2]);
		 panel.getTextFieldH().setText(values[3]);
		
	}
		
}
