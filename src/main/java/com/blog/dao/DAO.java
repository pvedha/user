package com.blog.dao;

import com.blog.api.Post;

public interface DAO {

	int create(Post post);
	Post read(int p);
	void update(Post post);
}
