package com.wolfbeisz.spiegeltv_archive_importer.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LoadOptions implements Serializable {
	private List<FilterOption> filters = new ArrayList<>();
	private List<SortOption> sorts = new ArrayList<>();
	private int skipItems;
	private int takeItems;
	public List<FilterOption> getFilters() {
		return filters;
	}
	public void setFilters(List<FilterOption> filters) {
		this.filters = filters;
	}
	public List<SortOption> getSorts() {
		return sorts;
	}
	public void setSorts(List<SortOption> sorts) {
		this.sorts = sorts;
	}
	public int getSkipItems() {
		return skipItems;
	}
	public void setSkipItems(int skipItems) {
		this.skipItems = skipItems;
	}
	public int getTakeItems() {
		return takeItems;
	}
	public void setTakeItems(int takeItems) {
		this.takeItems = takeItems;
	}
	
}
