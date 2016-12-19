package com.wolfbeisz.spiegeltv_archive_importer.service;

import com.wolfbeisz.spiegeltv_archive_importer.dao.ImageDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.MediumDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.RestApiDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.TagDao;
import com.wolfbeisz.spiegeltv_archive_importer.entity.Image;
import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;
import com.wolfbeisz.spiegeltv_archive_importer.entity.RestApi;
import com.wolfbeisz.spiegeltv_archive_importer.entity.Tag;

public class RestApiService {
	private ImageDao imageDao_;
	private MediumDao mediumDao_;
	private RestApiDao restApiDao_;
	private TagDao tagDao_;
	
	public RestApiService(ImageDao iDao, MediumDao mDao, RestApiDao aDao, TagDao tDao) {
		imageDao_ = iDao;
		mediumDao_ = mDao;
		restApiDao_ = aDao;
		tagDao_ = tDao;
	}
	
	/**
	 * Inserts the specified api and any depending entity in the db.
	 * @param api
	 */
	public void insert(RestApi api) {
		//TODO
		for (Medium m : api.getMedia()) {
			for (Image i : m.getImages()) {
				imageDao_.persist(i);
			}
			
			for (Tag t : m.getTags()) {
				tagDao_.persist(t);
			}
			
			mediumDao_.persist(m);
		}
		
		restApiDao_.persist(api);
	} 
}
