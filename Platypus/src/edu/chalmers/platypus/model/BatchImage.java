package edu.chalmers.platypus.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BatchImage {
	private final URI imageURI;

	public BatchImage(final File file) {
		this.imageURI = file.toURI();
	}

	public ImageIcon getThumbnail() {
		try {
			return new ImageIcon(imageURI.toURL());
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public BufferedImage getImage() {
		try {
			return ImageIO.read(imageURI.toURL());
		} catch (final MalformedURLException e) {		
			e.printStackTrace();
			return null;
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}

