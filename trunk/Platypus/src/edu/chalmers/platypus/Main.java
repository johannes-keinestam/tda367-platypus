package edu.chalmers.platypus;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.chalmers.platypus.ctrl.PlatypusCtrl;
import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.PlatypusModel;
import edu.chalmers.platypus.view.PlatypusGUI;
import edu.chalmers.platypus.view.gui.PlatypusApp;
/**
 *
 * @author skoldator
 */
public class Main {
    public static void main(String[] args) {
        PlatypusModel model = PlatypusModel.getInstance();
        PlatypusCtrl ctrl = PlatypusCtrl.getInstance();
        PlatypusGUI gui = PlatypusGUI.getInstance();
    	
    	Locator.setModel(model);
    	Locator.setCtrl(ctrl);
    	
    	addImgTest();
    }
    
    private static void addImgTest() {
    	Locator.getCtrl().addFilter(new IFilter() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "Filter 1";
			}
			
			@Override
			public ImageIcon getDescriptiveImage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "Filter 1 är awesome är gör asfräcka saker. Jag svär alltså.";
			}
			
			@Override
			public BufferedImage applyFilter(BufferedImage image) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public JPanel getPanel() {
				// TODO Auto-generated method stub
				return null;
			}
		});
    	Locator.getCtrl().addFilter(new IFilter() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "Filter 2 - blabla";
			}
			
			@Override
			public ImageIcon getDescriptiveImage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "Filter 2 är inte lika bra. Fuckin' shit sucks.";
			}

			@Override
			public BufferedImage applyFilter(BufferedImage image) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public JPanel getPanel() {
				// TODO Auto-generated method stub
				return null;
			}
		});
    	Locator.getCtrl().addFilter(new IFilter() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "Filter 3";
			}
			
			@Override
			public ImageIcon getDescriptiveImage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "Filter 1 är awesome är gör asfräcka saker. Jag svär alltså.";
			}
			
			@Override
			public BufferedImage applyFilter(BufferedImage image) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public JPanel getPanel() {
				// TODO Auto-generated method stub
				return null;
			}
		});
    	Locator.getCtrl().addFilter(new IFilter() {
			
			@Override
			public String getName() {
				// TODO Auto-generated method stub
				return "Filter 4";
			}
			
			@Override
			public ImageIcon getDescriptiveImage() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public String getDescription() {
				// TODO Auto-generated method stub
				return "Filter 1 är awesome är gör asfräcka saker. Jag svär alltså.";
			}
			
			@Override
			public BufferedImage applyFilter(BufferedImage image) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public JPanel getPanel() {
				// TODO Auto-generated method stub
				return null;
			}
		});
    }
}
