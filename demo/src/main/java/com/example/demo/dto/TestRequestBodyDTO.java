package com.example.demo.dto;

import lombok.Data;

@Data
public class TestRequestBodyDTO {
	private int id;
	private String message;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	 public TestRequestBodyDTO() {
	    }

}
