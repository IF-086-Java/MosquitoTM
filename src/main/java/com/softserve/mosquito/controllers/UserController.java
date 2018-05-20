package com.softserve.mosquito.controllers;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.repositories.UserRepo;

@Path("/users")
public class UserController {

    @GET
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("user_id") long userId) {
        return "Returned user with ID: " + userId;
    }

    @PUT
    @Path("/{user_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(@PathParam("user_id") long userId,
                             @FormParam("email") String email,
                             @FormParam("first_name") String firstName,
                             @FormParam("last_name") String lastName,
                             @FormParam("specializations") List<String> specializations,
                             @FormParam("password") String password) {

        return "Updated USER - " + firstName + " " + lastName
                + " EMAIL: " + email
                + " SPECIALIZATION_IDs: " + specializations;
    }

    @DELETE
    @Path("/{user_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUserById(@PathParam("user_id") long userId) {
        return Response.ok().entity("Deleted user with ID: " + userId).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersBySpecializationId(@QueryParam("specialization") String specialization) {

        if (specialization == null) {
            return Response.ok().entity(new UserRepo().readAll()).build();
        }

        return Response.ok().entity("Got all users with Specialization " + specialization).build();
    }

}
