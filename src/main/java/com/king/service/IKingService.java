package com.king.service;

import com.king.constants.KingDataStatus;
import com.king.constants.SortDirection;
import com.king.controller.KingDataResponse;

public interface IKingService {

	KingDataResponse readKingData(Integer page, Integer pageSize, String name, KingDataStatus status, String sortField,
			SortDirection sortOrder);

}
