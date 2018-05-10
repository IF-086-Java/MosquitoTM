package com.softserve.mosquito.controllers;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/tasks")
public class LogWorkController {

    @GET
    @Path("/{taskId}/log-works")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLogWorksForTask(@PathParam("taskId") Long taskId) {
        return "Got log-works for task with id " + taskId;
    }

    @POST
    @Path("/{taskId}/log-works")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createLogWork(@PathParam("taskId") Long taskId) {
        return "Created log-work for task with ID " + taskId;
    }

    @PUT
    @Path("/{taskId}/log-works")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateLogWorksForTask(@PathParam("taskId") Long taskId) {
        return "Updated log-work for task with ID " + taskId;
    }

    @DELETE
    @Path("/{taskId}/log-works")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteLogWorksForTask(@PathParam("taskId") Long taskId) {
        return "Deleted log-work for task with ID " + taskId;
    }
}
