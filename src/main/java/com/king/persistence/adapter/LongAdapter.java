package com.king.persistence.adapter;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class LongAdapter extends TypeAdapter<Long> {

	@Override
	public void write(JsonWriter out, Long value) throws IOException {
		if (value != null) {
			out.value(value);
		} else {
			String aux = null;
			out.value(aux);
		}
	}

	@Override
	public Long read(JsonReader jsonReader) throws IOException {
		Long value = null;

		String valueStr = jsonReader.nextString();
		if (valueStr != null && valueStr.length() > 0) {
			value = Long.parseLong(valueStr);
		}

		return value;
	}

}