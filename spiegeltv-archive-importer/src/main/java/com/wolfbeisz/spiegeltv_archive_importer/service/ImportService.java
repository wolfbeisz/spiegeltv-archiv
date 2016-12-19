package com.wolfbeisz.spiegeltv_archive_importer.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonReader;
import javax.json.JsonValue;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.wolfbeisz.spiegeltv_archive_importer.dao.ImageDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.JsonMediaDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.MediumDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.RestApiDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.TagDao;
import com.wolfbeisz.spiegeltv_archive_importer.entity.RestApi;
import com.wolfbeisz.spiegeltv_archive_importer.model.Medium;

public class ImportService {
	public void importJsonFiles(Collection<File> files, String restApiVersion) throws FileNotFoundException, IOException {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		try {
			JsonMediaDao jsonMediaDao = new JsonMediaDao(JAXBContext.newInstance(Medium.class));
			TransformService transformService = new TransformService();
			RestApiService service = buildRestApiService(em);
			
			List<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> mediaFromJson = new ArrayList<>();
			
			for (File file : files) {
				try (Reader r = new FileReader(file)) {
					mediaFromJson.add(jsonMediaDao.readMedium(r));
				}
			}
			
			RestApi api = transformService.transformToJpaModel(mediaFromJson, restApiVersion);
			service.insert(api);
			
			em.getTransaction().commit();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		} finally {
			if (em != null)
				em.close();
			
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}
	}

	private RestApiService buildRestApiService(EntityManager em) {
		ImageDao imageDao = new ImageDao(em);
		MediumDao mediumDao = new MediumDao(em);
		RestApiDao restApiDao = new RestApiDao(em);
		TagDao tagDao = new TagDao(em);
		return new RestApiService(imageDao, mediumDao, restApiDao, tagDao);
	}
	
	public void importFromWeb(String restApiVersion) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("spiegeltv-archive-importer");
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		try {
			JsonMediaDao jsonMediaDao = new JsonMediaDao(JAXBContext.newInstance(Medium.class));
			TransformService transformService = new TransformService();
			RestApiService service = buildRestApiService(em);
			
			List<Integer> availableMedia = getAvailableMedia(restApiVersion);
			List<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> mediaFromJson = new ArrayList<>();
			
			for (Integer id : availableMedia) {
				try (Reader mediumAsJson = getMediumReader(restApiVersion, id)) {
					mediaFromJson.add(jsonMediaDao.readMedium(mediumAsJson));
				}
				catch (Exception e) {
					System.out.println(e);
				}
			}
			
			RestApi api = transformService.transformToJpaModel(mediaFromJson, restApiVersion);
			service.insert(api);
			
			em.getTransaction().commit();
		} catch (IOException | JAXBException e) {
			throw new RuntimeException(e);
		} finally {
			if (em != null)
				em.close();
			
			if (entityManagerFactory != null)
				entityManagerFactory.close();
		}
	}
	
	private List<Integer> getAvailableMedia(String restApiVersion) throws IOException {
		List<Integer> mediaIds = new ArrayList<>();
		
		URL url = new URL("http://spiegeltv-ivms2-restapi.s3.amazonaws.com/"+restApiVersion+"/restapi/media.json");
		try (InputStream stream = url.openStream(); JsonReader jsonReader = Json.createReader(stream);) {
			JsonArray ids = jsonReader.readArray();
			for (JsonValue value : ids) {
				if (value instanceof JsonNumber) {
					int id = ((JsonNumber)value).intValue();
					mediaIds.add(id);
				}
			}
		}
		
		return mediaIds;
	}
	
	private Reader getMediumReader(String restApiVersion, Integer mediumId) throws IOException {
		URL mediumUrl = new URL("http://spiegeltv-ivms2-restapi.s3.amazonaws.com/"+restApiVersion+"/restapi/media/"+mediumId+".json");
		return new InputStreamReader(mediumUrl.openStream());
	}
}
