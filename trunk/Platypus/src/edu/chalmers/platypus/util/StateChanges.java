package edu.chalmers.platypus.util;

/**
 * Labels for possible changes in state in model. Used to communicate to the
 * GUI when the model has changed.
 */
public enum StateChanges {
	NEW_IMAGE_IN_BATCH,
	NEW_FILTER_ADDED_TO_BATCH,
	NEW_FILTER_ADDED_TO_APPLICATION,
	IMAGE_REMOVED_FROM_BATCH,
	FILTER_REMOVED_FROM_BATCH,
	NEW_PREVIEW_IMAGE,
	PREVIEW_IMAGE_UPDATED,
	PROCESSING_IMAGE,
        APPLYING_FILTER,
	SAVED_IMAGE,
        SAVE_OPERATION_FINISHED,
        SAVE_OPERATION_ABORTED,
        MODEL_RESET,
        ERROR_OCCURED,
        DUPLICATE_FILTER_SELECTED,
        IMAGE_BATCH_EMPTY,
        IMAGE_BATCH_CLEARED
}
