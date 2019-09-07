package com.shouwn.com.skhuservice.web;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ApiDataBuilder {

	private Map<String, Object> data = new HashMap<>();

	public ApiDataBuilder addData(String name, Object data) {
		this.data.put(name, data);
		return this;
	}

	public Map<String, Object> packaging() {
		return this.data;
	}
}
