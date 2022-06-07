package com.king.test.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testOk() throws Exception {
		mockMvc.perform(get("/king-data").contentType("application/json").param("page", "1").param("pageSize", "20"))
			.andExpect(status().isOk());
		mockMvc
			.perform(get("/king-data").contentType("application/json")
				.param("page", "1")
				.param("pageSize", "20")
				.param("name", "test"))
			.andExpect(status().isOk());
		mockMvc
			.perform(get("/king-data").contentType("application/json")
				.param("page", "1")
				.param("pageSize", "20")
				.param("name", "test")
				.param("status", "COMPLETED"))
			.andExpect(status().isOk());
		mockMvc
			.perform(get("/king-data").contentType("application/json")
				.param("page", "1")
				.param("pageSize", "20")
				.param("name", "test")
				.param("status", "COMPLETED")
				.param("sortField", "createdOn"))
			.andExpect(status().isOk());
		mockMvc
			.perform(get("/king-data").contentType("application/json")
				.param("page", "1")
				.param("pageSize", "20")
				.param("name", "test")
				.param("status", "COMPLETED")
				.param("sortField", "createdOn")
				.param("sortDirection", "ASC"))
			.andExpect(status().isOk());
		mockMvc
			.perform(get("/king-data").contentType("application/json")
				.param("page", "1")
				.param("pageSize", "20")
				.param("name", "test")
				.param("status", "COMPLETED")
				.param("sortField", "delta")
				.param("sortDirection", "ASC"))
			.andExpect(status().isOk());
	}

	@Test
	public void testKo() throws Exception {
		mockMvc.perform(get("/king-data").contentType("application/json")).andExpect(status().isBadRequest());
		mockMvc.perform(get("/king-data").contentType("application/json").param("page", "0"))
			.andExpect(status().isBadRequest());
		mockMvc.perform(get("/king-data").contentType("application/json").param("pageSize", "1"))
			.andExpect(status().isBadRequest());
		mockMvc.perform(get("/king-data").contentType("application/json").param("pageSize", "10"))
			.andExpect(status().isBadRequest());
		mockMvc.perform(get("/king-data").contentType("application/json").param("page", "1"))
			.andExpect(status().isBadRequest());
		mockMvc.perform(get("/king-data").contentType("application/json").param("page", "1").param("pageSize", "1"))
			.andExpect(status().isBadRequest());
		mockMvc
			.perform(get("/king-data").contentType("application/json")
				.param("page", "1")
				.param("pageSize", "20")
				.param("name", "test")
				.param("status", "COMPLETED")
				.param("sortField", "createdOn")
				.param("sortDirection", "OSC"))
			.andExpect(status().isBadRequest());
		mockMvc.perform(post("/king-data").contentType("application/json").param("page", "1").param("pageSize", "20"))
			.andExpect(status().isMethodNotAllowed());
		mockMvc.perform(put("/king-data").contentType("application/json").param("page", "1").param("pageSize", "20"))
			.andExpect(status().isMethodNotAllowed());
		mockMvc.perform(delete("/king-data").contentType("application/json").param("page", "1").param("pageSize", "20"))
			.andExpect(status().isMethodNotAllowed());
		mockMvc.perform(patch("/king-data").contentType("application/json").param("page", "1").param("pageSize", "20"))
			.andExpect(status().isMethodNotAllowed());
	}

	@Test
	public void readStatusListOk() throws Exception {
		mockMvc.perform(get("/status-list").contentType("application/json")).andExpect(status().isOk());
	}

	@Test
	public void readStatusListKo() throws Exception {
		mockMvc.perform(post("/status-list").contentType("application/json")).andExpect(status().isMethodNotAllowed());
		mockMvc.perform(put("/status-list").contentType("application/json")).andExpect(status().isMethodNotAllowed());
		mockMvc.perform(delete("/status-list").contentType("application/json"))
			.andExpect(status().isMethodNotAllowed());
		mockMvc.perform(patch("/status-list").contentType("application/json")).andExpect(status().isMethodNotAllowed());
	}

}
