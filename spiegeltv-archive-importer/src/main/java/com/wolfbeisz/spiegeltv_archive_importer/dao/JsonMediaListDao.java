package com.wolfbeisz.spiegeltv_archive_importer.dao;

import java.io.Reader;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.eclipse.persistence.jaxb.UnmarshallerProperties;

public class JsonMediaListDao {
	public ArrayList<Integer> readMediaList(Reader input) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(Integer.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			unmarshaller.setProperty(UnmarshallerProperties.MEDIA_TYPE, "application/json");
			unmarshaller.setProperty(UnmarshallerProperties.JSON_INCLUDE_ROOT, false);
			
			return unmarshaller.unmarshal(new StreamSource(input), ArrayList.class).getValue();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
}
