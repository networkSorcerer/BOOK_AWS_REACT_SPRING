package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseDTO<T> {
	private String error;
	private List<T> data;
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	public ResponseDTO(String error, List<T> data) {
		super();
		this.error = error;
		this.data = data;
	}
	
	
	
}
