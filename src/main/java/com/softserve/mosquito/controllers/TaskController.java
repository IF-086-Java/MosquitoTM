package com.softserve.mosquito.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.softserve.mosquito.enitities.Task;

/**
 * Task controller : JAX-RS + MySql + JSON test.
 */

@Path("/tasks")
public class TaskController {
    @Context
    ServletContext context;

    @POST
    @Consumes("text/plain")
    public String createSubTaskOrProject(@QueryParam("parentId") Long parentId) {
        return "Created task with ID " + parentId;
    }

    @GET
    @Path("/user-task")
    public String getUserTasks(@QueryParam("workerId") Long workerId) {
        return "User tasks " + workerId;
    }

    @GET
    @Path("/owner-task")
    public String getOwnerTasks(@QueryParam("ownerId") Long ownerId) {
        return "Owner id " + ownerId;
    }

    @GET
    @Path("{taskId}")
    public String getTaskById(@PathParam("taskId") Long taskId) {
        return "Got task with id " + taskId;
    }

    @PUT
    @Path("/{taskId}")
    public String updateTask(@PathParam("taskId") Long taskId) {
        return "Task updated " + taskId;
    }

    @GET
    public String getSubTaskOrProject(@QueryParam("parentId") Long parentId) {
        return "Got subTask/project for ID " + parentId;
    }

    @DELETE
    @Path("/{taskId}")
    public String deleteTask(@PathParam("taskId") Long taskId) {
        return "Deleted task with id " + taskId;
    }

    @GET
    @Path("/status")
    public String getTaskByStatus(@QueryParam("taskStatus")Long statusId){
        return "Got task with status id " + statusId;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public List<Task> testAllTasks() {

        //TODO move to service, repo. It's just DB test...
        List<Task> tasks = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = (Connection) context.getAttribute("DBConnection");
            stmt = connection.createStatement();
            rs = stmt.executeQuery("select task_id, name from tasks");
            while (rs.next()) {
                tasks.add(new Task(rs.getLong("task_id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }
}
