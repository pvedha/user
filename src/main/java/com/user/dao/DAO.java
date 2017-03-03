package com.user.dao;

import java.util.ArrayList;

import com.user.api.BlogUser;
import com.user.api.Chats;
import com.user.api.Comments;
import com.user.api.Post;
import com.user.dto.ChatsDto;
import com.user.dto.NewChat;
import com.user.dto.NewComment;
import com.user.dto.NewPost;
import com.user.dto.UserDto;

public interface DAO {
	
	//user
	int userCreate(BlogUser user);	
	ArrayList<BlogUser> readAllUsers();
	ArrayList<String> readUserIds();
	BlogUser validateLogin(String userId, String password);
	BlogUser getUser(String userId);
	boolean updateUser(UserDto user);
			
}
