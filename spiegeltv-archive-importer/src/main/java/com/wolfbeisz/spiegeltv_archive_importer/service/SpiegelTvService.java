package com.wolfbeisz.spiegeltv_archive_importer.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class SpiegelTvService {
	public List<Integer> getAvailableMedia(String restApiVersion) throws IOException {
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
	
	public Reader getMediumReader(String restApiVersion, Integer mediumId) throws IOException {
		URL mediumUrl = new URL("http://spiegeltv-ivms2-restapi.s3.amazonaws.com/"+restApiVersion+"/restapi/media/"+mediumId+".json");
		return new InputStreamReader(mediumUrl.openStream());
	}
}
