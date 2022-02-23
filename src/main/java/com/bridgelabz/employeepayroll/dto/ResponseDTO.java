package com.bridgelabz.employeepayroll.dto;

import lombok.Data;

//Created ResponseDTO class to get output in form of message with data
@Data
public class ResponseDTO {
	private String messsage;
	private Object data;
	
	public ResponseDTO(String messsage, Object data) {
		super();
		this.messsage = messsage;
		this.data = data;
	}
}