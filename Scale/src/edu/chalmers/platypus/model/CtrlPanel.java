package edu.chalmers.platypus.model;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class CtrlPanel extends JPanel {
	private JTextField textFieldH;
	private JTextField textFieldW;
	private JTextField textFieldWP;
	private JTextField textFieldHP;
	private JComboBox comboBox;
	private JLabel lblReso;
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
		setPreferredSize(new Dimension(400, 160));
		
		textFieldH = new JTextField();
		textFieldH.setBounds(70, 11, 46, 20);
		add(textFieldH);
		textFieldH.setColumns(10);
		
		textFieldW = new JTextField();
		textFieldW.setBounds(70, 42, 46, 20);
		add(textFieldW);
		textFieldW.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Height:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(14, 14, 46, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Width:");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(14, 45, 46, 14);
		add(lblNewLabel_1);
		
		textFieldWP = new JTextField();
		textFieldWP.setColumns(10);
		textFieldWP.setBounds(200, 42, 46, 20);
		add(textFieldWP);
		
		JLabel lblWidth = new JLabel("Width:(%)");
		lblWidth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWidth.setBounds(126, 45, 64, 14);
		add(lblWidth);
		
		JLabel lblHieght = new JLabel("Hieght:(%)");
		lblHieght.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHieght.setBounds(126, 14, 64, 14);
		add(lblHieght);
		
		textFieldHP = new JTextField();
		textFieldHP.setColumns(10);
		textFieldHP.setBounds(200, 11, 46, 20);
		add(textFieldHP);
		
		comboBox = new JComboBox();
		comboBox.setBounds(268, 11, 75, 20);
		comboBox.addItem("640x480");
		comboBox.addItem("800x600");
		comboBox.addItem("1024x768");
		comboBox.addItem("1280x800");
		comboBox.addItem("1280x1024");
		comboBox.addItem("1600x1200");
		comboBox.addItem("1920x1080");
		comboBox.addItem("1920x1200");
		comboBox.addItem("2560x1440");
		comboBox.addItem("2560x1600");
		
		add(comboBox);
		
		ButtonGroup bg = new ButtonGroup();
		
		JRadioButton rdbtnPixels = new JRadioButton("Pixels");
		rdbtnPixels.setBounds(14, 95, 109, 23);
		add(rdbtnPixels);
		
		JRadioButton rdbtnProcent = new JRadioButton("Procent");
		rdbtnProcent.setBounds(137, 95, 109, 23);
		add(rdbtnProcent);
		
		JRadioButton rdbtnFixed = new JRadioButton("Fixed");
		rdbtnFixed.setBounds(268, 95, 109, 23);
		add(rdbtnFixed);
		bg.add(rdbtnFixed);
		bg.add(rdbtnProcent);
		bg.add(rdbtnPixels);
		
		JLabel lblResult = new JLabel("Result:");
		lblResult.setHorizontalAlignment(SwingConstants.RIGHT);
		lblResult.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblResult.setBounds(14, 135, 64, 14);
		add(lblResult);
		
		lblReso = new JLabel("");
		lblReso.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblReso.setBounds(88, 137, 289, 14);
		add(lblReso);
		
		comboBox.setEnabled(true);
		textFieldH.setEnabled(false);
		textFieldW.setEnabled(false);
		textFieldHP.setEnabled(false);
		textFieldWP.setEnabled(false);
		
		rdbtnFixed.setSelected(true);
		
		rdbtnPixels.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				textFieldH.setEnabled(true);
				textFieldW.setEnabled(true);
				textFieldHP.setEnabled(false);
				textFieldWP.setEnabled(false);
				
			}
		});
		rdbtnProcent.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(false);
				textFieldH.setEnabled(false);
				textFieldW.setEnabled(false);
				textFieldHP.setEnabled(true);
				textFieldWP.setEnabled(true);
				
			}
		});
		rdbtnFixed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				comboBox.setEnabled(true);
				textFieldH.setEnabled(false);
				textFieldW.setEnabled(false);
				textFieldHP.setEnabled(false);
				textFieldWP.setEnabled(false);
				
			}
		});

	}
	public void loadState(int op){
		switch (op) {
		case 1:
			comboBox.setEnabled(false);
			textFieldH.setEnabled(true);
			textFieldW.setEnabled(true);
			textFieldHP.setEnabled(false);
			textFieldWP.setEnabled(false);
			break;
		case 2:
			comboBox.setEnabled(false);
			textFieldH.setEnabled(false);
			textFieldW.setEnabled(false);
			textFieldHP.setEnabled(true);
			textFieldWP.setEnabled(true);
			break;
		case 3:
			comboBox.setEnabled(true);
			textFieldH.setEnabled(false);
			textFieldW.setEnabled(false);
			textFieldHP.setEnabled(false);
			textFieldWP.setEnabled(false);
			break;

		default:
			break;
		}
	}
	
	public JTextField getTextFieldH() {
		return textFieldH;
	}
	public JTextField getTextFieldW() {
		return textFieldW;
	}
	public JTextField getTextFieldHP() {
		return textFieldHP;
	}
	public JTextField getTextFieldWP() {
		return textFieldWP;
	}
	public JComboBox getComboBox(){
		return comboBox;
	}
	public JLabel getLblReso(){
		return lblReso;
	}
	
}
