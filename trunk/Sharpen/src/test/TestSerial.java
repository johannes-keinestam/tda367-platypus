package test;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JFrame;

import edu.chalmers.platypus.model.Filter;
import edu.chalmers.platypus.model.IFilter;

public class TestSerial {
	public static void main(String[] args) {
		TestSerial ts = new TestSerial();
		ts.test();
	}
	public void test(){
		FileOutputStream fos;
		Filter fc = new Filter();
		fc.applyFilter(new BufferedImage(10, 10, 1));
		ArrayList<IFilter> l = new ArrayList<IFilter>();
		l.add(fc);
		JFrame jf = new JFrame();
		 jf.add(l.get(0).getPanel());
		 jf.setVisible(true);
		try {
			fos = new FileOutputStream(System.getenv("USERPROFILE")+"/PlatyPix/Presets/ctrl.test");
			ObjectOutputStream oos;
			 try {
				oos = new ObjectOutputStream(fos);
				 oos.writeObject(l);
		         oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Filter ctrl;
		FileInputStream fis;
		ArrayList<IFilter> al;
		try {
			fis = new FileInputStream(System.getenv("USERPROFILE")+"/PlatyPix/Presets/ctrl.test");
			ObjectInputStream ois;
			try {
				ois = new ObjectInputStream(fis);
				try {
					 al = (ArrayList<IFilter>) ois.readObject();
					 System.out.println(al.get(0).getName());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	           
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	
	}
}
