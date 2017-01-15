package com.wolfbeisz.spiegeltv_archive_importer.web.model;

import java.io.Serializable;

public class SortOption implements Serializable {
	private SortDirection sortDirection;
	private String sortField;
	private int rank;
	public SortDirection getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(SortDirection sortDirection) {
		this.sortDirection = sortDirection;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
}
