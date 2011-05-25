package edu.chalmers.platypus.util;

import edu.chalmers.platypus.ctrl.IFilterCtrl;
import edu.chalmers.platypus.ctrl.IImageCtrl;
import edu.chalmers.platypus.ctrl.IMiscCtrl;
import edu.chalmers.platypus.ctrl.IPresetCtrl;
import edu.chalmers.platypus.ctrl.IPreviewCtrl;

public class CtrlLocator {
	private static IFilterCtrl filterCtrl;
	private static IImageCtrl imageCtrl;
	private static IPresetCtrl presetCtrl;
	private static IPreviewCtrl previewCtrl;
	private static IMiscCtrl miscCtrl;
	
	
	public static IMiscCtrl getMiscCtrl() {
		return miscCtrl;
	}

	public static void setMiscCtrl(IMiscCtrl miscCtrl) {
		CtrlLocator.miscCtrl = miscCtrl;
	}

	public static IFilterCtrl getFilterCtrl() {
		return filterCtrl;
	}
	
	public static void setFilterCtrl(IFilterCtrl filterCtrl) {
		CtrlLocator.filterCtrl = filterCtrl;
	}
	
	public static IImageCtrl getImageCtrl() {
		return imageCtrl;
	}
	
	public static void setImageCtrl(IImageCtrl imageCtrl) {
		CtrlLocator.imageCtrl = imageCtrl;
	}
	
	public static IPresetCtrl getPresetCtrl() {
		return presetCtrl;
	}
	
	public static void setPresetCtrl(IPresetCtrl presetCtrl) {
		CtrlLocator.presetCtrl = presetCtrl;
	}
	
	public static IPreviewCtrl getPreviewCtrl() {
		return previewCtrl;
	}
	
	public static void setPreviewCtrl(IPreviewCtrl previewCtrl) {
		CtrlLocator.previewCtrl = previewCtrl;
	}
	
}
