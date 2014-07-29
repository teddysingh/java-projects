package com.teddy.learning.config;

import com.google.inject.Provider;
import com.teddy.learning.cache.CacheClient;
import com.teddy.learning.cache.impl.CouchClient;

public class CouchClientProvider implements Provider<CacheClient> {

	public CacheClient get() {
		return new CouchClient();
	}

}
