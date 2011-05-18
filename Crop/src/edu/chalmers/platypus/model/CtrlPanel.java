package edu.chalmers.platypus.model;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CtrlPanel extends JPanel {
	private JTextField textFieldX;
	private JTextField textFieldY;
	private JTextField textFieldH;
	private JTextField textFieldW;
	/**
	 * Create the panel.
	 */
	public CtrlPanel() {
		
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
		setPreferredSize(new Dimension(400, 150));
		
		JLabel lblX = new JLabel("X:(%)");
		lblX.setHorizontalAlignment(SwingConstants.RIGHT);
		lblX.setBounds(10, 11, 46, 14);
		add(lblX);
		
		JLabel lblY = new JLabel("Y:(%)");
		lblY.setHorizontalAlignment(SwingConstants.RIGHT);
		lblY.setBounds(10, 36, 46, 14);
		add(lblY);
		
		textFieldX = new JTextField();
		textFieldX.setBounds(66, 8, 46, 20);
		add(textFieldX);
		textFieldX.setColumns(10);
		
		textFieldY = new JTextField();
		textFieldY.setColumns(10);
		textFieldY.setBounds(66, 33, 46, 20);
		add(textFieldY);
		
		textFieldH = new JTextField();
		textFieldH.setColumns(10);
		textFieldH.setBounds(207, 33, 45, 20);
		add(textFieldH);
		
		textFieldW = new JTextField();
		textFieldW.setColumns(10);
		textFieldW.setBounds(207, 8, 45, 20);
		add(textFieldW);
		
		JLabel lblHeight = new JLabel("Height:(%)");
		lblHeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHeight.setBounds(122, 36, 74, 14);
		add(lblHeight);
		
		JLabel lblWidth = new JLabel("Width:(%)");
		lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWidth.setBounds(122, 11, 74, 14);
		add(lblWidth);

	}
	public JTextField getTextFieldX() {
		return textFieldX;
	}
	public JTextField getTextFieldY() {
		return textFieldY;
	}
	public JTextField getTextFieldH() {
		return textFieldH;
	}
	public JTextField getTextFieldW() {
		return textFieldW;
	}
}
