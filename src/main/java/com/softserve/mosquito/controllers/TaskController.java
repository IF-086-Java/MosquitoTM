package com.softserve.mosquito.controllers;

import com.softserve.mosquito.enitities.Task;
import com.softserve.mosquito.repositories.TaskRepo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Task controller : JAX-RS + MySql + JSON test.
 */

@Path("/tasks")
public class TaskController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String createSubTaskOrProject(@QueryParam("parentId") Long parentId,
                                         @FormParam("task_name") String taskName,
                                         @FormParam("priority") String priority,
                                         @FormParam("specialization") String specialization,
                                         @FormParam("assignee") String assignee,
                                         @FormParam("estimation") String estimation) {
        return "Created task with " + parentId + " " + taskName
                + " " + priority + " " + specialization + " " + assignee
                + " " + estimation;
    }

    @GET
    @Path("/workers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWorkerTasks(@QueryParam("worker_id") Long workerId) {
        return "User tasks " + workerId;
    }

    @GET
    @Path("/owners")
    @Produces(MediaType.APPLICATION_JSON)
    public String getOwnerTasks(@QueryParam("owner_id") Long ownerId) {
        return "Owner id " + ownerId;
    }

    @GET
    @Path("/{task_id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTaskById(@PathParam("task_id") Long taskId) {
        return "Got task with id " + taskId;
    }

    @PUT
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String updateTask(@PathParam("task_id") Long taskId,
                             @FormParam("task_name") String taskName,
                             @FormParam("priority") String priority,
                             @FormParam("specialization") String specialization,
                             @FormParam("assignee") String assignee,
                             @FormParam("estimation") String estimation,
                             @FormParam("status") String status) {
        return "Task updated " + taskId + " " + taskName
                + " " + priority + " " + specialization + " " + assignee
                + " " + estimation + " " + status;
    }

    @GET
    @Path("/parents")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSubTaskOrProject(@QueryParam("parent_id") Long parentId) {
        return "Got subTask/project for ID " + parentId;
    }

    @DELETE
    @Path("/{task_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteTask(@PathParam("task_id") Long taskId) {
        return "Deleted task with id " + taskId;
    }

    @GET
    @Path("/statuses")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTaskByStatus(@QueryParam("task_status") Long statusId) {
        return "Got task with status id " + statusId;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Task> testAllTasks() {
        TaskRepo taskRepo = new TaskRepo();
        return taskRepo.readAll();
    }
}
