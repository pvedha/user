package com.blog.rest;

import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blog.biz.Blog;

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
}
