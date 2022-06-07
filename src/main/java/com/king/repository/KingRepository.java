package com.king.repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.king.adapter.LocalDateTimeAdapter;
import com.king.adapter.LongAdapter;
import com.king.dto.KingDataOutput;

@Repository
public class KingRepository implements IKingRepository {
	private Logger logger = LoggerFactory.getLogger(KingRepository.class);

	private static final String SERVER_URL = "https://storage.googleapis.com/king-airnd-recruitment-sandbox-data/data.json";

	@Override
	@Cacheable(value = "kingData")
	public KingDataOutput readServerData() {
		KingDataOutput dataOutput = null;

		try {
			StringBuilder text = new StringBuilder();
			URL serverURL = new URL(SERVER_URL);
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
			logger.error(e.getLocalizedMessage(), e);
		}
		return dataOutput;
	}

}
