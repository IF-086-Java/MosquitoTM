package com.softserve.mosquito.controllers;


import javax.ws.rs.*;

@Path("/tasks")
public class LogWorkController {

    @GET
    @Path("/{taskId}/log-works")
    public String getLogWorksForTask(@PathParam("taskId") String taskId) {
        return "Got log-works for task with id " + taskId;
    }

    @POST
    @Path("/{taskId}/log-works")
    public String createLogWork(@PathParam("taskId") String taskId) {
        return "Created log-work for task with ID " + taskId;
    }

    @PUT
    @Path("/{taskId}/log-works")
    public String updateLogWorksForTask(@PathParam("taskId") String taskId) {
        return "Updated log-work for task with ID " + taskId;
    }

    @DELETE
    @Path("/{taskId}/log-works")
    public String deleteLogWorksForTask(@PathParam("taskId") String taskId) {
        return "Deleted log-work for task with ID " + taskId;
    }
}
