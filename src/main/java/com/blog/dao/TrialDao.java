package com.blog.dao;

import java.util.ArrayList;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.blog.api.Post;
import com.blog.biz.BlogInterface;

public class TrialDao implements DAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");
	
	@Override
	public int create(Post post) {
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



}
