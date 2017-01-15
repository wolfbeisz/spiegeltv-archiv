package com.wolfbeisz.spiegeltv_archive_importer.web.model;

import java.io.Serializable;

public class FilterOption implements Serializable {
	private String field;
	private Serializable value;
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public Serializable getValue() {
		return value;
	}
	public void setValue(Serializable value) {
		this.value = value;
	}
}
