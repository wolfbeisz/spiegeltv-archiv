package com.wolfbeisz.spiegeltv_archive_importer.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.Ignore;

public class MediumDaoTest {
	@Test @Ignore
	public void testJpaSetup() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.close();
		entityManagerFactory.close();
	}
}
