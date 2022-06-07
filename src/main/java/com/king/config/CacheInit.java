package com.king.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import com.king.persistence.IKingRepository;

@Component
public class CacheInit implements ApplicationListener<ApplicationReadyEvent> {

	@Autowired
	private IKingRepository kingRepository;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		kingRepository.readServerData();
	}

}
