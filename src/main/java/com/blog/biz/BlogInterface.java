package com.blog.biz;

import java.util.ArrayList;

import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.DuplicateUserException;
import com.blog.api.InvalidCommentException;
import com.blog.api.InvalidPostException;
import com.blog.api.InvalidSearchKeyException;
import com.blog.api.InvalidUserException;
import com.blog.api.Post;
import com.blog.dto.AuthenticationDto;
import com.blog.dto.NewComment;
import com.blog.dto.NewPost;
import com.blog.dto.PostDto;

public interface BlogInterface {
	//post
	ArrayList<PostDto> readAllPost();
	PostDto readPost(int number);
	int createPost(Post post);
	int createPost(NewPost newPost);
	ArrayList<PostDto> searchByCategory(String category);	
	ArrayList<PostDto> searchPost(String keys) throws InvalidSearchKeyException;
	int createPostPersist(NewPost newPost);
	
	//post dependent
	ArrayList<String> readCategory();
	ArrayList<Integer> readFavourites(String userId);
	boolean addFavourite(String userId, int postId);
	boolean removeFavourite(String userId, int postId);
	
	//comment
	int addComment(NewComment comment) throws InvalidCommentException;	
	public ArrayList<Comments> readCommentsOfPost(int postId) throws InvalidPostException;
	
	//user
	ArrayList<BlogUser> readAllUsers();
	ArrayList<String> readUserIds();
	int createUser(BlogUser user) throws InvalidUserException, DuplicateUserException;
	AuthenticationDto validateLogin(String userId, String password);
	
}
