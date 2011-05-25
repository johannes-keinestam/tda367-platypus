package edu.chalmers.platypus.util.interfaces;

import java.util.ArrayList;

import edu.chalmers.platypus.model.Preset;

public interface IPresetCtrl {
	public ArrayList<Preset> getLoadedPresetList();

	public void loadPreset(Preset preset);
	
	public void savePreset(String name);
}
