package com.wolfbeisz.spiegeltv_archive_importer.web.model;

import java.util.List;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;
import com.wolfbeisz.spiegeltv_archive_importer.web.service.MediumDataProvider;

public class MediumDataModel extends TableDataModel<Medium> {

	private MediumDataProvider provider_;
	
	public MediumDataModel(MediumDataProvider provider) {
		provider_ = provider;
	}
	
	@Override
	protected List<Medium> loadData(LoadOptions loadOptions) {
		return provider_.loadData(loadOptions);
	}

	@Override
	protected long loadRowCount(LoadOptions loadOptions) {
		return provider_.loadRowCount(loadOptions);
	}

}
