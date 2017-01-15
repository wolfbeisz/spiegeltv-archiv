package com.wolfbeisz.spiegeltv_archive_importer.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import org.primefaces.model.SortOrder;

import com.mysql.jdbc.StringUtils;

public abstract class TableDataModel<T> extends LazyDataModel<T> {
	 
    private static final long serialVersionUID = -6074599821435569629L;
 
    private int rowCount;
 
    @Override
    public int getRowCount() {
        return rowCount;
    }
 
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }
	
    @Override
	public List<T> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
      LoadOptions loadOptions = convert(first, pageSize, sortField, sortOrder, filters);
      List<T> data = loadData(loadOptions);

      Long longSize = loadRowCount(loadOptions);

      setRowCount(longSize.intValue());

      return data;
	}

    protected abstract List<T> loadData(LoadOptions loadOptions);
    protected abstract long loadRowCount(LoadOptions loadOptions);
 
    private LoadOptions convert(int first, int pageSize, String sortField, SortOrder sortOrder,
            Map<String, Object> filters) {
        List<SortMeta> sorts = new ArrayList<>();
        if (!isBlank(sortField)) {
            SortMeta sort = new SortMeta();
            sort.setSortField(sortField);
            sort.setSortOrder(sortOrder);
            sorts.add(sort);
        }
 
        return convert(first, pageSize, sorts, filters);
    }
 
    private LoadOptions convert(int first, int pageSize, List<SortMeta> multiSortMeta, Map<String, Object> filters) {
        LoadOptions loadOptions = new LoadOptions();
        loadOptions.setSkipItems(first);
        loadOptions.setTakeItems(pageSize);
 
        List<SortOption> sorts = new ArrayList<>();
        for (int i = 0; i < multiSortMeta.size(); ++i) {
            SortMeta sortMeta = multiSortMeta.get(i);
            if (sortMeta != null) {
                String sortField = sortMeta.getSortField();
                if (!isBlank(sortField)) {
                    SortOption sort = new SortOption();
                    sort.setSortField(sortField);
                    sort.setSortDirection(convert(sortMeta.getSortOrder()));
                    sort.setRank(i);
                    sorts.add(sort);
                }
            }
        }
        loadOptions.setSorts(sorts);
 
        List<String> filterFields = new ArrayList<>(filters.keySet());
        List<FilterOption> filterOptions = new ArrayList<>();
        for (String filterField : filterFields) {
            if (!isBlank(filterField)) {
                Object filterValue = filters.get(filterField);
                FilterOption filterOption = new FilterOption();
                filterOption.setField(filterField);
                filterOption.setValue((Serializable)filterValue); // check this in case of an exception
                filterOptions.add(filterOption);
            }
        }
        loadOptions.setFilters(filterOptions);
 
        return loadOptions;
    }
 
    private SortDirection convert(SortOrder sortOrder) {
        switch (sortOrder) {
        case ASCENDING:
            return SortDirection.ASCENDING;
        case DESCENDING:
            return SortDirection.DESCENDING;
        case UNSORTED:
            return SortDirection.UNSORTED;
        default:
            throw new IllegalArgumentException("Unknown sort order");
        }
    }
    
    private static boolean isBlank(String value) {
    	return value == null || value.isEmpty();
    }
}
