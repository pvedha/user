package com.blog.biz;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.blog.api.BlogUser;
import com.blog.api.DuplicateUserException;
import com.blog.api.InvalidUserException;
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
	
	@Override	
	public int createPost(Post post) 	{
		//	post.setCreatedOn(new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
		return dao.postCreate(post);
	}

	@Override
	public int createUser(BlogUser user) throws InvalidUserException, DuplicateUserException {
		if (user == null || user.getUserid() == null || user.getName() == null
				|| user.getPassword() == null) {
			throw new InvalidUserException();
		}
		 
		if (dao.readUser(user.getUserid()) != null) {
			throw new DuplicateUserException();	  
		};
		return dao.userCreate(user);
	}

}
