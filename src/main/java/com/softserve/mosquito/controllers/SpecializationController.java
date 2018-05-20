package com.softserve.mosquito.controllers;

import com.softserve.mosquito.enitities.Specialization;
import com.softserve.mosquito.services.SpecializationService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/specializations")
public class SpecializationController {
	private SpecializationService specializationService = new SpecializationService();

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})
	public Specialization createSpecialization(@FormParam("specialization_id") Byte specializationId,
											   @FormParam("title") String title){
		return specializationService.create(new Specialization(specializationId, title));
	}

	@GET
	@Path("/{specialization_id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Specialization getSpecializationById(@PathParam("specialization_id") Long specialization_id){
		return specializationService.read(specialization_id);
	}

	@PUT
	@Path("/{specialization_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Specialization updateSpecialization(@PathParam("specialization_id") Byte specializationId,
											   @FormParam("title") String title){
		return specializationService.update(new Specialization(specializationId, title));
	}

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<Specialization> getAllSpecializations(){
		return specializationService.readAll();
	}

}
