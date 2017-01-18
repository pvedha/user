package com.blog.biz;

import java.util.ArrayList;

import com.blog.api.Post;
import com.blog.dao.DAO;
import com.blog.dao.OracleDAOImpl;
import com.blog.dao.TrialDao;

public class Blog implements BlogInterface {
	private DAO dao;
	
	public Blog() {
		dao = new OracleDAOImpl();
	}

	@Override
	public ArrayList<Post> readAllPost() {
		ArrayList<Post> posts = dao.readAllPost();
		return posts;
	}

	@Override
	public Post readPost(int number) {
		return dao.read(number);
	}

}
