package com.wolfbeisz.spiegeltv_archive_importer.service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.wolfbeisz.spiegeltv_archive_importer.entity.Image;
import com.wolfbeisz.spiegeltv_archive_importer.entity.Medium;
import com.wolfbeisz.spiegeltv_archive_importer.entity.RestApi;
import com.wolfbeisz.spiegeltv_archive_importer.entity.Tag;

public class TransformService {
	
	private SimpleDateFormat dateFormat_ = new SimpleDateFormat("yyyy-MM-dd");
	private SimpleDateFormat dateTimeFormat_ = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	public RestApi transformToJpaModel(Collection<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> media, String version) {
		try {
			RestApi api = new RestApi();
			api.setVersion((new SimpleDateFormat("yyyyMMddHHmmss")).parse(version));
			Collection<Medium> transformedMedia = transform(media);
			api.getMedia().addAll(transformedMedia);
			for (Medium medium : transformedMedia) {
				medium.getApi().add(api);
			}
			
			return api;
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Transforms JAXB-models representing media to JPA entities.
	 * @param media
	 * @return
	 */
	public Collection<Medium> transform(Collection<com.wolfbeisz.spiegeltv_archive_importer.model.Medium> media) {
		List<Medium> result = new ArrayList<>();
		
		for (com.wolfbeisz.spiegeltv_archive_importer.model.Medium source : media) {
			Medium resultMedium = new Medium();
			transformTags(source, resultMedium);
			transformImages(source, resultMedium);
			transformProperties(source, resultMedium);
			
			result.add(resultMedium);
		}
		
		return result;
	}

	private void transformProperties(
			com.wolfbeisz.spiegeltv_archive_importer.model.Medium source,
			Medium target) {
		// TODO Auto-generated method stub
		target.setAdAllowed(source.isAdAllowed());
		target.setAirdate(transformDate(source.getAirdate()));
		target.setCreated(transformDateTime(source.getCreated()));
		target.setDescription(source.getDescription());
		target.setDurationInMs(source.getDurationInMs());
		target.setIsPremium(source.isPremium());
		target.setLanguage(source.getLanguage());
		target.setMediumId(source.getId());
		target.setParentMedium(source.getParentMedium());
		target.setSlug(source.getSlug());
		target.setSubtitle(source.getSubtitle());
		target.setTeaser(source.getTeaser());
		target.setTitle(source.getTitle());
		target.setType(source.getType());
		target.setUpdated(transformDateTime(source.getUpdated()));
		target.setUuid(source.getUuid());
		target.setWebAirdate(transformDate(source.getWebAirdate()));
		target.setWide(source.isWide());
		
	}

	//TODO : allow null?
	private Date transformDate(String date) {
		if (date == null)
			return null;
		
		try {
			return dateFormat_.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Date transformDateTime(String date) {
		if (date == null)
			return null;
		
		try {
			return dateTimeFormat_.parse(date);
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private void transformImages(
			com.wolfbeisz.spiegeltv_archive_importer.model.Medium source,
			Medium target) {
		List<Image> images = new ArrayList<>();
		
		for (com.wolfbeisz.spiegeltv_archive_importer.model.Image srcImage : source.getImages()) {
			Image image = new Image();
			image.setMedium(target);
			image.setUrl(srcImage.getUrl());
			image.setWidth(srcImage.getWidth());
			image.setSpecSlug(srcImage.getSpec_slug());
			image.setHeight(srcImage.getHeight());
			
			images.add(image);
		}
		
		target.setImages(images);
	}

	private void transformTags(
			com.wolfbeisz.spiegeltv_archive_importer.model.Medium source,
			Medium target) {
		List<Tag> tags = new ArrayList<>();
		
		for (String srcTag : source.getTags()) {
			Tag tag = new Tag();
			tag.setMedium(target);
			tag.setName(srcTag);
			
			tags.add(tag);
		}
		
		target.setTags(tags);
	}
}
