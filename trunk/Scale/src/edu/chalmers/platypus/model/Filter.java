package edu.chalmers.platypus.model;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import com.jhlabs.image.ScaleFilter;

public class Filter extends Observable implements IFilter {
	private CtrlPanel panel;
	private int operation = 3;
	
	public Filter(){
		panel = new CtrlPanel();
		ActionListener alP = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				operation = 2;
				
				setChanged();
				notifyObservers();
				
			}
		};
		ActionListener alPx = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				operation = 1;
				
				setChanged();
				notifyObservers();
				
			}
		};
		panel.getComboBox().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				operation = 3;
				
				setChanged();
				notifyObservers();	
			}
		});
		panel.getTextFieldH().addActionListener(alPx);
		panel.getTextFieldW().addActionListener(alPx);
		panel.getTextFieldHP().addActionListener(alP);
		panel.getTextFieldWP().addActionListener(alP);
	}
	
	@Override
	public String getName() {
		return "Scale";
	}

	@Override
	public String getDescription() {
		return "Scales image";
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
		ScaleFilter sf = null;
		int w = image.getWidth();
		int h = image.getHeight();
		BufferedImage bf = null;
		switch (operation) {
		case 1:
			try {
				h = Integer.parseInt(panel.getTextFieldH().getText());
				w = Integer.parseInt(panel.getTextFieldW().getText());
			} catch (NumberFormatException e) {
			}
			sf = new ScaleFilter(w, h);
			panel.getLblReso().setText(w+"x"+h);
			bf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			break;
		case 2:
			try {
				h = Integer.parseInt(panel.getTextFieldHP().getText());
				w = Integer.parseInt(panel.getTextFieldWP().getText());
			} catch (NumberFormatException e) {
			}
			sf = new ScaleFilter(image.getWidth()*w/100, image.getHeight()*h/100);
			panel.getLblReso().setText(+ image.getWidth()*w/100+"x"+image.getHeight()*h/100);
			bf = new BufferedImage(image.getWidth()*w/100, image.getHeight()*h/100, BufferedImage.TYPE_INT_RGB);
			break;
		case 3:
			String reso = (String) panel.getComboBox().getSelectedItem();
			int index = reso.lastIndexOf("x");
			String wS = reso.substring(0, index);
			String hS = reso.substring(index+1);
			
			w = Integer.parseInt(wS);
			h = Integer.parseInt(hS);
			
			sf = new ScaleFilter(w, h);
			panel.getLblReso().setText(Integer.parseInt(wS)+"x"+ Integer.parseInt(hS));
			bf = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
			break;

		default:
			sf = new ScaleFilter(100, 100);
			break;
		}
		
		sf.filter(image, bf);
		return bf;
   }

	@Override
	public void saveState(String folder) {
		try {
			FileOutputStream fos = new FileOutputStream(System.getenv("USERPROFILE")+"/PlatyPix/Presets/"+folder+"/"+getName());
			ObjectOutputStream oos;
			try {
				String[] state = new String[6];
				state[0] = panel.getTextFieldW().getText();
				state[1] = panel.getTextFieldH().getText();
				state[2] = panel.getTextFieldWP().getText();
				state[3] = panel.getTextFieldHP().getText();
				state[4] = (String) panel.getComboBox().getSelectedItem();
				state[5] = Integer.toString(operation);
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
					String[] state = (String[]) ois.readObject();
					panel.getTextFieldW().setText(state[0]);
					panel.getTextFieldH().setText(state[1]);
					panel.getTextFieldWP().setText(state[2]);
					panel.getTextFieldHP().setText(state[3]);
					panel.getComboBox().setSelectedItem(state[4]);
					operation = Integer.parseInt(state[5]);
					panel.loadState(operation);

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
