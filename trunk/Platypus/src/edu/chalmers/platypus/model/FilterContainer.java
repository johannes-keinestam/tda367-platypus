package edu.chalmers.platypus.model;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class FilterContainer {

	private static FilterContainer filterContainer;
	private ArrayList<IFilter> loadedFilters = new ArrayList<IFilter>();
	private URLClassLoader libraryLoader;

	private FilterContainer() {
		scanForFilters();
	}

	public static FilterContainer getFilterContainerObject() {
		if (filterContainer == null) {
			filterContainer = new FilterContainer();
		}
		return filterContainer;
	}

	public void addFilter(IFilter filter) {
		loadedFilters.add(filter);
	}

	public ArrayList<IFilter> getList() {
		return new ArrayList<IFilter>(loadedFilters);
	}

	public IFilter getFilter(String name) {
		for (IFilter filter : loadedFilters) {
			if (filter.getName().equals(name)) {
				return filter;
			}
		}
		return null;
	}

	public boolean importFilter(URL[] url) {
		URLClassLoader loader = new URLClassLoader(url, libraryLoader);
		
		try {
			Class filter = loader
					.loadClass("edu.chalmers.platypus.model.FilterClass");
			try {
				IFilter f = (IFilter) filter.newInstance();
				loadedFilters.add(f);
				System.out.println(f.getName() + " loaded");
				return true;
			} catch (InstantiationException e) {
				return false;
			} catch (IllegalAccessException e) {
				return false;
			}
		} catch (ClassNotFoundException e1) {
			return false;
		}

	}

	public void scanForFilters() {
		File folder = new File(System.getenv("USERPROFILE")
				+ "/PlatyPix/Filters");

		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				URL url[] = new URL[1];
				try {
					url[0] = new URL("file:///" + System.getenv("USERPROFILE")
							+ "/PlatyPix/Filters/" + listOfFiles[i].getName());
					scanJarForLibraries(listOfFiles[i].getName());
					importFilter(url);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}

		}
	}
	private void scanJarForLibraries(String file) {
		  
		try {
			JarFile jarFile = new JarFile(System.getenv("USERPROFILE")
					+ "/PlatyPix/Filters/"+file);
			
			JarEntry entry = jarFile.getJarEntry("Libraries.txt");
			   BufferedInputStream stream = new BufferedInputStream(jarFile.getInputStream(entry));
			   loadLibraries(stream);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch ( NullPointerException e){
			
		}

		
	}
	private void loadLibraries(BufferedInputStream stream) {
	    String thisLine;
	    try {
	      BufferedInputStream is =  stream;
	      BufferedReader br = new BufferedReader
	         (new InputStreamReader(is));
	      while ((thisLine = br.readLine()) != null) {  
	    	  	scanClasses(thisLine);
	         }
	      }
	    catch (Exception e) {
	      e.printStackTrace();
	      }
	  }
	private void scanClasses(String jar){
		JarFile jarFile;
		try {
			jarFile = new JarFile(System.getenv("USERPROFILE")
					+ "/PlatyPix/Filters/"+jar);	   
			   Enumeration<JarEntry> enu = jarFile.entries();
		       while (enu.hasMoreElements()) {
		    	   JarEntry h = enu.nextElement();
		    	   if(!h.isDirectory()&& h.toString().endsWith(".class"))
		    		   loadClass(h.toString().replaceAll("/", ".").replaceAll(".class", ""), jar);
		       }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	private void loadClass(String file, String jar){
		URL url[] = new URL[1];
		try {
			url[0] = new URL("file:///" + System.getenv("USERPROFILE")
					+ "/PlatyPix/Filters/"+jar);
			libraryLoader = new URLClassLoader(url);
			try {
				Class cl = libraryLoader.loadClass(file);
				System.out.println(cl.toString());
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}