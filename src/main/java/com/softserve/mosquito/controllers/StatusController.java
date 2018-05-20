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

import com.softserve.entities.Status;
import com.softserve.mosquito.services.StatusService;

@Path("/statuses")
public class StatusController {
    private StatusService statusService = new StatusService() ;

    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({MediaType.APPLICATION_JSON})
    public Status createStatus(@FormParam("status_id") Byte statusId,
                               @FormParam("title") String title) {
        return statusService.create(new Status(statusId, title));
    }

    @GET
    @Path("/{status_id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Status getStatusById(@PathParam("status_id") Long id){ return statusService.read(id);
    }


    @PUT
    @Path("/{status_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Status updateStatus(@PathParam("status_id") Byte id,
                               @FormParam("title") String title) {
        return statusService.update(new Status(id, title));
    }

    @DELETE
    @Path("/{status_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deletePriority(@PathParam("status_id") Byte id) {
        statusService.delete(new Status(id));
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Status> getAllStatuses(){
        return statusService.readAll();
    }
}

