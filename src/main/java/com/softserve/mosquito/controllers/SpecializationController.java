package com.softserve.mosquito.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import com.softserve.mosquito.enitities.Specialization;
import com.softserve.mosquito.services.SpecializationService;

@Path("/specializations")
public class SpecializationController {
	private SpecializationService specializationService = new SpecializationService();

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Specialization createSpecialization(Specialization specialization){
		return specializationService.create(specialization);
	}

	@GET
	@Path("/{specialization_id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Specialization getSpecializationById(@PathParam("specialization_id") Long specialization_id){
		return specializationService.read(specialization_id);
	}

	@PUT
	@Path("/{specialization_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Specialization updateSpecialization(Specialization specialization){
		return specializationService.update(specialization);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Specialization> getAllSpecializations(){
		return specializationService.readAll();
	}

}
