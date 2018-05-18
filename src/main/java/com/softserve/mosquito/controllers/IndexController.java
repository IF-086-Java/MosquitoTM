package com.softserve.mosquito.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.softserve.mosquito.dtos.UserLoginDto;
import com.softserve.mosquito.dtos.UserRegistrationDto;
import com.softserve.mosquito.enitities.User;
import com.softserve.mosquito.services.UserService;
import com.softserve.mosquito.validation.UserValidation;

@Path("/")
public class IndexController {
    private UserService userService = new UserService();
    private UserValidation validation = new UserValidation(new UserService());

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
    public User login(UserLoginDto userLoginDto, @Context HttpServletRequest request) {
       if(validation.isLoginValid(userLoginDto)) {
    	   User user = userService.getUserByEmail(userLoginDto.getEmail());
    	   
    	   HttpSession session = request.getSession();
    	   session.setAttribute("user_id", user.getId());
    	   session.setAttribute("name", user.getFirstName() + " " + user.getLastName());
    	   
    	   return user;
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
