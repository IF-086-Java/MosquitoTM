package com.softserve.mosquito.controllers;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.services.UserService;
import com.softserve.mosquito.validation.UserValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.softserve.entities.*;

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
    public Response login(UserLoginDto userLoginDto, @Context HttpServletRequest request) {
       if(validation.isLoginValid(userLoginDto)) {
    	   User user = userService.getUserByEmail(userLoginDto.getEmail());
    	   
    	   // Start session with authorized user
    	   HttpSession session = request.getSession();
    	   session.setAttribute("user_id", user.getId());
    	   session.setAttribute("name", user.getFirstName() + " " + user.getLastName());
    	   
    	   //OR request.sendRedirect("/mytasks.html") OR Response.temporaryRedirect(location - "/mytasks.html")).build()
    	   return Response.status(Response.Status.OK).entity(user).build();
       }else {
    	   //OR request.sendRedirect("/login.html") OR Response.temporaryRedirect(location - "/login.html").build()
    	   return Response.status(Response.Status.UNAUTHORIZED).entity(new User()).build();
       }
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
