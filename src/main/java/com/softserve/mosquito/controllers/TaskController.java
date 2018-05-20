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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.softserve.entities.Task;
import com.softserve.mosquito.repositories.TaskRepo;

/**
 * Task controller : JAX-RS + MySql + JSON test.
 */

@Path("/tasks")
public class TaskController {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
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
    @Path("/worker-id")
    @Produces(MediaType.TEXT_PLAIN)
    public String getWorkerTasks(@QueryParam("workerId") Long workerId) {
        return "User tasks " + workerId;
    }

    @GET
    @Path("/owner-id")
    @Produces(MediaType.TEXT_PLAIN)
    public String getOwnerTasks(@QueryParam("ownerId") Long ownerId) {
        return "Owner id " + ownerId;
    }

    @GET
    @Path("/{taskId}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTaskById(@PathParam("taskId") Long taskId) {
        return "Got task with id " + taskId;
    }

    @PUT
    @Path("/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String updateTask(@PathParam("taskId") Long taskId,
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
    @Path("/parent")
    @Produces(MediaType.TEXT_PLAIN)
    public String getSubTaskOrProject(@QueryParam("parentId") Long parentId) {
        return "Got subTask/project for ID " + parentId;
    }

    @DELETE
    @Path("/{taskId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteTask(@PathParam("taskId") Long taskId) {
        return "Deleted task with id " + taskId;
    }

    @GET
    @Path("/status")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTaskByStatus(@QueryParam("taskStatus") Long statusId) {
        return "Got task with status id " + statusId;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Task> testAllTasks() {
        TaskRepo taskRepo = new TaskRepo();
        return taskRepo.readAll();
    }
}
