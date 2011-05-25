package edu.chalmers.platypus.ctrl;

import java.beans.PropertyChangeEvent;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Observable;
import java.util.Observer;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.util.ComBus;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.util.StateChanges;
import edu.chalmers.platypus.util.interfaces.IFilterCtrl;

public class FilterCtrl implements IFilterCtrl {
	
	private URLClassLoader libraryLoader;
	private static FilterCtrl instance;

	/**
	 * Constructor
	 */
	private FilterCtrl() {
		scanForFilters();
	}
	
	/**
	 * Returns the singleton instance of this Class, if none exist
	 * the constructor is run and the instance is set.
	 * 
	 * @return the singleton instance of the FilterCtrl.
	 */
	public static FilterCtrl getInstance() {
		if (instance == null) {
			instance = new FilterCtrl();
		}
		return instance;
	}
	
	
	
	/**
	 * Returns a list of filters available for use in the program.
	 * 
	 * @return the list of filters.
	 */
	public ArrayList<IFilter> getLoadedFilterList() {
		return ModelLocator.getModel().getFilterContainer().getList();
	}
	
	/**
	 * Adds the specified filter to the batch of active filters.
	 * 
	 * @param the filter to be added to the batch.
	 */
	public void addFilterToBatch(IFilter filter) {
		PropertyChangeEvent pce;
		if (!ModelLocator.getModel().getActiveFilters().getList().contains(filter)) {
			ModelLocator.getModel().getActiveFilters().getList().add(filter);
			if (filter instanceof Observable) {
				((Observable) filter).addObserver(new Observer() {
					@Override
					public void update(Observable o, Object arg) {
						ComBus.notifyListeners(new PropertyChangeEvent(this,
								StateChanges.PREVIEW_IMAGE_UPDATED.toString(),
								null, o));
					}
				});
			}
			pce = new PropertyChangeEvent(this,
					StateChanges.NEW_FILTER_ADDED_TO_BATCH.toString(), null,
					filter);
		} else {
			pce = new PropertyChangeEvent(this,
					StateChanges.ERROR_OCCURED.toString(), null,
					"The selected filter is already in use");
			ComBus.notifyListeners(pce);
			pce = new PropertyChangeEvent(this,
					StateChanges.DUPLICATE_FILTER_SELECTED.toString(), null,
					null);
		}
		ComBus.notifyListeners(pce);
	}

