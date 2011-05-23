package edu.chalmers.platypus.model;

import java.awt.Dimension;
import java.awt.GraphicsEnvironment;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CtrlPanel extends JPanel {
	private JTextField txtText;
	private JColorChooser jColor;
	private JComboBox cmbBxFonts;
	private JComboBox cmbBxSize;
	private JTextField textFieldX;
	private JTextField textFieldY;

	public JTextField getTxtText() {
		return txtText;
	}

	/**
	 * Create the panel.
	 */
	public CtrlPanel() {
		setLayout(null);
		
		try {
		    // Set System L&F
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
	    }
	    catch (InstantiationException e) {
	       // handle exception
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
	    }
		
		setLayout(null);
		setPreferredSize(new Dimension(575, 350));
		
		txtText = new JTextField();
		txtText.setText("Text...");
		txtText.setBounds(10, 11, 166, 20);
		add(txtText);
		txtText.setColumns(10);
		
		jColor = new JColorChooser();
		jColor.setBounds(10, 42, 500, 300);
		jColor.setVisible(true);
		add(jColor);
		
		cmbBxFonts = new JComboBox();
		cmbBxFonts.setBounds(186, 11, 119, 20);
		String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
		    for ( int i = 0; i < fonts.length; i++ )
		    {
		    	cmbBxFonts.addItem(fonts[i]);
		    }
		add(cmbBxFonts);
		
		cmbBxSize = new JComboBox();
		cmbBxSize.setEditable(true);
		cmbBxSize.setBounds(315, 11, 72, 20);
		cmbBxSize.addItem(8);
		cmbBxSize.addItem(10);
		cmbBxSize.addItem(12);
		cmbBxSize.addItem(14);
		cmbBxSize.addItem(16);
		cmbBxSize.addItem(20);
		cmbBxSize.addItem(24);
		cmbBxSize.addItem(30);
		cmbBxSize.addItem(48);
		cmbBxSize.addItem(72);
		add(cmbBxSize);
		
		JLabel lblX = new JLabel("X:(%)");
		lblX.setBounds(397, 14, 29, 14);
		add(lblX);
		
		textFieldX = new JTextField();
		textFieldX.setText("50%");
		textFieldX.setBounds(436, 11, 36, 20);
		add(textFieldX);
		textFieldX.setColumns(10);
		
		textFieldY = new JTextField();
		textFieldY.setText("50%");
		textFieldY.setColumns(10);
		textFieldY.setBounds(521, 11, 36, 20);
		add(textFieldY);
		
		JLabel lblY = new JLabel("Y:(%)");
		lblY.setBounds(482, 14, 29, 14);
		add(lblY);
		

	}

	public JTextField getTextFieldX() {
		return textFieldX;
	}

	public JTextField getTextFieldY() {
		return textFieldY;
	}

	public JComboBox getCmbBxSize() {
		return cmbBxSize;
	}

	public JColorChooser getjColor() {
		return jColor;
	}
	public JComboBox getCmbBxFonts() {
		return cmbBxFonts;
	}
}
