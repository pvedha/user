package com.blog.dao;

import java.awt.List;
import java.util.ArrayList;

import com.blog.api.Post;

public interface DAO {

	int create(Post post);
	Post read(int p);
	ArrayList<Post> readAllPost();
	void update(Post post);
}
