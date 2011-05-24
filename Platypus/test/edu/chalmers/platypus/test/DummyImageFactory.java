package edu.chalmers.platypus.test;

import java.io.File;

import edu.chalmers.platypus.model.BatchImage;

public class DummyImageFactory {
	public static BatchImage getRandomDummyBatchImage() {
		return new BatchImage(new File(""));
	}
}
