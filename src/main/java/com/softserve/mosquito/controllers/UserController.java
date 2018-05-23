package com.softserve.mosquito.controllers;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;

@Path("/users")
public class UserController {
	
	private UserService userService = new UserService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User createUser(User user) {
		return userService.create(user);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers() {
		return userService.readAll();
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public User getUserById(@PathParam("userId") long userId) {
		return userService.read(userId);
	}
	
	@PUT
	@Path("/{userId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(@PathParam("userId") long userId, UserRegistrationDto userDto) {
		User user = new User();
		//TODO: Modify userService.update(User) => userService.update(UserUpdateDtp)
		return userService.update(user);
	}
	
	@DELETE
	@Path("/{userId}")
	public Response deleteUserById(@PathParam("userId") long userId) {
		userService.delete(userService.read(userId));
		return Response.ok().entity("Deleted user with ID: " + userId).build();
	}
	
	/*
	 * Return all users with specific specialization
	 * @param: specializationId - path parameter: users/specializations/2 (2 - Project Manager)
	 */
	@GET
	@Path("/specializations/{specializationId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsersBySpecializationId(@PathParam("specializationId") long specializationId) {
		//TODO: Create in UserService method - getUsersBySpecialization(long specializationId)
		return Response.ok().entity("All users with Specialization " + specializationId).build();
	}
	
}
