package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.validation.Validation;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class IndexController {

    private Validation validation = new Validation();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String testIndex() {

        return "Hello Mosquito <br>" +
                "<a href = \"/tasks\">Get tasks </a>";
    }
    
    /**
     * Just login test. Override this method
     * @param user : JSON body from Front-end in POST
     * @return JSON to Front 
     * */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(UserLoginDto user) {
        validation.loginValidation(user);
        return new User(user.getUsername(), "", "", "", user.getPassword());
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registration(@Valid UserRegistrationDto user){

        if (validation.registerValidation(user))
            return Response.status(Response.Status.CREATED).entity(user).build();

        return  Response.status(Response.Status.FORBIDDEN).entity(user).build();
    }
    
}
