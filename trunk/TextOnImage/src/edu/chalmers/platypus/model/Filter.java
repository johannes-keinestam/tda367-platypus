package edu.chalmers.platypus.model;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Filter extends Observable implements IFilter {
	/**
	 * 
	 */
	CtrlPanel panel;
	
	public Filter(){
		panel = new CtrlPanel();
		ActionListener ac = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers();
			}
		};
		panel.getTxtText().addActionListener(ac);
		panel.getTextFieldX().addActionListener(ac);
		panel.getTextFieldY().addActionListener(ac);
		panel.getjColor().getSelectionModel().addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				setChanged();
				notifyObservers();
			}
		});
		panel.getCmbBxFonts().addActionListener(ac);
		panel.getCmbBxSize().addActionListener(ac);
		
	}
	
	@Override
	public String getName() {
		return "Text on image";
	}

	@Override
	public String getDescription() {
		return "Writes text on image.";
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
		int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dst = new BufferedImage(
            w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = dst.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.setPaint(panel.getjColor().getColor());
        g2d.setFont(new Font((String) panel.getCmbBxFonts().getSelectedItem(), Font.BOLD, checkSize()));
        String s = panel.getTxtText().getText();
        int x = (int) (dst.getWidth()*checkProcent(panel.getTextFieldX()))/100;
        int y = (int) (dst.getWidth()*checkProcent(panel.getTextFieldY()))/100;
        g2d.drawString(s, x, y);
        g2d.dispose();
        return dst;
        
   }
	private int checkSize(){
	try {
		int size = (Integer) panel.getCmbBxSize().getSelectedItem();
		if(size > 0){
			return size;
		}
		else{
			return 8;
		}
	} catch (Exception e) {
		return 8;
	}	
		
	}
	private int checkProcent(JTextField text){
		try {
			int procent = Integer.parseInt(text.getText());
			if(procent >= 0  && procent <= 100){
				text.setText(procent+"");
				return procent;
			}
			else{
				text.setText(50+"");
				return 50;
			}
		} catch (Exception e) {
			text.setText(50+"");
			return 50;
		}
	}
	@Override
	public Object[] getState() {
		String[] state = new String[6];
		state[0] = Integer.toString(panel.getjColor().getColor().getRGB());
        state[1] = (String) panel.getCmbBxFonts().getSelectedItem().toString();
        state[2] = Integer.toString(checkSize());
        state[3] = panel.getTxtText().getText();
        state[4] = Integer.toString(checkProcent(panel.getTextFieldX()));
        state[5] = Integer.toString(checkProcent(panel.getTextFieldX()));    
		return state;
	}

	@Override
	public void setState(Object[] state) {	
		String[] values =  Arrays.copyOf(state, state.length, String[].class);
		panel.getjColor().setColor(new Color(Integer.parseInt(values[0])));
        panel.getCmbBxFonts().setSelectedItem(values[1]);
        panel.getCmbBxSize().setSelectedItem(Integer.parseInt(values[2]));
        panel.getTxtText().setText(values[3]);
        panel.getTextFieldX().setText(values[4]);
        panel.getTextFieldX().setText(values[5]);
		
	}
		
}
