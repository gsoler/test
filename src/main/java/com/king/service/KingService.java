package com.king.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.king.constants.KingDataComparator;
import com.king.constants.KingDataStatus;
import com.king.constants.SortDirection;
import com.king.controller.KingDataResponse;
import com.king.dto.KingData;
import com.king.dto.KingDataOutput;
import com.king.repository.IKingRepository;

@Service
public class KingService implements IKingService {

	@Autowired
	private IKingRepository kingRepository;

	@Override
	public KingDataResponse readKingData(Integer page, Integer pageSize, String name, KingDataStatus status,
			String sortField, SortDirection sortDirection) {
		KingDataResponse response = new KingDataResponse();

		if (sortField == null || sortField.length() == 0) {
			sortField = "id";
		}
		if (sortDirection == null) {
			sortDirection = SortDirection.ASC;
		}

		KingDataOutput outputData = kingRepository.readServerData();
		List<KingData> kingDataList = outputData.getOutput().stream().filter(kingData -> {
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
			.collect(Collectors.toList());

		Integer pageOffset = (page - 1) * pageSize;
		response.setPageOffset(pageOffset);
		response.setNumResults(kingDataList.size());
		response.setNumPages(Math.floorDiv(response.getNumResults(), pageSize));
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
