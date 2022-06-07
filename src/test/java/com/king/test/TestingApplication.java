package com.king.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.king.controller.endpoint.KingController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestingApplication {

	@LocalServerPort
	private int port;

	@Autowired
	private KingController kingController;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void contextLoads() {
		assertThat(kingController).isNotNull();
	}

	@Test
	public void checkErrorMessages() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data", String.class))
			.contains("\"status\":400");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=0", String.class))
			.contains("\"status\":400");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?pageSize=1", String.class))
			.contains("\"status\":400");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=0&pageSize=0",
				String.class)).contains("readKingData.page").contains("readKingData.pageSize");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=1&pageSize=0",
				String.class)).contains("readKingData.pageSize");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=0&pageSize=5",
				String.class)).contains("readKingData.page");
	}

	@Test
	public void checkOkMessages() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=1&pageSize=10",
				String.class)).contains("\"pageOffset\":0");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=5&pageSize=20",
				String.class)).contains("\"pageOffset\":80");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=1&pageSize=20&name=sus"
				+ "&status=COMPLETED&sortField=name&sortDirection=ASC", String.class)).contains("\"pageOffset\":0");
		assertThat(
				this.restTemplate.getForObject("http://localhost:" + port + "/king-data?page=1&pageSize=20&name=qwerty"
						+ "&status=ERROR&sortField=createdOn&sortDirection=DESC", String.class))
							.contains("\"numResults\":0");
	}

}
