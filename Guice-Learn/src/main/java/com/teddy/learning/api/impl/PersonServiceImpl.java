package com.teddy.learning.api.impl;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.teddy.learning.api.PersonService;
import com.teddy.learning.audit.Audit;
import com.teddy.learning.db.TransactionLog;
import com.teddy.learning.event.PurgeCacheEvent;

@Singleton
public class PersonServiceImpl implements PersonService {
	
	@Inject
	TransactionLog txnLog;
	
	@Inject @Named("audit")
	TransactionLog auditLog;
	
	@Inject
	AsyncEventBus eventBus;

	@Audit
	public Response getPersonDetails(int id) {
		txnLog.logSomething("getPersonDetails:" + id);
		
		auditLog.logSomething("getPersonDetails:" + id);
		
		eventBus.post(new PurgeCacheEvent(id));
		
		return Response.ok(String.format("{ \"name\": \"Person%d\", \"id\": \"EMP00%d\" }", id, id)).build();
	}

	public Response getPersonServiceInfo() {
		return Response.ok("{ \"info\": \"This service serves Person info.\" }").build();
	}

}
