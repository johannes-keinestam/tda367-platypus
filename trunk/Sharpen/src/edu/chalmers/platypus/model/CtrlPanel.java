package edu.chalmers.platypus.model;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Font;

public class CtrlPanel extends JPanel {
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
		
		JLabel lblSharpen = new JLabel("Sharpen");
		lblSharpen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSharpen.setBounds(10, 11, 109, 41);
		lblSharpen.revalidate();
		
		add(lblSharpen);

	}


}
