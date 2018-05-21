package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;
import com.softserve.mosquito.validation.UserValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class IndexController {
    private UserService userService = new UserService();
    private UserValidation validation = new UserValidation();

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String testIndex() {

        return "Hello Mosquito <br>" +
                "<a href = \"/tasks\">Get tasks </a>";
    }
    
    /**
     * 
     * */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@Valid UserLoginDto userLoginDto, @Context HttpServletRequest request) {
       if(validation.isValidCredentials(userLoginDto)) {
    	   
    	   // Start session with authorized user
    	   User user = userService.getUserByEmail(userLoginDto.getEmail());
    	   HttpSession session = request.getSession();
    	   session.setAttribute("user_id", user.getId());
    	   
    	   return Response.status(Response.Status.OK).entity(user).build();
       }else {
    	   return Response.status(Response.Status.UNAUTHORIZED).build();
       }
    }

    @POST
    @Path("/registration")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registration(@Valid UserRegistrationDto user){

        if (validation.registerValidation(user))
            return Response.ok().entity(user).build();

        return  Response.status(Response.Status.FORBIDDEN).entity(user).build();
    }
    
}
