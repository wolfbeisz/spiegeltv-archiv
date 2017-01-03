package com.wolfbeisz.spiegeltv_archive_importer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;
import org.junit.Ignore;

import com.wolfbeisz.spiegeltv_archive_importer.dao.ImageDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.MediumDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.RestApiDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.TagDao;

public class ImportServiceTest {
	@Test @Ignore
	public void testSingleFileImport() throws FileNotFoundException, IOException {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager em = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
			em = entityManagerFactory.createEntityManager();
			ImportService importService = buildImportService(em);
			importService.importJsonFiles(Arrays.asList(new File("C:\\Users\\Philipp\\Desktop\\medium.json")), "20161215145319");
		} finally {
			if (em != null)
				em.close();
			
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}
	}
	
	@Test @Ignore
	public void testWebImport() { 
		EntityManagerFactory entityManagerFactory = null;
		EntityManager em = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
			em = entityManagerFactory.createEntityManager();
			ImportService importService = buildImportService(em);
			importService.importFromWeb("20161223163606");
		} finally {
			if (em != null)
				em.close();
			
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}
	}
	
	private ImportService buildImportService(EntityManager em) {
		SpiegelTvService spiegelTvService = new SpiegelTvService();
		RestApiService restApiService = buildRestApiService(em);
		TransformService transformService = new TransformService();
		MediumService mediumService = new MediumService(spiegelTvService);
		DeduplicationService deduplicationService = buildDeduplicationService(em);
		return new ImportService(em, restApiService, transformService, mediumService, deduplicationService);
	}
	
	private RestApiService buildRestApiService(EntityManager em) {
		ImageDao imageDao = new ImageDao(em);
		MediumDao mediumDao = new MediumDao(em);
		RestApiDao restApiDao = new RestApiDao(em);
		TagDao tagDao = new TagDao(em);
		return new RestApiService(imageDao, mediumDao, restApiDao, tagDao);
	}
	
	private DeduplicationService buildDeduplicationService(EntityManager em) {
		MediumDao dao = new MediumDao(em);
		return new DeduplicationService(dao);
	}
	
	@Test @Ignore
	public void testMultiFileImport() throws FileNotFoundException, IOException {
		EntityManagerFactory entityManagerFactory = null;
		EntityManager em = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
			em = entityManagerFactory.createEntityManager();
			ImportService importService = buildImportService(em);
			importService.importJsonFiles(Arrays.asList(new File("C:\\Users\\Philipp\\Desktop\\medium.json"), new File("C:\\Users\\Philipp\\Desktop\\medium2.json")), "20161215145321");
		} finally {
			if (em != null)
				em.close();
			
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}
	}
	
	public static final String MEDIUM_2_JSON = "";
}
