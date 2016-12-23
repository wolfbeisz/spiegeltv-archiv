package com.wolfbeisz.spiegeltv_archive_importer.service;

import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import com.wolfbeisz.spiegeltv_archive_importer.dao.JsonMediaDao;
import com.wolfbeisz.spiegeltv_archive_importer.model.Medium;

public class MediumService {
	
	private SpiegelTvService spiegelTv_;
	
	public MediumService(SpiegelTvService spiegelTvService)
	{
		spiegelTv_ = spiegelTvService;
	}
	
	public List<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> getAvailableMedia(
			String restApiVersion) throws JAXBException, IOException {
		JsonMediaDao jsonMediaDao = new JsonMediaDao(JAXBContext.newInstance(Medium.class));
		
		List<Integer> availableMedia = spiegelTv_.getAvailableMedia(restApiVersion);
		List<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> mediaFromJson = new ArrayList<>();
		
		// move to SpiegelTvService
		for (Integer id : availableMedia) {
			try (Reader mediumAsJson = spiegelTv_.getMediumReader(restApiVersion, id)) {
				mediaFromJson.add(jsonMediaDao.readMedium(mediumAsJson));
			}
			catch (Exception e) {
				System.out.println(e);
			}
		}
		return mediaFromJson;
	}
}
