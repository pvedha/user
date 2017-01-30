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
import javax.ws.rs.core.Response.Status;

import com.blog.api.BlogUser;
import com.blog.api.DuplicateUserException;
import com.blog.api.InvalidUserException;
import com.blog.biz.Blog;
import com.blog.dto.AuthenticationDto;

@Path("/user")
public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	// remove/hide this - reveals all.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readAllUsers() {
		Blog blog = new Blog();
		ArrayList<BlogUser> blogUsers = blog.readAllUsers();
		return Response.ok().entity(blogUsers).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ids")
	public Response readUserIds() {
		Blog blog = new Blog();
		ArrayList<String> userNames = blog.readUserIds();
		return Response.ok().entity(userNames).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userid}/{password}")
	public Response login(@PathParam("userid") String userId, @PathParam("password") String password) {
		Blog blog = new Blog();
		AuthenticationDto token = blog.validateLogin(userId, password);
		if (token != null) {
			return Response.ok().entity(token).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addUser")
	public Response addUser(BlogUser user) throws InvalidUserException, DuplicateUserException {
		Blog blog = new Blog();
		try {
			int number = blog.createUser(user);
			return Response.ok().entity(number + "").build();
		} catch (Exception e) {
			return Response.status(Status.CONFLICT).entity("Invalid User Details").build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addUser/{userid}/{name}/{password}/{about}")
	public Response addUser(@PathParam("userid") String userId, @PathParam("name") String name,
			@PathParam("password") String password, @PathParam("about") String about)
			throws InvalidUserException, DuplicateUserException {
		Blog blog = new Blog();
		BlogUser user = new BlogUser(userId, name, password, about);
		int number = blog.createUser(user);
		return Response.ok().entity(number + "").build();
	}
}
