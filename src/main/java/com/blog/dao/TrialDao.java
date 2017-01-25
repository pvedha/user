package com.blog.dao;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.Post;
import com.blog.biz.BlogInterface;

public class TrialDao implements DAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");
	
	@Override
	public int postCreate(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Post read(int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> readAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Post post) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int userCreate(BlogUser user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public BlogUser readUser(String userid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<BlogUser> readAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> readUserIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int commentCreate(Comments comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Comments> readComments(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BlogUser validateLogin(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}


}
