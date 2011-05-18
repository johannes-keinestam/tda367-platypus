package edu.chalmers.platypus.model;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CtrlPanel extends JPanel {
	private JSlider sliderAmount;
	

	/**
	 * Create the panel.
	 */
	public CtrlPanel() {
		setLayout(null);
		setPreferredSize(new Dimension(400, 130));
		
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
		
		
		sliderAmount = new JSlider(0, 20);
		sliderAmount.setPaintTicks(true);
		sliderAmount.setMinorTickSpacing(1);
		sliderAmount.setToolTipText("Amount of blur");
		sliderAmount.setBounds(136, 29, 200, 50);
		add(sliderAmount);
		
		
		JLabel lblBluriness = new JLabel("Bluriness:");
		lblBluriness.setHorizontalAlignment(SwingConstants.RIGHT);
		lblBluriness.setBounds(56, 47, 70, 14);
		
		lblBluriness.revalidate();
		
		add(lblBluriness);

		


	}


	public JSlider getSliderAmount() {
		return sliderAmount;
	}
}
