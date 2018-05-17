package com.softserve.mosquito.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.repositories.UserRepo;

@Path("/users")
public class UserController {
	
	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String creareUser(@FormParam("email") String email,
			@FormParam("first_name") String firstName,
			@FormParam("last_name") String lastName,
			@FormParam("specializations") List<String> specializations,
			@FormParam("password") String password) {
		String testResult = "Created USER - " + firstName + " " + lastName 
				+ " EMAIL: "  + email + " SPECIALIZATIN_IDs" + specializations;
		
		return testResult;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		
		//TODO: Delete. Only for testing
		return new UserRepo().readAll();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUserById(@PathParam("userId") long userId) {
		return "Returned user with ID: " + userId;
	}
	
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUser(@PathParam("userId") long userId,
			@FormParam("email") String email,
			@FormParam("first_name") String firstName,
			@FormParam("last_name") String lastName,
			@FormParam("specializations") List<String> specializations,
			@FormParam("password") String password) {
		String testResult = "Updated USER - " + firstName + " " + lastName 
				+ " EMAIL: " + email 
				+ " SPECIALIZATION_IDs: " + specializations;
		
		return testResult;
	}
	
	@DELETE
	@Path("/{userId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUserById(@PathParam("userId") long userId) {
		return "Deleted user with ID: " + userId;
	}
	
	@GET
	@Path("/specializations/{specializationId}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUsersBySpecializationId(@PathParam("specializationId") long specializationId) {
		return "Got all users with Specialization(ID: " + specializationId + ")" ;
	}
	
	
	
}
