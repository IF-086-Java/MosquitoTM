package com.softserve.mosquito.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/specializations")
public class SpecializationController {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllSpecializations() {
		return "Returned list of Specializations";
	}
}
