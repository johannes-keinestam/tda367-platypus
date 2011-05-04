package edu.chalmers.platypus.model;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;

public class BatchImage {
	private final File imageFile;

	public BatchImage(final File file) {
		this.imageFile = file;
	}

	public BufferedImage getThumbnail(int width, int height) {
		try {

                        //Rough scaling
			if(width == -1 && height == -1){
                            return getImage();
                        }
			
			ImageInputStream iis = ImageIO.createImageInputStream(imageFile);
			Iterator readers = ImageIO.getImageReaders(iis);
			ImageReader reader = (ImageReader) readers.next();
			reader.setInput(iis, true);
			ImageReadParam param = reader.getDefaultReadParam();
			//param.setSourceSubsampling(40, 40, 0,0);
			float imageRatio = reader.getAspectRatio(0);

			
			if (imageRatio <= 1.0){
                            param.setSourceSubsampling(reader.getHeight(0)/height, reader.getHeight(0)/height, 0,0);
			} else {
                            param.setSourceSubsampling(reader.getWidth(0)/width, reader.getWidth(0)/width, 0,0);
			}
			BufferedImage b = reader.read(0, param);
                        
                        double thumbRatio = (double) width / (double) height;
                        int imageWidth = b.getWidth();
                        int imageHeight = b.getHeight();
                        double aspectRatio = (double) imageWidth / (double) imageHeight;

                        int scaleWidth = width;
                        int scaleHeight = height;
                        if (thumbRatio < aspectRatio) {
                            scaleHeight = (int) (width / aspectRatio);
                        } else {
                            scaleWidth = (int) (height * aspectRatio);
                        }

                        BufferedImage scaledImage = new BufferedImage(scaleWidth, scaleHeight,
                                b.getType());
                        Graphics2D graphics2D = scaledImage.createGraphics();
                        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                        graphics2D.drawImage(b, 0, 0, scaleWidth, scaleHeight, null);

			return scaledImage;
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

