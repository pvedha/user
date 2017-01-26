package com.blog.dto;

import java.sql.Date;

public class CommentDto {

	private int commentId;
	private int postId;
	private String message;
	private String userId;
	private Date postedOn;
	
	
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


	public Date getPostedOn() {
		return postedOn;
	}


	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}
	
	

}
