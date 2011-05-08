package edu.chalmers.platypus.model;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.util.Observable;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class FilterClass extends Observable implements IFilter {
	CtrlPanel panel;
	
	public FilterClass(){
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
		//remove later
		System.out.println("AplliedFilter");
		int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage img = new BufferedImage(
            w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = img.createGraphics();
        g2d.drawImage(image, 0, 0, null);
        g2d.setPaint(panel.getjColor().getColor());
        g2d.setFont(new Font((String) panel.getCmbBxFonts().getSelectedItem(), Font.BOLD, checkSize()));
        String s = panel.getTxtText().getText();
        FontMetrics fm = g2d.getFontMetrics();
        int x = (int) (img.getWidth()*checkProcent(panel.getTextFieldX()))/100;
        int y = (int) (img.getWidth()*checkProcent(panel.getTextFieldY()))/100;
        g2d.drawString(s, x, y);
        g2d.dispose();
        return img;
        
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

		
}
