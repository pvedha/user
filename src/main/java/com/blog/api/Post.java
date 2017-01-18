package com.blog.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Post {

	@Id
	@GeneratedValue
	protected int postId;
	protected String title;
	protected String message;
	protected String postedBy;
	protected String createdOn;
	protected String tags;
	protected String category;
	
	
	public Post() {
		
	}

	public Post(int postId, String title, String message, String postedBy, String createdOn, String tags,
			String category) {
		super();
		this.postId = postId;
		this.title = title;
		this.message = message;
		this.postedBy = postedBy;
		this.createdOn = createdOn;
		this.tags = tags;
		this.category = category;
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
	public String getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
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
	

}

