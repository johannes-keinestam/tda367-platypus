package edu.chalmers.platypus.test;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import edu.chalmers.platypus.model.IFilter;

public class DummyFilterFactory {
	public static IFilter getRandomDummyFilter() {
		return new IFilter() {
			
			@Override
			public void saveState(String folder) {}
			
			@Override
			public void loadState(String folder) {}
			
			@Override
			public JPanel getPanel() {
				return null;
			}
			
			@Override
			public String getName() {
				return "FilterID "+((Math.random()*100)+(Math.random()*10));
			}
			
			@Override
			public ImageIcon getDescriptiveImage() {
				return null;
			}
			
			@Override
			public String getDescription() {
				return null;
			}
			
			@Override
			public BufferedImage applyFilter(BufferedImage image) {
				return null;
			}
			
			@Override
			public boolean equals(Object o) {
				if (o instanceof IFilter) {
					IFilter other = (IFilter)o;
					return this.getName().equals(other.getName());
				}
				return false;
			}
		};
	}
}
