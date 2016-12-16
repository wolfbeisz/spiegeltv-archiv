package com.wolfbeisz.spiegeltv_archive_importer.dao;

import java.io.InputStream;
import java.io.Reader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

import com.wolfbeisz.spiegeltv_archive_importer.model.Medium;

public class JsonMediaDao {
	public Medium readMedium(Reader input) {
		try (JsonReader jsonReader = Json.createReader(input)) {
			return readMediumInternal(jsonReader);
		}
	}
	
	private Medium readMediumInternal(JsonReader jsonReader) {
		JsonObject jsonMedium = jsonReader.readObject();
		Medium m = new Medium();
		m.setUuid(jsonMedium.getString("uuid"));
		return m;
	}

	public Medium readMedium(InputStream input) {
		try (JsonReader jsonReader = Json.createReader(input)) {
			return readMediumInternal(jsonReader);
		}
	}
	
}
