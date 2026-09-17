package com.evoluxiontech.evoxa_hiring_portal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ResponseDto {
	private String message;
	private Object data;

}
