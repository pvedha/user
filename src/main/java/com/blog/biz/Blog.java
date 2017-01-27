package com.blog.biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.DuplicateUserException;
import com.blog.api.InvalidCommentException;
import com.blog.api.InvalidPostException;
import com.blog.api.InvalidSearchKeyException;
import com.blog.api.InvalidUserException;
import com.blog.api.Post;
import com.blog.dao.DAO;
import com.blog.dao.OracleDAOImpl;
import com.blog.dto.AuthenticationDto;
import com.blog.dto.CommentDto;
import com.blog.dto.NewPost;
import com.blog.dto.PostDto;

public class Blog implements BlogInterface {
	private DAO dao;

	public Blog() {
		dao = new OracleDAOImpl();
	}

	@Override
	public PostDto readPost(int postId) {
		Post post = dao.readPost(postId);
		ArrayList<Comments> comments = dao.readComments(postId);
		return getPostDto(post, comments);
	}

	@Override
	public ArrayList<PostDto> readAllPost() {
		ArrayList<Post> posts = dao.readAllPost();
		ArrayList<Comments> comments = dao.readComments(getPostIds(posts));
		return getPostDto(posts, comments);
	}

	private ArrayList<PostDto> getPostDto(ArrayList<Post> posts, ArrayList<Comments> comments) {
		ArrayList<PostDto> postDtos = new ArrayList<>();
		for (Post post : posts) {
			PostDto postDto = getPostDto(post, comments);
			postDtos.add(postDto);

		}
		return postDtos;
	}

	public PostDto getPostDto(Post post, ArrayList<Comments> comments) {
		PostDto postDto = new PostDto();
		int postId = post.getPostId();
		postDto.setPostId(postId);
		postDto.setTitle(post.getTitle());
		postDto.setMessage(post.getMessage());
		postDto.setPostedBy(post.getPostedBy().getUserid());
		postDto.setTags(post.getTags());		
		postDto.setPosted_on(new SimpleDateFormat("dd-MMM-yyyy, HH:mm:ss").format(post.getCreatedOn()));
		postDto.setCategory(post.getCategory());

		// This needs a better logic like multimap.
		ArrayList<CommentDto> postComments = new ArrayList<>();

		for (Comments comment : comments) {
			if (comment.getPostId() == postId) {
				CommentDto commentDto = new CommentDto();
				commentDto.setCommentId(comment.getCommentId());
				commentDto.setPostId(comment.getPostId());
				commentDto.setMessage(comment.getMessage());
				commentDto.setPostedOn(comment.getPostedOn());
				commentDto.setUserId(comment.getPostedBy().getUserid());

				postComments.add(commentDto);
			}
		}

		postDto.setComments(postComments);
		return postDto;
	}

	private ArrayList<Integer> getPostIds(ArrayList<Post> posts) {
		ArrayList<Integer> postIds = new ArrayList<>();

		for (Post post : posts) {
			postIds.add(post.getPostId());
		}
		return postIds;
	}

	@Override
	public int createPost(Post post) {
		// post.setCreatedOn(new
		// SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));

		return dao.postCreate(post);
	}

	@Override
	public int createUser(BlogUser user) throws InvalidUserException, DuplicateUserException {
		if (user == null || user.getUserid() == null || user.getName() == null || user.getPassword() == null) {
			throw new InvalidUserException();
		}

		if (dao.readUser(user.getUserid()) != null) {
			throw new DuplicateUserException();
		}
		;
		return dao.userCreate(user);
	}

	@Override
	public int addComment(Comments comment) throws InvalidCommentException {
		if (comment == null || comment.getPostedBy() == null || comment.getMessage() == null
				|| this.readPost(comment.getPostId()) == null) {
			throw new InvalidCommentException();
		}
		return dao.commentCreate(comment);
	}

	public ArrayList<Comments> readCommentsOfPost(int postId) throws InvalidPostException {
		if (this.readPost(postId) == null) {
			throw new InvalidPostException();
		}
		return dao.readComments(postId);
	}

	@Override
	public ArrayList<BlogUser> readAllUsers() {
		return dao.readAllUsers();
	}

	@Override
	public ArrayList<String> readUserIds() {
		return dao.readUserIds();
	}

	@Override
	public AuthenticationDto validateLogin(String userId, String password) {
		BlogUser user = dao.validateLogin(userId, password);
		AuthenticationDto token = null;
		if (user != null) token = this.makeAuthDto(user);
		return token;
	}

	private AuthenticationDto makeAuthDto(BlogUser user) {
		// TODO Auto-generated method stub		
		return (new AuthenticationDto(user.getUserid(), 
				user.getName(), user.getAbout()));
	}
	
	public Boolean validateToken(AuthenticationDto requestToken) {
		AuthenticationDto temp = new AuthenticationDto(requestToken.getUserId(),
				requestToken.getName(), requestToken.getAbout());
		return temp.getToken().equals(requestToken.getToken());		
	}

	@Override
	public int createPost(NewPost newPost) {
		return dao.postCreate(newPost);
	}
	
	public ArrayList<PostDto> searchPost(String keys) throws InvalidSearchKeyException {
		if (keys == null || keys.isEmpty()) 
			throw new InvalidSearchKeyException();
		ArrayList<String> keyList = new ArrayList(Arrays.asList(keys.split("\\s")));
		ArrayList<Post> posts = dao.searchPost(keyList);
		ArrayList<Comments> comments = dao.readComments(getPostIds(posts));
		return getPostDto(posts, comments);
	}
	
	@Override
	public int createPostPersist(NewPost newPost) {
		Post post = new Post();
		post.setPostId(dao.getNextPostId());
		post.setPostedBy(dao.readUser(newPost.getUserId()));
		post.setTitle(newPost.getTitle());
		post.setMessage(newPost.getMessage());
		post.setTags(newPost.getTags());
		post.setCategory(newPost.getCategory());
		//post.setCreatedOn(new java.sql.Date(new java.util.Date().getTime()));
		return dao.postCreate(post);
	}

	@Override
	public ArrayList<String> readCategory() {		
		return dao.readCategory();
	}

	public ArrayList<PostDto> searchByCategory(String category) {
		ArrayList<Post> posts = dao.searchByCategory(category);
		ArrayList<Comments> comments = dao.readComments(getPostIds(posts));
		return getPostDto(posts, comments);
	}	

}
