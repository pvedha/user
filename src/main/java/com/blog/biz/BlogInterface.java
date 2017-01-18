package com.blog.biz;

import java.util.ArrayList;

import com.blog.api.Post;

public interface BlogInterface {
	ArrayList<Post> readAllPost();
	Post readPost(int number);
}
