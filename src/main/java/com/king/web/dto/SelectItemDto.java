package com.king.web.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelectItemDto implements Serializable {
	private static final long serialVersionUID = 6549782030984884667L;

	private String value;
	private String label;

}
