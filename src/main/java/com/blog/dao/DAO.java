package com.blog.dao;

import java.util.ArrayList;

import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.Post;
import com.blog.dto.NewPost;

public interface DAO {

	int postCreate(Post post);
	int userCreate(BlogUser user);
	Post readPost(int p);
	ArrayList<Post> readAllPost();
	void update(Post post);
	BlogUser readUser(String userid);
	int commentCreate(Comments comment);
	ArrayList<Comments> readComments(int postId);
	ArrayList<BlogUser> readAllUsers();
	ArrayList<String> readUserIds();
	BlogUser validateLogin(String userId, String password);
	ArrayList<Comments> readComments(ArrayList<Integer> postIds);
	int getNextPostId();
	int postCreate(NewPost newPost);
	ArrayList<Post> searchPost(ArrayList<String> keys);
}
