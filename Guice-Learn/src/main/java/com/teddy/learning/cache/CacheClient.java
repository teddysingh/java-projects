package com.teddy.learning.cache;

public interface CacheClient {
	public String get(String key);
	public void set(String value);
}
