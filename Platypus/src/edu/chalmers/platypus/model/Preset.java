package edu.chalmers.platypus.model;

import java.io.Serializable;
import java.util.Date;

public class Preset implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 310479221482604361L;
	String name;
	Date date;
	String[] filters;

	public Preset(String name, String filters[]) {
		this.name = name;
		this.filters = filters;
		this.date = new Date();
	}

	public String getName() {
		return name;
	}

	public Date getDate() {
		return date;
	}

	public String[] getFilters() {
		return filters;
	}
}
