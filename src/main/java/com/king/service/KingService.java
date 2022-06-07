package com.king.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.constants.KingDataComparator;
import com.king.constants.KingDataStatus;
import com.king.constants.SortDirection;
import com.king.persistence.IKingRepository;
import com.king.persistence.domain.KingDataOutput;
import com.king.web.dto.KingDataDto;
import com.king.web.dto.KingDataResponse;
import com.king.web.dto.SelectItemDto;

@Service
public class KingService implements IKingService {

	private static final DateTimeFormatter RETURN_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	@Autowired
	private IKingRepository kingRepository;

	@Override
	public List<SelectItemDto> getStatusList() {
		List<SelectItemDto> returnList = new ArrayList<>();

		returnList.add(new SelectItemDto("", "All"));
		for (KingDataStatus kingDataStatus : KingDataStatus.values()) {
			returnList.add(new SelectItemDto(kingDataStatus.name(), kingDataStatus.label()));
		}

		return returnList;
	}

	@Override
	public KingDataResponse readKingData(Integer page, Integer pageSize, String name, KingDataStatus status,
			String sortField, SortDirection sortDirection) {
		KingDataResponse response = new KingDataResponse();

		if (sortField == null || sortField.length() == 0
				|| !KingDataComparator.FIELD_COMPARATOR.containsKey(sortField)) {
			sortField = KingDataComparator.FIELD_COMPARATOR.keySet().iterator().next();
		}
		if (sortDirection == null) {
			sortDirection = SortDirection.ASC;
		}

		KingDataOutput outputData = kingRepository.readServerData();
		List<KingDataDto> kingDataList = outputData.getOutput().stream().filter(kingData -> {
			boolean filterOk = true;
			if (name != null && name.length() > 0) {
				filterOk = kingData.getName().toLowerCase().contains(name.toLowerCase());
			}
			if (status != null) {
				filterOk = filterOk && kingData.getStatus().equals(status);
			}
			return filterOk;
		})
			.sorted(SortDirection.ASC.equals(sortDirection) ? KingDataComparator.FIELD_COMPARATOR.get(sortField)
					: KingDataComparator.FIELD_COMPARATOR.get(sortField).reversed())
			.map(kingData -> {
				KingDataDto returnObject = KingDataDto.builder()
					.id(kingData.getId())
					.name(kingData.getName())
					.description(kingData.getDescription())
					.delta(kingData.getDelta())
					.build();
				if (kingData.getStatus() != null) {
					returnObject.setStatus(kingData.getStatus().label());
				}
				if (kingData.getCreatedOn() != null) {
					returnObject.setCreatedOn(kingData.getCreatedOn().format(RETURN_DATE_FORMATTER));
				}
				return returnObject;
			})
			.collect(Collectors.toList());

		Integer pageOffset = (page - 1) * pageSize;
		response.setPageOffset(pageOffset);
		response.setNumResults(kingDataList.size());
		response.setNumPages((int) Math.ceil(response.getNumResults().doubleValue() / pageSize.doubleValue()));
		response.setMoreElements(page < response.getNumPages());

		if (pageOffset > kingDataList.size() - 1) {
			response.setResultList(new ArrayList<>());
		} else {
			response
				.setResultList(kingDataList.subList(pageOffset, Math.min(pageOffset + pageSize, kingDataList.size())));
		}
		return response;
	}

}
