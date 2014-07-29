package com.teddy.learning.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.teddy.learning.api.PersonService;
import com.teddy.learning.api.impl.PersonServiceImpl;
import com.teddy.learning.audit.Audit;
import com.teddy.learning.audit.interceptor.AuditInterceptor;
import com.teddy.learning.cache.CacheClient;
import com.teddy.learning.db.TransactionLog;
import com.teddy.learning.db.impl.mongo.MongoTransactionLog;
import com.teddy.learning.db.impl.mysql.MySQLTransactionLog;
import com.teddy.learning.event.AsyncCacheEventsHandler;

public class GuiceServletConfig extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {

			// when you want only one instance (per Injector) to
			// be reused for all injections for that binding.
			@Provides
			@Singleton
			public AsyncEventBus getEventBus() {
				Executor asyncExecutor = Executors.newFixedThreadPool(2);
				return new AsyncEventBus(asyncExecutor);
			}

			protected void configureServlets() {
				bind(PersonService.class).to(PersonServiceImpl.class);

				// Linked Bindings
				bind(TransactionLog.class).to(MySQLTransactionLog.class);

				// Binding Annotations example @Named
				bind(TransactionLog.class).annotatedWith(Names.named("audit"))
						.to(MongoTransactionLog.class);

				// Untargetted Binding
				bind(AsyncCacheEventsHandler.class).asEagerSingleton();
				
				// Provider based binding
				bind(CacheClient.class).toProvider(CouchClientProvider.class);
				
				bindInterceptor(Matchers.any(), Matchers.annotatedWith(Audit.class)
						, new AuditInterceptor());

				serve("/*").with(GuiceContainer.class);
			}
		});
	}
}
