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


@Path("/tasks")
public class CommentController {
	
	@POST
	@Path("/{taskId}/comments")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.TEXT_PLAIN})
	public String createComment(@PathParam("taskId") long taskId,
			@FormParam("comment") String comment) {
		return "Created comment for task with ID:" + taskId + ". Text: " + comment;
	}
	
	@GET
	@Path("/{taskId}/comments")
	@Produces({MediaType.TEXT_PLAIN})
	public String getCommentsByTaskId(@PathParam("taskId") long taskId){
		return "Returned all comments for task with ID: " + taskId;
	}
	
	@PUT
	@Path("/{taskId}/comments/{commentId}")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.TEXT_PLAIN})
	public String updateComment(@PathParam("taskId") long taskId,
			@PathParam("commentId") long commentId,
			@FormParam("comment") String comment){
		return "Updated comment with ID:" + commentId + "(taskId: " + taskId + ") New text: " + comment;
	}
	
	@DELETE
	@Path("/{taskId}/comments/{commentId}")
	public String deletComments(@PathParam("taskId") long taskId,
			@PathParam("commentId") long commentId){
		return "Updated comment with ID:" + commentId + " (taskId: " + taskId + ")";
	}
}
