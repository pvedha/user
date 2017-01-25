package com.blog.biz;

import java.util.ArrayList;

import com.blog.api.BlogUser;
import com.blog.api.Comment;
import com.blog.api.DuplicateUserException;
import com.blog.api.InvalidCommentException;
import com.blog.api.InvalidPostException;
import com.blog.api.InvalidUserException;
import com.blog.api.Post;

public interface BlogInterface {
	ArrayList<Post> readAllPost();
	Post readPost(int number);
	int createPost(Post post);
	int addComment(Comment comment) throws InvalidCommentException;
	int createUser(BlogUser user) throws InvalidUserException, DuplicateUserException;
	public ArrayList<Comment> readCommentsOfPost(int postId) throws InvalidPostException;
}
