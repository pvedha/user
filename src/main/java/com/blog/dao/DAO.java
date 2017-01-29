package com.blog.dao;

import java.util.ArrayList;

import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.Post;
import com.blog.dto.NewComment;
import com.blog.dto.NewPost;

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
	BlogUser readUser(String userid);	
	ArrayList<BlogUser> readAllUsers();
	ArrayList<String> readUserIds();
	BlogUser validateLogin(String userId, String password);
	
		
	//generic
	ArrayList<String> readCategory();
	int commentAdd(NewComment newPost);
	
}
