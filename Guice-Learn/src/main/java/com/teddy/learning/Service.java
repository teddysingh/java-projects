package com.teddy.learning;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.WebAppContext;

public class Service {
	public static void main(String[] args) throws Exception {

		WebAppContext webAppContext = new WebAppContext();
		
		webAppContext.setWar(
				"/Users/vsinha/eclipse_workspace/Guice-Learn/build/libs/Guice-Learn-1.0.war");
		webAppContext.setParentLoaderPriority(true);
		webAppContext.setContextPath("/");

        Server server = new Server(9999);
        server.setHandler(webAppContext);
        server.start();
        server.join();

    }
}
