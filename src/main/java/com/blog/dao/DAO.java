package com.blog.dao;

import java.util.ArrayList;

import com.blog.api.BlogUser;
import com.blog.api.Post;

public interface DAO {

	int postCreate(Post post);
	int userCreate(BlogUser user);
	Post read(int p);
	ArrayList<Post> readAllPost();
	void update(Post post);
	BlogUser readUser(String userid);
}
