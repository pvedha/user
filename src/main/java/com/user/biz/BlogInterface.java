package com.user.biz;

import java.util.ArrayList;

import com.user.api.BlogUser;
import com.user.api.Comments;
import com.user.api.Post;
import com.user.api.Exceptions.DuplicateUserException;
import com.user.api.Exceptions.InvalidCommentException;
import com.user.api.Exceptions.InvalidPostException;
import com.user.api.Exceptions.InvalidSearchKeyException;
import com.user.api.Exceptions.InvalidUserException;
import com.user.dto.AuthenticationDto;
import com.user.dto.NewComment;
import com.user.dto.NewPost;
import com.user.dto.PostDto;
import com.user.dto.UserDto;

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
	UserDto getUser(String userId);
	boolean updateUser(UserDto user);
	AuthenticationDto validateSession(String userId, String password);
	int initDB();
	ArrayList<PostDto> readLimitedPosts(int offset);
	
}
