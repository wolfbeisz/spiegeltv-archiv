package com.wolfbeisz.spiegeltv_archive_importer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.wolfbeisz.spiegeltv_archive_importer.dao.JsonMediaDao;
import com.wolfbeisz.spiegeltv_archive_importer.entity.RestApi;
import com.wolfbeisz.spiegeltv_archive_importer.model.Medium;

public class ImportService {
	private EntityManager em_;
	private RestApiService restApiservice_;
	private TransformService transformService_;
	private MediumService mediumService_;
	private DeduplicationService deduplicationService_;
	
	public ImportService(EntityManager em, RestApiService restApiService, TransformService transformService, MediumService mediumService, DeduplicationService deduplicationService) {
		em_ = em;
		restApiservice_ = restApiService;
		transformService_ = transformService;
		mediumService_ = mediumService;
		deduplicationService_ = deduplicationService;
	}
	
	public void importJsonFiles(Collection<File> files, String restApiVersion) throws FileNotFoundException, IOException {
		try {
			em_.getTransaction().begin();
			JsonMediaDao jsonMediaDao = new JsonMediaDao(JAXBContext.newInstance(Medium.class));
			
			List<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> mediaFromJson = new ArrayList<>();
			
			for (File file : files) {
				try (Reader r = new FileReader(file)) {
					mediaFromJson.add(jsonMediaDao.readMedium(r));
				}
			}
			
			RestApi api = transformService_.transformToJpaModel(mediaFromJson, restApiVersion);
			deduplicationService_.deduplicateMedia(api);
			restApiservice_.insert(api);
			
			em_.getTransaction().commit();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void importFromWeb(String restApiVersion) {
		try {
			em_.getTransaction().begin();
			List<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> mediaFromJson = mediumService_.getAvailableMedia(restApiVersion);
			
			RestApi api = transformService_.transformToJpaModel(mediaFromJson, restApiVersion);
			deduplicationService_.deduplicateMedia(api);
			restApiservice_.insert(api);
			
			em_.getTransaction().commit();
		} catch (IOException | JAXBException e) {
			throw new RuntimeException(e);
		}
	}
}
