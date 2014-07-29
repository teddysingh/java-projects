package com.teddy.learning.cache.impl;

import com.teddy.learning.cache.CacheClient;

public class CouchClient implements CacheClient{

	String value = "default-";
	
	public String get(String key) {
		return getValue() + key;
	}

	public void set(String value) {
		// Do nothing as of now
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}	

}
