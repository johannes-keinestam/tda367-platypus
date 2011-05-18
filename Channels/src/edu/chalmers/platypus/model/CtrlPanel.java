package edu.chalmers.platypus.model;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CtrlPanel extends JPanel {
	private JSlider sliderAmountB;
	private JSlider sliderAmountG;
	private JSlider sliderAmountR;
	

	/**
	 * Create the panel.
	 */
	public CtrlPanel() {
		setLayout(null);
		
		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	    }
	    catch (ClassNotFoundException e) {
	    }
	    catch (InstantiationException e) {
	    }
	    catch (IllegalAccessException e) {
	    }
		
		setLayout(null);
		setPreferredSize(new Dimension(400, 170));
		
		
		sliderAmountB = new JSlider(-2000, 2000);
		sliderAmountB.setMinorTickSpacing(1);
		sliderAmountB.setToolTipText("Amount of Blue");
		sliderAmountB.setBounds(122, 85, 200, 50);
		add(sliderAmountB);
		
		JLabel lblRG = new JLabel("Blue:");
		lblRG.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRG.setBounds(60, 109, 64, 14);
		lblRG.revalidate();
		add(lblRG);
		
		
		sliderAmountG = new JSlider(-2000, 2000);
		sliderAmountG.setMinorTickSpacing(1);
		sliderAmountG.setToolTipText("Amount of Green");
		sliderAmountG.setBounds(122, 48, 200, 50);
		add(sliderAmountG);
		
		JLabel lblGR = new JLabel("Green:");
		lblGR.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGR.setBounds(60, 73, 64, 14);
		lblGR.revalidate();
		add(lblGR);
		
		
		sliderAmountR = new JSlider(-2000, 2000);
		sliderAmountR.setMinorTickSpacing(1);
		sliderAmountR.setToolTipText("Amount of Red");
		sliderAmountR.setBounds(122, 11, 200, 50);
		add(sliderAmountR);
		
		JLabel lblRB = new JLabel("Red:");
		lblRB.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRB.setBounds(60, 23, 64, 14);
		lblRB.revalidate();
		add(lblRB);
		

	}

	public JSlider getSliderAmountB() {
		return sliderAmountB;
	}


	public JSlider getSliderAmountG() {
		return sliderAmountG;
	}


	public JSlider getSliderAmountR() {
		return sliderAmountR;
	}
}
