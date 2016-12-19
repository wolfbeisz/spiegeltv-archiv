package com.wolfbeisz.spiegeltv_archive_importer.dao;

import javax.persistence.EntityManager;

import com.wolfbeisz.spiegeltv_archive_importer.entity.RestApi;

public class RestApiDao {
	private EntityManager em_;

	public RestApiDao(EntityManager em) {
		em_ = em;
	}
	
	public void persist(RestApi api)
	{
		em_.persist(api);
	}
}
