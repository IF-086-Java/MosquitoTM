package com.softserve.mosquito.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.enitities.User;

@Path("/")
public class IndexController {

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

        return new User(user.getUsername(), "", "", user.getPassword());
    }
    
}
