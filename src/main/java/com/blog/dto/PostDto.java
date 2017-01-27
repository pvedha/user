package com.blog.dto;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.blog.api.BlogUser;
import com.blog.api.Comments;

public class PostDto {

	private int postId;
	private String title;
	private String message;	
	private String postedBy;
	private String posted_on;
	private String tags;
	private String category;
	private ArrayList<CommentDto> comments = new ArrayList<>();
	
	public PostDto() {
	}
	public int getPostId() {
		return postId;
	}

	public void setPostId(int postId) {
		this.postId = postId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(String postedBy) {
		this.postedBy = postedBy;
	}

	public String getPosted_on() {
		return posted_on;
	}

	public void setPosted_on(String posted_on) {
		this.posted_on = posted_on;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public ArrayList<CommentDto> getComments() {
		return comments;
	}

	public void setComments(ArrayList<CommentDto> comments) {
		this.comments = comments;
	}

	

}
