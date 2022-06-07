package com.king.test.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.king.constants.KingDataStatus;
import com.king.constants.SortDirection;
import com.king.persistence.IKingRepository;
import com.king.persistence.domain.KingData;
import com.king.persistence.domain.KingDataOutput;
import com.king.service.IKingService;
import com.king.service.KingService;
import com.king.web.dto.KingDataResponse;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ServiceTest {

	@TestConfiguration
	static class ServiceTestContextConfiguration {
		@Bean
		public IKingService kingService() {
			return new KingService();
		}
	}

	@Autowired
	private IKingService kingService;

	@MockBean
	private IKingRepository kingRepositoryMock;

	@BeforeEach
	public void setUp() {
		KingDataOutput output = new KingDataOutput();
		List<KingData> list = new ArrayList<>();

		KingData kingData = KingData.builder()
			.id(3L)
			.status(KingDataStatus.CANCELED)
			.name("Test03")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().plusDays(15L))
			.delta(152L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(1L)
			.status(KingDataStatus.COMPLETED)
			.name("Test01")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().minusDays(3L))
			.delta(1L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(2L)
			.status(KingDataStatus.ERROR)
			.name("Test02")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().minusMonths(2L))
			.delta(10L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(4L)
			.status(KingDataStatus.COMPLETED)
			.name("Test04")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().plusWeeks(5L))
			.delta(74L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(7L)
			.status(KingDataStatus.COMPLETED)
			.name("Test07")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().minusMonths(3L))
			.delta(748L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(9L)
			.status(KingDataStatus.ERROR)
			.name("Test09")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().minusDays(15L))
			.delta(574L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(12L)
			.status(KingDataStatus.CANCELED)
			.name("Test12")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().plusMonths(2L))
			.delta(724L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(10L)
			.status(KingDataStatus.CANCELED)
			.name("Test10")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().minusWeeks(9L))
			.delta(34L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(5L)
			.status(KingDataStatus.ERROR)
			.name("Test05")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().plusDays(2L))
			.delta(48L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(6L)
			.status(KingDataStatus.COMPLETED)
			.name("Test06")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().minusWeeks(8L))
			.delta(null)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(8L)
			.status(KingDataStatus.COMPLETED)
			.name("Test08")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().minusMonths(5L))
			.delta(0L)
			.build();
		list.add(kingData);

		kingData = KingData.builder()
			.id(11L)
			.status(KingDataStatus.ERROR)
			.name("Test11")
			.description("This is a test object")
			.createdOn(LocalDateTime.now().plusMonths(5L))
			.delta(7431L)
			.build();
		list.add(kingData);

		output.setOutput(list);

		assertNotNull(kingRepositoryMock);
		Mockito.when(kingRepositoryMock.readServerData()).thenReturn(output);
	}

	@Test
	public void whenNoFilter_thenDataShouldBeFound() {
		KingDataResponse response = kingService.readKingData(1, 10, null, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(2);
		assertThat(response.getResultList().size()).isEqualTo(10);
	}

	@Test
	public void whenPage2_thenDataShouldBeFound() {
		KingDataResponse response = kingService.readKingData(2, 10, null, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(2);
		assertThat(response.getResultList().size()).isEqualTo(2);
	}

	@Test
	public void whenBigPages_thenDataShouldBeFound() {
		KingDataResponse response = kingService.readKingData(1, 20, null, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(1);
		assertThat(response.getResultList().size()).isEqualTo(12);
	}

	@Test
	public void whenSmallPages_thenDataShouldBeFound() {
		KingDataResponse response = kingService.readKingData(1, 5, null, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(3);
		assertThat(response.getResultList().size()).isEqualTo(5);
	}

	@Test
	public void whenEmpty_thenDataShouldNotFound() {
		KingDataResponse response = kingService.readKingData(5, 5, null, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(3);
		assertThat(response.getResultList().size()).isEqualTo(0);
	}

	@Test
	public void whenValidName_thenDataShouldBeFound() {
		String name = "Test05";
		KingDataResponse response = kingService.readKingData(1, 10, name, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(1);
		assertThat(response.getNumPages()).isEqualTo(1);
		assertThat(response.getResultList().size()).isEqualTo(1);
		assertThat(response.getResultList().get(0).getName()).isEqualTo(name);
		assertThat(response.getResultList().get(0).getId()).isEqualTo(5L);
	}

	@Test
	public void whenInvalidName_thenDataShouldNotFound() {
		String name = "TestABC";
		KingDataResponse response = kingService.readKingData(1, 10, name, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(0);
		assertThat(response.getNumPages()).isEqualTo(0);
		assertThat(response.getResultList().size()).isEqualTo(0);
	}

	@Test
	public void whenValidStatus_DataShouldBeFound() {
		KingDataStatus status = KingDataStatus.COMPLETED;
		KingDataResponse response = kingService.readKingData(1, 10, null, status, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(5);
		assertThat(response.getNumPages()).isEqualTo(1);
		assertThat(response.getResultList().size()).isEqualTo(5);
	}

	@Test
	public void whenSortByName_thenDataShouldBeFound() {
		String sortField = "name";
		SortDirection sortDirection = SortDirection.ASC;
		KingDataResponse response = kingService.readKingData(1, 10, null, null, sortField, sortDirection);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(2);
		assertThat(response.getResultList().size()).isEqualTo(10);
		for (int index = 0; index < response.getResultList().size(); ++index) {
			assertThat(response.getResultList().get(index).getName())
				.isEqualTo("Test" + (index + 1 < 10 ? "0" + (index + 1) : index + 1));
			assertThat(response.getResultList().get(index).getId()).isEqualTo(index + 1);
		}
	}

	@Test
	public void whenSortByCreatedOn_thenDataShouldBeFound() {
		String sortField = "createdOn";
		SortDirection sortDirection = SortDirection.DESC;
		KingDataResponse response = kingService.readKingData(1, 10, null, null, sortField, sortDirection);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(2);
		assertThat(response.getResultList().size()).isEqualTo(10);
		assertThat(response.getResultList().get(0).getName()).isEqualTo("Test11");
		assertThat(response.getResultList().get(0).getId()).isEqualTo(11L);
		assertThat(response.getResultList().get(1).getName()).isEqualTo("Test12");
		assertThat(response.getResultList().get(1).getId()).isEqualTo(12L);
		assertThat(response.getResultList().get(2).getName()).isEqualTo("Test04");
		assertThat(response.getResultList().get(2).getId()).isEqualTo(4L);
		assertThat(response.getResultList().get(3).getName()).isEqualTo("Test03");
		assertThat(response.getResultList().get(3).getId()).isEqualTo(3L);
		assertThat(response.getResultList().get(4).getName()).isEqualTo("Test05");
		assertThat(response.getResultList().get(4).getId()).isEqualTo(5L);
		assertThat(response.getResultList().get(5).getName()).isEqualTo("Test01");
		assertThat(response.getResultList().get(5).getId()).isEqualTo(1L);
		assertThat(response.getResultList().get(6).getName()).isEqualTo("Test09");
		assertThat(response.getResultList().get(6).getId()).isEqualTo(9L);
		assertThat(response.getResultList().get(7).getName()).isEqualTo("Test06");
		assertThat(response.getResultList().get(7).getId()).isEqualTo(6L);
		assertThat(response.getResultList().get(8).getName()).isEqualTo("Test02");
		assertThat(response.getResultList().get(8).getId()).isEqualTo(2L);
		assertThat(response.getResultList().get(9).getName()).isEqualTo("Test10");
		assertThat(response.getResultList().get(9).getId()).isEqualTo(10L);
	}

	@Test
	public void whenInvalidSortField_thenDataShouldBeSortById() {
		String sortField = "test";
		SortDirection sortDirection = SortDirection.DESC;
		KingDataResponse response = kingService.readKingData(1, 10, null, null, sortField, sortDirection);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(2);
		assertThat(response.getResultList().size()).isEqualTo(10);
		assertThat(response.getResultList().get(0).getName()).isEqualTo("Test12");
		assertThat(response.getResultList().get(0).getId()).isEqualTo(12L);
		assertThat(response.getResultList().get(1).getName()).isEqualTo("Test11");
		assertThat(response.getResultList().get(1).getId()).isEqualTo(11L);
		assertThat(response.getResultList().get(2).getName()).isEqualTo("Test10");
		assertThat(response.getResultList().get(2).getId()).isEqualTo(10L);
		assertThat(response.getResultList().get(3).getName()).isEqualTo("Test09");
		assertThat(response.getResultList().get(3).getId()).isEqualTo(9L);
		assertThat(response.getResultList().get(4).getName()).isEqualTo("Test08");
		assertThat(response.getResultList().get(4).getId()).isEqualTo(8L);
		assertThat(response.getResultList().get(5).getName()).isEqualTo("Test07");
		assertThat(response.getResultList().get(5).getId()).isEqualTo(7L);
		assertThat(response.getResultList().get(6).getName()).isEqualTo("Test06");
		assertThat(response.getResultList().get(6).getId()).isEqualTo(6L);
		assertThat(response.getResultList().get(7).getName()).isEqualTo("Test05");
		assertThat(response.getResultList().get(7).getId()).isEqualTo(5L);
		assertThat(response.getResultList().get(8).getName()).isEqualTo("Test04");
		assertThat(response.getResultList().get(8).getId()).isEqualTo(4L);
		assertThat(response.getResultList().get(9).getName()).isEqualTo("Test03");
		assertThat(response.getResultList().get(9).getId()).isEqualTo(3L);
	}

	@Test
	public void whenAllFields_thenDataShouldBeFound() {
		String name = "Test08";
		KingDataStatus status = KingDataStatus.COMPLETED;
		String sortField = "name";
		SortDirection sortDirection = SortDirection.DESC;
		KingDataResponse response = kingService.readKingData(1, 10, name, status, sortField, sortDirection);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(1);
		assertThat(response.getNumPages()).isEqualTo(1);
		assertThat(response.getResultList().size()).isEqualTo(1);
		assertThat(response.getResultList().get(0).getName()).isEqualTo(name);
		assertThat(response.getResultList().get(0).getId()).isEqualTo(8L);
	}

	@Test
	public void whenInvalidPage_thenShouldThrowException() {
		assertThatExceptionOfType(IndexOutOfBoundsException.class)
			.isThrownBy(() -> kingService.readKingData(0, 10, null, null, null, null));
	}

	@Test
	public void whenZeroPageSize_thenDataShouldNotFound() {
		KingDataResponse response = kingService.readKingData(1, 0, null, null, null, null);

		assertNotNull(response);
		assertNotNull(response.getResultList());
		assertThat(response.getNumResults()).isEqualTo(12);
		assertThat(response.getNumPages()).isEqualTo(Integer.MAX_VALUE);
		assertThat(response.getResultList().size()).isEqualTo(0);
	}

}
