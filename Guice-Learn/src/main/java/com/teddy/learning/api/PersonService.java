package com.teddy.learning.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path ("/person")
public interface PersonService {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonServiceInfo();

	@GET
	@Path ("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getPersonDetails(@PathParam("id") int id);
}
