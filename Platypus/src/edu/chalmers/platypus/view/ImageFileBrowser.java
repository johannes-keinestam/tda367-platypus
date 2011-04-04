package edu.chalmers.platypus.view;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;


public class ImageFileBrowser extends JFileChooser implements PropertyChangeListener, ItemListener {
	private final JPanel previewPanel = new JPanel();
	private final JLabel previewLabel = new JLabel();
	private final JCheckBox previewCheckBox = new JCheckBox("Preview");

	private static final ImageIcon noPreview = new ImageIcon("src/platypus/gui/resources/emptyChoice.png");
	private static final int PREFERRED_WIDTH = 240;
	private static final int PREFERRED_HEIGHT = 180;
	
	public ImageFileBrowser() {
		super();
		initializePreview();
		
		super.addPropertyChangeListener(this);
	}

	private void initializePreview() {
		previewLabel.setVerticalAlignment(JLabel.CENTER);
		previewLabel.setHorizontalAlignment(JLabel.CENTER);
		previewLabel.setPreferredSize(new Dimension(PREFERRED_WIDTH, PREFERRED_HEIGHT));
		previewLabel.setIcon(noPreview);
		
		JPanel checkBoxPanel = new JPanel();
		previewCheckBox.setSelected(true);
		previewCheckBox.setHorizontalAlignment(SwingConstants.RIGHT);
		previewCheckBox.addItemListener(this);
		checkBoxPanel.add(previewCheckBox);
		
		previewPanel.setLayout(new BoxLayout(previewPanel, BoxLayout.Y_AXIS));
		previewPanel.add(checkBoxPanel);
		previewPanel.add(previewLabel);
		
		super.setAccessory(previewPanel);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent pce) {
		if (previewCheckBox.isSelected()) {
			if (pce.getPropertyName().equals(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)) {
				File chosenFile = (File)pce.getNewValue();
				if (chosenFile != null && chosenFile.isFile()) {
					ImageIcon thumbPreview = new ImageIcon(chosenFile.getPath());
					if (thumbPreview.getIconWidth() != -1) {
						if (thumbPreview.getIconWidth() > PREFERRED_WIDTH || thumbPreview.getIconHeight() > PREFERRED_HEIGHT) {
							float aspectRatio = (float)thumbPreview.getIconWidth()/(float)thumbPreview.getIconHeight();
							thumbPreview = new ImageIcon(thumbPreview.getImage()
									.getScaledInstance(aspectRatio >= 1 ? PREFERRED_WIDTH : (int)(PREFERRED_HEIGHT*aspectRatio), 
											aspectRatio >= 1 ? (int)(PREFERRED_WIDTH/aspectRatio) : PREFERRED_HEIGHT, Image.SCALE_FAST));
						}
						previewLabel.setIcon(thumbPreview);
						return;	
					}
				}
				previewLabel.setIcon(noPreview);
			}
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.DESELECTED) {
			previewLabel.setVisible(false);
		} else {
			previewLabel.setIcon(noPreview);
			previewLabel.setVisible(true);
		}
		
	}
}
