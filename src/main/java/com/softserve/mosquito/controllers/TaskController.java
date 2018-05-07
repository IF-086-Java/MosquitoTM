package com.softserve.mosquito.controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.softserve.mosquito.enitities.Task;

/**
 *  Task controller : JAX-RS + MySql + JSON test.
 * 
 * */

@Path("/tasks")
public class TaskController {
	@Context ServletContext context;

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
			while(rs.next()){
				tasks.add(new Task(rs.getLong("task_id"), rs.getString("name")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return tasks;
    }
}
