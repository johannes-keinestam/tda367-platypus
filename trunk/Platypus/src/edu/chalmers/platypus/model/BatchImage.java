package edu.chalmers.platypus.model;

import java.awt.Image;
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

	public ImageIcon getThumbnail(int width, int height) {
		try {
			if(width == -1 && height == -1){
				try{
                    return new ImageIcon(imageFile.toURI().toURL());
				} catch (MalformedURLException e) {
                    e.printStackTrace();
                    return null;
				}
			}
			
			BufferedImage unscaledImage = ImageIO.read(imageFile);
			
			int unscaledHeight = unscaledImage.getHeight();
			int unscaledWidth = unscaledImage.getWidth();
			
			int imageRatio = unscaledHeight/unscaledWidth;
			
//			int scaledHeight = 0;
//			int scaledWidth = 0;
//			
//			if(imageRatio>=1){
//				double scale = (double)unscaledHeight/height;
//				scaledHeight = height;
//				scaledWidth = (int)width*scale;
//			}else{
//				double scale = (double)unscaledWidth/width;
//				scaledHeight = height*scale;
//				scaledWidth = width;
//			}
			
			if(imageRatio>=1){
				return new ImageIcon(unscaledImage.getScaledInstance(-1,height,Image.SCALE_FAST));
			}else{
				return new ImageIcon(unscaledImage.getScaledInstance(width,-1,Image.SCALE_FAST));
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
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
		System.out.println(fileName.substring(fileName.lastIndexOf(File.separator)+1, fileName.lastIndexOf('.')));
		return fileName.substring(fileName.lastIndexOf(File.separator)+1, fileName.lastIndexOf('.'));
	}
}

