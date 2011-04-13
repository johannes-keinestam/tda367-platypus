package edu.chalmers.platypus.model;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;

public class CtrlPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CtrlPanel() {
		setLayout(null);
		
		JLabel lblBlackAndWhite = new JLabel("Black and White filter");
		lblBlackAndWhite.setHorizontalAlignment(SwingConstants.CENTER);
		lblBlackAndWhite.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBlackAndWhite.setBounds(0, 11, 364, 77);
		add(lblBlackAndWhite);

	}
}
