package com.softserve.mosquito.controllers;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/task-log-works")
public class LogWorkController {

    @GET
    @Path("/{task_id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getLogWorksForTask(@PathParam("task_id") Long taskId) {
        return "Got log-works for task with id " + taskId;
    }

    @POST
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createLogWork(@PathParam("task_id") Long taskId) {
        return "Created log-work for task with ID " + taskId;
    }

    @PUT
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateLogWorksForTask(@PathParam("task_id") Long taskId) {
        return "Updated log-work for task with ID " + taskId;
    }

    @DELETE
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteLogWorksForTask(@PathParam("task_id") Long taskId) {
        return "Deleted log-work for task with ID " + taskId;
    }
}
