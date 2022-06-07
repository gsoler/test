package com.king.web.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KingDataResponse implements Serializable {
	private static final long serialVersionUID = 3274476930441699271L;

	private Integer pageOffset;
	private Integer numResults;
	private List<KingDataDto> resultList;
	private boolean moreElements;
	private Integer numPages;

}