package com.wolfbeisz.spiegeltv_archive_importer.dao;

import javax.persistence.EntityManager;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Image;

public class ImageDao {
	private EntityManager em_;

	public ImageDao(EntityManager em) {
		em_ = em;
	}
	
	public void persist(Image image)
	{
		em_.persist(image);
	}
}
