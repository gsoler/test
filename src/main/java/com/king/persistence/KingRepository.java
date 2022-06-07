package com.king.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.king.persistence.adapter.LocalDateTimeAdapter;
import com.king.persistence.adapter.LongAdapter;
import com.king.persistence.domain.KingDataOutput;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class KingRepository implements IKingRepository {

	@Value("${server-url}")
	private String serverUrl;

	@Override
	@Cacheable(value = "kingData")
	public KingDataOutput readServerData() {
		KingDataOutput dataOutput = null;

		try {
			StringBuilder text = new StringBuilder();
			URL serverURL = new URL(serverUrl);
			URLConnection serverConnection = serverURL.openConnection();
			BufferedReader in = new BufferedReader(new InputStreamReader(serverConnection.getInputStream()));

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				text.append(inputLine);
			}

			Gson gson = new GsonBuilder().registerTypeHierarchyAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
				.registerTypeHierarchyAdapter(Long.class, new LongAdapter())
				.create();
			dataOutput = gson.fromJson(text.toString(), KingDataOutput.class);
		} catch (IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		return dataOutput;
	}

}
