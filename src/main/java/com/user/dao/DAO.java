package com.user.dao;

import java.util.ArrayList;

import com.user.api.BlogUser;
import com.user.api.Chats;
import com.user.api.Comments;
import com.user.api.Post;
import com.user.dto.ChatsDto;
import com.user.dto.NewChat;
import com.user.dto.NewComment;
import com.user.dto.NewPost;
import com.user.dto.UserDto;

public interface DAO {

	
	//post
	int postCreate(Post post);
	Post readPost(int p);
	ArrayList<Post> readAllPost();
	void update(Post post);	
	int getNextPostId();
	int postCreate(NewPost newPost);
	ArrayList<Post> searchPost(ArrayList<String> keys);
	ArrayList<Post> searchByCategory(String category);
	
	//comment
	int commentCreate(Comments comment);
	ArrayList<Comments> readComments(int postId);
	ArrayList<Comments> readComments(ArrayList<Integer> postIds);
	
	//user
	int userCreate(BlogUser user);	
	ArrayList<BlogUser> readAllUsers();
	ArrayList<String> readUserIds();
	BlogUser validateLogin(String userId, String password);
	BlogUser getUser(String userId);
	boolean updateUser(UserDto user);
	
		
	//generic
	ArrayList<String> readCategory();
	int commentAdd(NewComment newPost);
	boolean addFavourite(String userId, int postId);
	boolean removeFavourite(String userId, int postId);
	ArrayList<Integer> readFavourites(String userId);
	int chatAdd(NewChat chat);
	ArrayList<Chats> getTopChats();
	int initDB();
	ArrayList<Post> readLimitedPosts(int offset);	
}
