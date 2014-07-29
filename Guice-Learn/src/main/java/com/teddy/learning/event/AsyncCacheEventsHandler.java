package com.teddy.learning.event;

import java.util.Date;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.Subscribe;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.teddy.learning.cache.CacheClient;

@Singleton
public class AsyncCacheEventsHandler {
	
	@Inject
	protected AsyncEventBus eventBus;
	
	// Field level injection
	@Inject
	public CacheClient cache;
	
	// Constructor Injection
	@Inject
	public AsyncCacheEventsHandler(AsyncEventBus eventBus) {
		System.out.println("Registering eventHandler with eventBus ...");
		eventBus.register(this);
	}
	
	@Subscribe
	public void listenPurgeCache(PurgeCacheEvent pce) {
		System.out.println(new Date() + " - Performing some cache operation asynchronously");
		System.out.println(new Date() + " - Deleting entry: " + cache.get("couch"+pce.id));
	}
	
	@Subscribe
	public void listenDeadEvent(DeadEvent de) {
		System.out.println("Dead Even receieved ...");
	}
}
