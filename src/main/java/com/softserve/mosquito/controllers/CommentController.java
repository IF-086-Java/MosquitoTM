package com.softserve.mosquito.controllers;

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
	@Path("/{task_id}/comments")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({MediaType.APPLICATION_JSON})
	public String createComment(@PathParam("task_id") long taskId,
			@FormParam("comment") String comment) {
		return "Created comment for task with ID:" + taskId + ". Text: " + comment;
	}
	
	@GET
	@Path("/{task_id}/comments")
	@Produces({MediaType.APPLICATION_JSON})
	public String getCommentsByTaskId(@PathParam("task_id") long taskId){
		return "Returned all comments for task with ID: " + taskId;
	}
	
	@GET
	@Path("/{taskId}/comments/{commentId}")
	@Produces({MediaType.APPLICATION_JSON})
	public String getCommentsByCommentId(@PathParam("taskId") long taskId,
			@PathParam("commentId") long commentId){
		return "Returned comment with ID: " + commentId;
	}
	
	@PUT
	@Path("/{task_id}/comments/{comment_id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({MediaType.APPLICATION_JSON})
	public String updateComment(@PathParam("task_id") long taskId,
			@PathParam("comment_id") long commentId,
			@FormParam("comment") String comment){
		return "Updated comment with ID:" + commentId + " New text: " + comment;
	}
	
	@DELETE
	@Path("/{taskId}/comments/{commentId}")
	public String deletComments(@PathParam("taskId") long taskId,
			@PathParam("commentId") long commentId){
		return "Deleted comment with ID:" + commentId + " (taskId: " + taskId + ")";
	}
}
