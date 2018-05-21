package com.softserve.mosquito.controllers;

import com.softserve.mosquito.enitities.Status;
import com.softserve.mosquito.services.StatusService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

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

