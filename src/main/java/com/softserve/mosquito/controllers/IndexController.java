package com.softserve.mosquito.controllers;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.validation.UserValidation;

@Path("/")
public class IndexController {

    private UserValidation validation = new UserValidation();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String testIndex() {

        return "Hello Mosquito <br>" +
                "<a href = \"/tasks\">Get tasks </a>";
    }
    
    /**
     * @param user : JSON body from Front-end in POST
     * @return JSON to Front 
     * */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User login(UserLoginDto user) {
       if(validation.isLoginValid(user)) {
    	   return new User(user.getEmail(), "", "", user.getPassword());
       }else {
    	   return null;
       }
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registration(UserRegistrationDto user){
        return  null;
    }
    
}
