package com.wolfbeisz.spiegeltv_archive_importer.dao;

import java.io.StringReader;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import junit.framework.Assert;

import org.junit.Test;

import com.wolfbeisz.spiegeltv_archive_importer.model.Medium;

public class JsonMediaDaoTest {
	public static final String EXAMPLE_JSON = "{\r\n" + 
			"    \"subtitle\": \"Phantastische Tierwesen und wo sie zu finden sind\", \r\n" + 
			"    \"links\": [], \r\n" + 
			"    \"airdate\": null, \r\n" + 
			"    \"content_partners\": [], \r\n" + 
			"    \"is_premium\": false, \r\n" + 
			"    \"parent_medium\": null, \r\n" + 
			"    \"images\": [\r\n" + 
			"        {\r\n" + 
			"            \"url\": \"http://d22mepmetc46uf.cloudfront.net/images/Florence_Foster_Jenkins_klein.jpg\", \r\n" + 
			"            \"width\": 224, \r\n" + 
			"            \"spec_slug\": \"f1-film-slider\", \r\n" + 
			"            \"height\": 126\r\n" + 
			"        }, \r\n" + 
			"        {\r\n" + 
			"            \"url\": \"http://d22mepmetc46uf.cloudfront.net/images/Florence_Foster_Jenkins_gro%C3%9F.jpg\", \r\n" + 
			"            \"width\": 640, \r\n" + 
			"            \"spec_slug\": \"f3-film-embed\", \r\n" + 
			"            \"height\": 335\r\n" + 
			"        }\r\n" + 
			"    ], \r\n" + 
			"    \"playlists\": [], \r\n" + 
			"    \"is_ad_allowed\": true, \r\n" + 
			"    \"id\": 6162, \r\n" + 
			"    \"child_media\": [], \r\n" + 
			"    \"uuid\": \"763f71a0e2f94cd6835efcfd97a90174\", \r\n" + 
			"    \"title\": \"Making of...\", \r\n" + 
			"    \"companyroles\": [\r\n" + 
			"        {\r\n" + 
			"            \"role\": \"producer\", \r\n" + 
			"            \"company_id\": 7, \r\n" + 
			"            \"company_unicode\": \"S&L Medienproduktion\", \r\n" + 
			"            \"company_slug\": \"sl-medienproduktion\"\r\n" + 
			"        }\r\n" + 
			"    ], \r\n" + 
			"    \"type\": \"movie\", \r\n" + 
			"    \"personroles\": [], \r\n" + 
			"    \"updated\": \"2016-11-17T13:25:36\", \r\n" + 
			"    \"description\": \"Außerdem die Mini Making ofs zu „Amerikanisches Idyll“ und der Komödie „Florence Foster Jenkins“. Eigentlich könnte sich die Millionärin Florence Foster Jenkins zurücklehnen und ihren Reichtum genießen, doch sie fühlt sich zu Höherem berufen und strebt eine Karriere als Opernsängerin an. \", \r\n" + 
			"    \"timedtextsources\": [], \r\n" + 
			"    \"tags\": [\r\n" + 
			"        \"Making of\", \r\n" + 
			"        \"Kino Trailer\", \r\n" + 
			"        \"Kino\"\r\n" + 
			"    ], \r\n" + 
			"    \"deeplinks\": [], \r\n" + 
			"    \"duration_in_ms\": 752826, \r\n" + 
			"    \"teaser\": \"In New York wird die magische Welt 1926 von einer unbekannten Macht bedroht, die die Gemeinschaft der Zauberer verraten will. J. K. Rowling ist eine coole Erweiterung des \\\"Harry Potter\\\"-Universums gelungen.\", \r\n" + 
			"    \"slug\": \"making-phantastische-tierwesen\", \r\n" + 
			"    \"channels\": [], \r\n" + 
			"    \"language\": \"de\", \r\n" + 
			"    \"created\": \"2016-11-17T13:02:16\", \r\n" + 
			"    \"products\": [], \r\n" + 
			"    \"web_airdate\": \"2016-11-17\", \r\n" + 
			"    \"is_wide\": true\r\n" + 
			"}";
	@Test
	public void testFlatDataMapping() throws JAXBException {
		JsonMediaDao dao = new JsonMediaDao(JAXBContext.newInstance(Medium.class));
		try(StringReader stringReader = new StringReader(EXAMPLE_JSON)) {
			Medium medium = dao.readMedium(stringReader);
			Assert.assertEquals("Phantastische Tierwesen und wo sie zu finden sind", medium.getSubtitle());
			Assert.assertEquals(null, medium.getAirdate());
			Assert.assertEquals((Boolean)false, medium.isPremium());
			Assert.assertEquals(null, medium.getParentMedium());
			Assert.assertEquals((Boolean)true, medium.isAdAllowed());
			Assert.assertEquals((Integer)6162, medium.getId());
			Assert.assertEquals(medium.getUuid(), "763f71a0e2f94cd6835efcfd97a90174");
			Assert.assertEquals("Making of...", medium.getTitle());
			Assert.assertEquals("movie", medium.getType());
			Assert.assertEquals("2016-11-17T13:25:36", medium.getUpdated());
			Assert.assertTrue(medium.getDescription().startsWith("Außerdem die"));
			Assert.assertEquals((Integer)752826, medium.getDurationInMs());
			Assert.assertTrue(medium.getTeaser().startsWith("In New York"));
			Assert.assertEquals("making-phantastische-tierwesen", medium.getSlug());
			Assert.assertEquals("de", medium.getLanguage());
			Assert.assertEquals("2016-11-17T13:02:16", medium.getCreated());
			Assert.assertEquals((Boolean)true, medium.isWide());
			Assert.assertEquals(Arrays.asList("Making of", "Kino Trailer", "Kino"), medium.getTags());
			Assert.assertNotNull(medium.getImages());
			Assert.assertEquals(2, medium.getImages().size());
		}
	}  
}
