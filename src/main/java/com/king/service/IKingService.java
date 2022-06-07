package com.king.service;

import java.util.List;

import com.king.constants.KingDataStatus;
import com.king.constants.SortDirection;
import com.king.web.dto.KingDataResponse;
import com.king.web.dto.SelectItemDto;

public interface IKingService {

	List<SelectItemDto> getStatusList();

	KingDataResponse readKingData(Integer page, Integer pageSize, String name, KingDataStatus status, String sortField,
			SortDirection sortOrder);

}
