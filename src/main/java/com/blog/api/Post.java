package com.blog.api;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.Nationalized;

@Entity
@XmlRootElement
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // Check whether this works
													// while posting
	protected int post_Id;
	protected String title;
	protected String message;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "posted_by")
	protected BlogUser postedBy;
	protected Date posted_on;
	protected String tags;
	protected String category;
	// Need to add comment array

	public Post() {

	}

	public Post(int postId, String title, String message, BlogUser postedBy, Date createdOn, String tags,
			String category) {
		super();
		this.post_Id = postId;
		this.title = title;
		this.message = message;
		this.postedBy = postedBy;
		this.posted_on = createdOn;
		this.tags = tags;
		this.category = category;
	}

	public int getPostId() {
		return post_Id;
	}

	public void setPostId(int postId) {
		this.post_Id = postId;
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

	public BlogUser getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(BlogUser postedBy) {
		this.postedBy = postedBy;
	}

	public Date getCreatedOn() {
		return posted_on;
	}

	public void setCreatedOn(Date createdOn) {
		this.posted_on = createdOn;
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
