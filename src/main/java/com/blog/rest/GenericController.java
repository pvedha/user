package com.blog.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blog.api.InvalidCommentException;
import com.blog.biz.Blog;
import com.blog.dto.ChatsDto;
import com.blog.dto.NewChat;

@Path("/")
public class GenericController {

	public GenericController() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/version")
	public Response getVersion() {
		return Response.ok().entity("2.1").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/category")
	public Response readCategory() {
		Blog blog = new Blog();
		ArrayList<String> categories = blog.readCategory();
		return Response.ok().entity(categories).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/favourite/{userId}")
	public Response readCategory(@PathParam("userId") String userId) {
		Blog blog = new Blog();
		ArrayList<Integer> favourites = blog.readFavourites(userId);
		return Response.ok().entity(favourites).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/favourite/add/{userId}/{postId}")
	public Response addFavourite(@PathParam("userId") String userId, @PathParam("postId") int postId) {
		Blog blog = new Blog();
		boolean result = blog.addFavourite(userId, postId);
		return Response.ok().entity(result + "").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/favourite/remove/{userId}/{postId}")
	public Response removeFavourite(@PathParam("userId") String userId, @PathParam("postId") int postId) {
		Blog blog = new Blog();
		boolean result = blog.removeFavourite(userId, postId);
		return Response.ok().entity(result + "").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getChats")	
	public Response getChats() {		
		Blog blog = new Blog();
		ArrayList<ChatsDto> posts = blog.readRecentChats();
		return Response.ok().entity(posts).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/newChat")
	public Response newChat(NewChat chat) throws InvalidCommentException {
		Blog blog = new Blog();
		int number = blog.addChat(chat);
		return Response.ok().entity(number + "").build();
	}
	
}
