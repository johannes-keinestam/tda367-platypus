package edu.chalmers.platypus.test;

import edu.chalmers.platypus.model.Preset;

public class DummyPresetFactory {
	public static Preset getRandomDummyPreset() {
		return new Preset("PresetID "+((Math.random()*100)+(Math.random()*10)), new String[0]) {
			@Override
			public boolean equals(Object o) {
				if (o instanceof Preset) {
					Preset other = (Preset)o;
					return this.getName() == other.getName();
				}
				return false;
			}
		};
	}

}
