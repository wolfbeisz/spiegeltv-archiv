package com.wolfbeisz.spiegeltv_archive_importer.app;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.wolfbeisz.spiegeltv_archive_importer.dao.ImageDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.MediumDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.RestApiDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.TagDao;
import com.wolfbeisz.spiegeltv_archive_importer.service.DeduplicationService;
import com.wolfbeisz.spiegeltv_archive_importer.service.ImportService;
import com.wolfbeisz.spiegeltv_archive_importer.service.MediumService;
import com.wolfbeisz.spiegeltv_archive_importer.service.RestApiService;
import com.wolfbeisz.spiegeltv_archive_importer.service.SpiegelTvService;
import com.wolfbeisz.spiegeltv_archive_importer.service.TransformService;

public class WebImportApp {
	
	public static void main(String[] args) throws IOException {
		WebImportApp app = new WebImportApp();
		
		if ("--latest".equals(args[0])) {
			app.importFromWeb(new SpiegelTvService().getLatestRestapiVersion());
		}
		
		app.importFromWeb(args[0]);
	}
	
	public void importFromWeb(String restApiVersion) { 
		EntityManagerFactory entityManagerFactory = null;
		EntityManager em = null;
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
			em = entityManagerFactory.createEntityManager();
			ImportService importService = buildImportService(em);
			importService.importFromWeb(restApiVersion);
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
}
