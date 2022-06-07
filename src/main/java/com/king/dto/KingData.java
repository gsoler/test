package com.king.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.king.constants.KingDataStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class KingData implements Serializable {
	private static final long serialVersionUID = -3655482206310321204L;

	private Long id;
	private KingDataStatus status;
	private LocalDateTime createdOn;
	private String name;
	private String description;
	private Long delta;

}
