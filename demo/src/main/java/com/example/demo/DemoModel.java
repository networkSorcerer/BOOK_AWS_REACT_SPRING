package com.example.demo;

import io.micrometer.common.lang.NonNull;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder

@RequiredArgsConstructor
public class DemoModel {

	@NonNull
	private String id;
}
