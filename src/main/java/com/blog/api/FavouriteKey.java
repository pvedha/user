package com.blog.api;

import java.io.Serializable;

public class FavouriteKey implements Serializable{
	/**
	 * this can be used as a Key with two @id in favourite entity
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private int postId;
	public FavouriteKey() {
		// TODO Auto-generated constructor stub
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getPostId() {
		return postId;
	}
	public void setPostId(int postId) {
		this.postId = postId;
	}
	
	
}
