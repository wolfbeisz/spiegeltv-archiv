package com.wolfbeisz.spiegeltv_archive_importer.service;

import java.io.StringReader;
import java.util.Arrays;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.junit.Test;

import com.wolfbeisz.spiegeltv_archive_importer.dao.JsonMediaDao;
import com.wolfbeisz.spiegeltv_archive_importer.dao.JsonMediaDaoTest;
import com.wolfbeisz.spiegeltv_archive_importer.model.Medium;

public class TransformServiceTest {
	@Test
	public void testTransformSingleMedium() throws JAXBException {
		JsonMediaDao dao = new JsonMediaDao(JAXBContext.newInstance(Medium.class));
		TransformService service = new TransformService();
		try(StringReader stringReader = new StringReader(JsonMediaDaoTest.EXAMPLE_JSON)) {
			Medium medium = dao.readMedium(stringReader);
			Collection<com.wolfbeisz.spiegeltv_archive_importer.entity.Medium> collection = service.transform(Arrays.asList(medium));
			Assert.assertEquals(1, collection.size());
			com.wolfbeisz.spiegeltv_archive_importer.entity.Medium medium2 = collection.iterator().next();
			Assert.assertEquals("763f71a0e2f94cd6835efcfd97a90174", medium2.getUuid());
			Assert.assertEquals(2, medium2.getImages().size());
			Assert.assertEquals(3, medium2.getTags().size());
			Assert.assertEquals("Making of...", medium2.getTitle());
			Assert.assertEquals((Integer)6162, medium2.getMediumId());
		}
	}
}
