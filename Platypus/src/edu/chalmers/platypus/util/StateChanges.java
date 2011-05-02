package edu.chalmers.platypus.util;

public enum StateChanges {
	NEW_IMAGE_IN_BATCH,
	NEW_FILTER_ADDED_TO_BATCH,
	NEW_FILTER_ADDED_TO_APPLICATION,
	IMAGE_REMOVED_FROM_BATCH,
	FILTER_REMOVED_FROM_BATCH,
	NEW_PREVIEW_IMAGE,
	PREVIEW_IMAGE_UPDATED,
	PROCESSING_IMAGE, 
	SAVED_IMAGE
}
