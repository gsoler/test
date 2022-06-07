package com.king.web.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class KingDataDto implements Serializable {
	private static final long serialVersionUID = -3655482206310321204L;

	private Long id;
	private String status;
	private String createdOn;
	private String name;
	private String description;
	private Long delta;

}
