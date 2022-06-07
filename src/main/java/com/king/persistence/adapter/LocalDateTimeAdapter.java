package com.king.persistence.adapter;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {

	public static final String ZONED_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	public static final String RETURN_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

	@Override
	public void write(JsonWriter out, LocalDateTime value) throws IOException {
		if (value != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(RETURN_DATE_TIME_FORMAT);
			out.value(value.atZone(ZoneId.systemDefault()).format(formatter));
		} else {
			String aux = null;
			out.value(aux);
		}
	}

	@Override
	public LocalDateTime read(JsonReader jsonReader) throws IOException {
		LocalDateTime dateTime = null;
		JsonToken peek = jsonReader.peek();

		String dateStr = jsonReader.nextString();
		if (dateStr != null && dateStr.length() > 0) {
			if (peek == JsonToken.STRING) {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(ZONED_DATE_TIME_FORMAT);
				dateTime = LocalDateTime.parse(dateStr, formatter);
			} else if (peek == JsonToken.NUMBER) {
				Double dateDouble = Double.parseDouble(dateStr);
				dateTime = LocalDateTime.ofInstant(Instant.ofEpochMilli(dateDouble.longValue()),
						ZoneId.systemDefault());
			}
		}

		return dateTime;
	}

}