	/**
	 * Removes the specified filter from the batch of active filters.
	 * 
	 * @param the filter to be removed from the batch.
	 */
	public void removeFilterFromBatch(IFilter filter) {
		ArrayList<IFilter> list = ModelLocator.getModel().getActiveFilters()
				.getList();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == filter) {
				list.remove(i);
				PropertyChangeEvent pce = new PropertyChangeEvent(this,
						StateChanges.FILTER_REMOVED_FROM_BATCH.toString(),
						filter, null);
				ComBus.notifyListeners(pce);
				break;
			}
		}

	}

	
	/**
	 * Adds the filter to the program and notifies listeners on the ComBus 
	 * of the NEW_FILTER_ADDED_TO_APPLICATION event.
	 * 
	 * @param the filter to be added
	 */
	public void addFilter(IFilter filter) {
		ModelLocator.getModel().getFilterContainer().addFilter(filter);
		ComBus.notifyListeners(new PropertyChangeEvent(this,
				StateChanges.NEW_FILTER_ADDED_TO_APPLICATION.toString(), null,
				filter));
	}

	
	/**
	 * Adds an instance if the file is a filter and proceeds to copy the file
	 * to the PlatyPix/Filters directory.
	 * 
	 * @param a file representing the filter to be imported
	 */
	public void importNewFilter(File filter) {
		URL filterURL = null;
		URL url[] = new URL[1];

		try {
			filterURL = filter.toURI().toURL();
			url[0] = filterURL;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}

		if (importFilter(url)) {
			copyNewFilter(filter);
		} else {
			PropertyChangeEvent pce = new PropertyChangeEvent(this,
					StateChanges.ERROR_OCCURED.toString(), null,
					"The specified file is not a valid filter");
			ComBus.notifyListeners(pce);
		}
	}

	/**
	 * Copies the file to the PlatyPix/Filters directory. The file is assumed
	 * to be a filter.
	 * 
	 * @param the file to be copied
	 */
	public void copyNewFilter(File filter) {
		String filterFileName = filter.getPath();

		FileChannel outputChannel = null;
		FileChannel sourceChannel = null;

		File outputFile = new File(System.getProperty("user.home")
				+ "/PlatyPix/Filters/"
				+ filterFileName.substring(
						filterFileName.lastIndexOf(File.separator) + 1,
						filterFileName.length()));
		
		if(!filter.equals(outputFile)){
			
			try {
				sourceChannel = new FileInputStream(filter).getChannel();
				outputChannel = new FileOutputStream(outputFile).getChannel();
	
				try {
					outputChannel.transferFrom(sourceChannel, 0,
							sourceChannel.size());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (sourceChannel != null)
					try {
						sourceChannel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				if (outputChannel != null)
					try {
						outputChannel.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}

	}
	
	/**
	 * Imports a filter file (JAR) from the specified URL.
         *
	 * @param url filter files to load
	 * @return true if successfull, otherwise false
	 */
	private boolean importFilter(final URL[] url) {
		URLClassLoader loader = AccessController.doPrivileged(new PrivilegedAction<URLClassLoader>() {
			public URLClassLoader run() {
				return new URLClassLoader(url, libraryLoader);
			}
		});
		
		try {
			Class filter = loader
					.loadClass("edu.chalmers.platypus.model.Filter");
			try {
				IFilter f = (IFilter) filter.newInstance();
				ModelLocator.getModel().getFilterContainer().addFilter(f);
                                PropertyChangeEvent pce = new PropertyChangeEvent(
                                        this, StateChanges.FILTER_LOADED.toString(),
                                        null, f.getName());
                                ComBus.notifyListeners(pce);
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

	/**
	 * Scans for filters in default folder, and loads the filters found.
	 */
	public void scanForFilters() {
		File folder = new File(System.getProperty("user.home")
				+ "/PlatyPix/Filters");

		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				URL url[] = new URL[1];
				try {
					url[0] = new URL("file:///" + System.getProperty("user.home")
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
	
	/**
	 * Determines whether the JAR needs external libraries, and loads them.
         *
	 * @param file JAR to scan
	 */
	private void scanJarForLibraries(String file) {
		  
		try {
			JarFile jarFile = new JarFile(System.getProperty("user.home")
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
	
	/**
	 * Loads libraries specified in the file (usually a text file).
         *
	 * @param stream file to scan
	 */
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
	
	/**
	 * Scans a JAR and loads classes from it. Used when the classes are
         * needed for a filter to work.
	 * @param jar
	 */
	private void scanClasses(String jar){
		JarFile jarFile;
		try {
			jarFile = new JarFile(System.getProperty("user.home")
					+ "/PlatyPix/Filters/"+jar);	   
			   Enumeration<JarEntry> enu = jarFile.entries();
		       while (enu.hasMoreElements()) {
		    	   JarEntry h = enu.nextElement();
		    	   if(!h.isDirectory()&& h.toString().endsWith(".class"))
		    		   loadClass(h.toString().replaceAll("/", ".").replaceAll(".class", ""), jar);
		       }
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * Loads a class from inside a JAR file.
         *
	 * @param file class to load
	 * @param jar JAR that contains it
	 */
	private void loadClass(String file, String jar){
		URL url[] = new URL[1];
		try {
			url[0] = new URL("file:///" + System.getProperty("user.home")
					+ "/PlatyPix/Filters/"+jar);
			libraryLoader = new URLClassLoader(url);
			try {
				libraryLoader.loadClass(file);
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
