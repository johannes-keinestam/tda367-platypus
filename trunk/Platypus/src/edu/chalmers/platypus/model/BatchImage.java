package edu.chalmers.platypus.model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class BatchImage {
	private final File imageFile;

	public BatchImage(final File file) {
		this.imageFile = file;
	}

	public ImageIcon getThumbnail() {
		try {
			// TODO scale
			ImageIcon img = new ImageIcon(imageFile.toURI().toURL());
			return img;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public BufferedImage getImage() {
		try {
			return ImageIO.read(imageFile);
		} catch (final MalformedURLException e) {		
			e.printStackTrace();
			return null;
		} catch (final IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getFileName() {
		String fileName = imageFile.getPath();
		System.out.println(fileName);
		return fileName.substring(fileName.lastIndexOf(File.separator)+1, fileName.length());
	}
}

