package com.king.controller.endpoint;

import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.king.constants.KingDataStatus;
import com.king.constants.SortDirection;
import com.king.controller.KingDataResponse;
import com.king.service.IKingService;

@RestController
@CrossOrigin
@Validated
public class KingController {

	@Autowired
	private IKingService kingService;

	@GetMapping(path = "/king-data")
	public ResponseEntity<KingDataResponse> readKingData(@RequestParam @Min(1) int page,
			@RequestParam @Min(5) int pageSize, @RequestParam(required = false) String name,
			@RequestParam(required = false) KingDataStatus status, @RequestParam(required = false) String sortField,
			@RequestParam(required = false) SortDirection sortDirection) {
		KingDataResponse responseObject = kingService.readKingData(page, pageSize, name, status, sortField,
				sortDirection);
		return new ResponseEntity<>(responseObject, HttpStatus.OK);
	}

}
