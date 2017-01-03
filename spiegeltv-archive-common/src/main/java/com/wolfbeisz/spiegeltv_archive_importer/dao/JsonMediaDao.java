package com.wolfbeisz.spiegeltv_archive_importer.dao;

import java.io.Reader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;

import com.wolfbeisz.spiegeltv_archive_importer.model.Medium;

public class JsonMediaDao {
	
	private JAXBContext context_;
	
	public JsonMediaDao(JAXBContext context) {
		context_ = context;
	}
	
	public Medium readMedium(Reader input) {
		try {
			Unmarshaller unmarshaller = context_.createUnmarshaller();
			unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
			unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
			
			return (Medium) unmarshaller.unmarshal(new StreamSource(input), Medium.class).getValue();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
}
