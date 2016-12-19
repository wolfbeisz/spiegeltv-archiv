package com.wolfbeisz.spiegeltv_archive_importer.dao;

import javax.persistence.EntityManager;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Tag;

public class TagDao {
	private EntityManager em_;

	public TagDao(EntityManager em) {
		em_ = em;
	}
	
	public void persist(Tag tag)
	{
		em_.persist(tag);
	}
}
