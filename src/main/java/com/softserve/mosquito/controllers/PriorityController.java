package com.softserve.mosquito.controllers;

import com.softserve.mosquito.services.PriorityService;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import com.softserve.entities.*;

@Path("/priorities")
public class PriorityController {
    private PriorityService priorityService = new PriorityService();

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
    public Priority createPriority(@FormParam("id") Byte id,
                                   @FormParam("title") String title){
        return priorityService.create(new Priority(id, title));
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Priority getPriorityById(@PathParam("id") Long id){
        return priorityService.read(id);
    }


    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Priority updatePriority(@PathParam("id") Byte id,
                                   @FormParam("title") String title) {
        return priorityService.update(new Priority(id, title));
    }

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePriority(@PathParam("id") Byte id) {
        priorityService.delete(new Priority(id));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Priority> getAllPriority(){
        return priorityService.readAll();
    }
}
