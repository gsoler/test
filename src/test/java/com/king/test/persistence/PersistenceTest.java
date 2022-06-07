package com.king.test.persistence;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.king.persistence.IKingRepository;
import com.king.persistence.domain.KingDataOutput;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersistenceTest {

	@Autowired
	private IKingRepository kingRepository;

	@Test
	public void readKingData() {
		assertNotNull(kingRepository);
		KingDataOutput foundData = kingRepository.readServerData();
		assertNotNull(foundData);
		assertNotNull(foundData.getOutput());
	}

}
