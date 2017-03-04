package com.user.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.user.api.BlogUser;
import com.user.api.Exceptions.DuplicateUserException;
import com.user.api.Exceptions.InvalidUserException;
import com.user.biz.UserService;
import com.user.dto.AuthenticationDto;
import com.user.dto.UserDto;

@Path("/")
public class UserController {

	public UserController() {
		// TODO Auto-generated constructor stub
	}

	// remove/hide this - reveals all.
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readAllUsers() {
		UserService svc = new UserService(); 
		ArrayList<BlogUser> blogUsers = svc.readAllUsers();
		if (blogUsers != null) {
			return Response.ok().entity(blogUsers).build();
		} else {
			return Response.ok().entity(Status.NO_CONTENT).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/ids")
	public Response readUserIds() {
		UserService svc = new UserService();
		ArrayList<String> userNames = svc.readUserIds();
		if (userNames != null) {
			return Response.ok().entity(userNames).build();
		} else {
			return Response.ok().entity(Status.NO_CONTENT).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/view/{userId}")
	public Response getUser(@PathParam("userId") String userId) {
		UserService svc = new UserService();
		System.out.println("hit rest for view");
		UserDto user = svc.getUser(userId);
		if (user != null) {
	 	    return Response.ok().entity(user).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}		
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateUser(UserDto user) {
		UserService svc = new UserService();
		boolean result = svc.updateUser(user);
		if (result) {
			return Response.ok().entity(result).build();
		} else {
			return Response.status(Status.NOT_MODIFIED).build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userid}/{password}")
	public Response login(@PathParam("userid") String userId, @PathParam("password") String password) {
		UserService svc = new UserService();
		AuthenticationDto token = svc.validateLogin(userId, password);
		if (token != null) {
			return Response.ok().entity(token).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addUser")
	public Response addUser(BlogUser user)  {
		UserService svc = new UserService();
		try {
			int number = svc.createUser(user);
			return Response.ok().entity(number + "").build();
		} catch (DuplicateUserException e) {
			return Response.status(Status.CONFLICT).entity("Duplicate User Details").build();
		} catch (InvalidUserException e) {
			return Response.status(Status.FORBIDDEN).entity("Invalid User Details").build();
		}
		
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addUser/{userid}/{name}/{password}/{about}")
	public Response addUser(@PathParam("userid") String userId, @PathParam("name") String name,
			@PathParam("password") String password, @PathParam("about") String about) {
		UserService svc = new UserService();
		BlogUser user = new BlogUser(userId, name, password, about);
		try{
			int number = svc.createUser(user);
			return Response.ok().entity(number + "").build();
		} catch (DuplicateUserException e) {
			return Response.status(Status.CONFLICT).entity("Duplicate User Details").build();
		} catch (InvalidUserException e) {
			return Response.status(Status.FORBIDDEN).entity("Invalid User Details").build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/validate")
	public Response validateSession(@HeaderParam("userId") String userId, @HeaderParam("token") String token) {
		UserService svc = new UserService();
		System.out.println("User ID :" + userId + " Token : " + token);
		AuthenticationDto response = svc.validateSession(userId, token);
		if (response == null) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().entity(response).build();
	}

}
