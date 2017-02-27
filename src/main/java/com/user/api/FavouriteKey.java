package com.user.api;

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
	public FavouriteKey(String userId, int postId) {
		this.userId = userId;
		this.postId = postId;
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
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
}
