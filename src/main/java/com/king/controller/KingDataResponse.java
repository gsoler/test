package com.king.controller;

import java.io.Serializable;
import java.util.List;

import com.king.dto.KingData;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KingDataResponse implements Serializable {
	private static final long serialVersionUID = 3274476930441699271L;

	private Integer pageOffset;
	private Integer numResults;
	private List<KingData> resultList;
	private boolean moreElements;
	private Integer numPages;

}