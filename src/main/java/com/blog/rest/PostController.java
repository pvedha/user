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

import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.DuplicateUserException;

import com.blog.api.InvalidCommentException;
import com.blog.api.InvalidSearchKeyException;
import com.blog.api.InvalidUserException;
import com.blog.api.Post;
import com.blog.biz.Blog;
import com.blog.dto.ChatsDto;
import com.blog.dto.NewChat;
import com.blog.dto.NewComment;
import com.blog.dto.NewPost;
import com.blog.dto.PostDto;

@Path("/post")
public class PostController {

	public PostController() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readAllPosts() {
		Blog blog = new Blog();
		ArrayList<PostDto> posts = blog.readAllPost();
		return Response.ok().entity(posts).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{no}")
	public Response read(@PathParam("no") int number) {
		Blog blog = new Blog();
		return Response.ok().entity(blog.readPost(number)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/search/{keys}")
	public Response search(@PathParam("keys") String keys) throws InvalidSearchKeyException {
		Blog blog = new Blog();
		ArrayList<PostDto> posts = blog.searchPost(keys);
		return Response.ok().entity(posts).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/category/{category}")
	public Response searchByCategory(@PathParam("category") String category) throws InvalidSearchKeyException {
		Blog blog = new Blog();
		ArrayList<PostDto> posts = new ArrayList<>();
		try{
			posts = blog.searchByCategory(category);
		} catch (Exception e){
			System.out.println("Exception caught in search by category " + e.getMessage());			
		}	
		return Response.ok().entity(posts).build();
	}
	
	//not used??
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/makePost")
	public Response makePost(Post post) {
		Blog blog = new Blog();
		int number = blog.createPost(post);
		return Response.ok().entity(number + "").build();
	}	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/newPost")
	public Response newPost(NewPost newPost) {
		Blog blog = new Blog();
		int number = blog.createPost(newPost);
		return Response.ok().entity(number + "").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addComment")
	public Response addComment(NewComment comment) throws InvalidCommentException {
		Blog blog = new Blog();
		int number = blog.addComment(comment);
		return Response.ok().entity(number + "").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/newChat")
	public Response newChat(NewChat chat) throws InvalidCommentException {
		Blog blog = new Blog();
		int number = blog.addChat(chat);
		return Response.ok().entity(number + "").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/getChats")
	public Response getChats() {
		Blog blog = new Blog();
		ArrayList<ChatsDto> posts = blog.readRecentChats();
		return Response.ok().entity(posts).build();
	}
}
