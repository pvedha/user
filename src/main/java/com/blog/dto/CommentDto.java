package com.blog.dto;

import java.sql.Date;
import java.sql.Timestamp;

public class CommentDto {

	private int commentId;
	private int postId;
	private String message;
	private String userId;
	private String postedOn;
	
	
	public CommentDto() {
		// TODO Auto-generated constructor stub
	}


	public int getCommentId() {
		return commentId;
	}


	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}


	public int getPostId() {
		return postId;
	}


	public void setPostId(int postId) {
		this.postId = postId;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}
	
	

}
