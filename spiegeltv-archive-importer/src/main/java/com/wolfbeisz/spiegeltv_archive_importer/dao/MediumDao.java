package com.wolfbeisz.spiegeltv_archive_importer.dao;

import javax.persistence.EntityManager;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;

public class MediumDao {
	private EntityManager em_;

	public MediumDao(EntityManager em) {
		em_ = em;
	}
	
	public void persist(Medium medium)
	{
		em_.persist(medium);
	}
}
