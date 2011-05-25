package edu.chalmers.platypus.model;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

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
		
		JLabel lbl = new JLabel("Grayscale");
		lbl.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl.setBounds(10, 11, 109, 41);
		lbl.revalidate();
		
		add(lbl);

	}


}
