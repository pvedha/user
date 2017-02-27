package com.user.biz;

import java.util.ArrayList;

import com.user.dto.AuthenticationDto;
import com.user.dto.UserDto;
import com.user.api.BlogUser;
import com.user.api.Exceptions.DuplicateUserException;
import com.user.api.Exceptions.InvalidUserException;
import com.user.dao.DAO;
import com.user.dao.MongoDAOImpl;
import com.user.dao.OracleDAOImpl;

public class UserService {
	private DAO mongo;// = new MongoDAOImpl(); 

	public UserService() {
		try {			
			System.out.println("Initializing for mongo");
			mongo = new MongoDAOImpl();
			System.out.println("Inited mongo");
		} catch (Exception e){
			System.out.println("No class found most likely " + e.getMessage());
		}
	}

	public ArrayList<BlogUser> readAllUsers() {
		// TODO Auto-generated method stub
		return mongo.readAllUsers();
	}

	public ArrayList<String> readUserIds() {
		return mongo.readUserIds();
	}

	public UserDto getUser(String userId) {
		BlogUser blogUser = mongo.getUser(userId);
		UserDto user = new UserDto();
		user.setAbout(blogUser.getAbout());
		user.setUserName(blogUser.getName());
		return user;
	}

	public boolean updateUser(UserDto user) {
		return mongo.updateUser(user);
	}
	
	private AuthenticationDto makeAuthDto(BlogUser user) {
		// TODO Auto-generated method stub		
		return (new AuthenticationDto(user.getUserid(), 
				user.getName(), user.getAbout()));
	}

	public AuthenticationDto validateLogin(String userId, String password) {
		BlogUser user = mongo.validateLogin(userId, password);
		AuthenticationDto token = null;
		if (user != null) token = this.makeAuthDto(user);
		return token;
	}

	public int createUser(BlogUser user) {
		if (user == null || user.getUserid() == null || user.getName() == null || user.getPassword() == null) {
			throw new InvalidUserException();
		}

		if (mongo.getUser(user.getUserid()) != null) {
			throw new DuplicateUserException();
		}
		return mongo.userCreate(user);
	}
	
	public Boolean validateToken(AuthenticationDto requestToken) {
		AuthenticationDto temp = new AuthenticationDto(requestToken.getUserId(),
				requestToken.getName(), requestToken.getAbout());		
		return temp.getToken().equals(requestToken.getToken());		
	}
	
	public Boolean validateToken(String userId, String token){
		if(new AuthenticationDto().genToken(userId).equals(token)){
			return true;
		}
		return false;
	}
	
	public AuthenticationDto validateSession(String userId, String token) {
		if(!validateToken(userId, token)){
			return null;
		}
		System.out.println("proceeding to get the user details");
		AuthenticationDto response = new AuthenticationDto();		
		BlogUser user = mongo.getUser(userId);
		response.setToken(token); //we can regenerate with time
		response.setAbout(user.getAbout());
		response.setName(user.getName());
		response.setUserId(user.getUserid());		
		return response;
	}

}
