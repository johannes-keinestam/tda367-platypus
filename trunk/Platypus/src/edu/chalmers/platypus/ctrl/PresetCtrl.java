package edu.chalmers.platypus.ctrl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import edu.chalmers.platypus.model.IFilter;
import edu.chalmers.platypus.model.Preset;
import edu.chalmers.platypus.util.CtrlLocator;
import edu.chalmers.platypus.util.ModelLocator;
import edu.chalmers.platypus.util.interfaces.IPresetCtrl;

public class PresetCtrl implements IPresetCtrl{
	
	private static PresetCtrl instance;

	/**
	 * Constructor
	 */
	private PresetCtrl() {
	}
	
	/**
	 * Returns the singleton instance of this Class, if none exist
	 * the constructor is run and the instance is set.
	 * 
	 * @return the singleton instance of the PreviewCtrl.
	 */
	public static PresetCtrl getInstance() {
		if (instance == null) {
			instance = new PresetCtrl();
		}
		return instance;
	}

	
	/**
	 * Returns all the available presets in the PlatyPix/Presets directory
	 * 
	 * @return returns all the available presets
	 */
	public ArrayList<Preset> getLoadedPresetList() {
		File folder = new File(System.getProperty("user.home")
				+ "/PlatyPix/Presets");
		File[] listOfFiles = folder.listFiles();
		FileInputStream fis;
		ArrayList<Preset> presets = new ArrayList<Preset>();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile()) {
				Preset pe;
				try {
					fis = new FileInputStream(listOfFiles[i]);
					ObjectInputStream ois;
					try {
						ois = new ObjectInputStream(fis);
						try {
							pe = (Preset) ois.readObject();
							presets.add(pe);

						} catch (ClassCastException e) {
							// TODO Auto-generated catch block
							// e.printStackTrace();
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
					e.printStackTrace();
				}

			}

		}
		return presets;
	}

	/**
	 * Loads the preset into the program, all filters contained in the preset is 
	 * added to the filter batch.
	 * 
	 * @param the preset to be loaded into the program
	 */
	public void loadPreset(Preset preset) {
		for (String name : preset.getFilters()) {
			IFilter filter = ModelLocator.getModel().getFilterContainer().getFilter(name);
			FileInputStream fis;

			try {
				fis = new FileInputStream(System.getProperty("user.home")+"/PlatyPix/Presets/"+preset.getName()+"/"+filter.getName()+".preset");
				try {
					ObjectInputStream ois = new ObjectInputStream(fis);
					try {
						filter.setState((Object[]) ois.readObject());
					
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			CtrlLocator.getFilterCtrl().addFilterToBatch(filter);	
		}

		
	}
	
	/**
	 * Saves the current batch of filters as a preset to the
	 * PlatyPix/Presets directory. Replaces existing presets with same name.
	 * 
	 * @param the name of the preset
	 */
	public void savePreset(final String name){
    	//Saves the preset file
    	File f = new File(System.getProperty("user.home")+"/PlatyPix/Presets/"+name+"/");
		try{
			f.mkdir();
		}catch(Exception e){
		}
		
		for (IFilter filter : ModelLocator.getModel().getActiveFilters().getList()) {
			try {
				FileOutputStream fos = new FileOutputStream(System.getProperty("user.home")+"/PlatyPix/Presets/"+name+"/"+filter.getName()+".preset");
				ObjectOutputStream oos;
				try {
					 oos = new ObjectOutputStream(fos);
					 oos.writeObject(filter.getState());
			         oos.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
				catch (NullPointerException e){
					e.printStackTrace();
				}
			}
			catch (FileNotFoundException e) {	
				e.printStackTrace();
			}
		}
		//Saves the info file
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(System.getProperty("user.home")+"/PlatyPix/Presets/"+name+".info");
			ObjectOutputStream oos;
			try {
				 oos = new ObjectOutputStream(fos);
				 String[] filters = new String[ModelLocator.getModel().getActiveFilters().getList().size()];
				 for (int i = 0; i < ModelLocator.getModel().getActiveFilters().getList().size(); i++) {
					filters[i] = ModelLocator.getModel().getActiveFilters().getList().get(i).getName();
				}
				 oos.writeObject(new Preset(name, filters));
		         oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
           
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
