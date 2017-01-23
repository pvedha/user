package com.blog.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class BlogUser {
	@Id
	protected String userid;
	protected String name;     
	protected String password;
	protected String about;
	
	
	public BlogUser(String userid, String name, String password, String about) {
		super();
		this.userid = userid;
		this.name = name;
		this.password = password;
		this.about = about;
	}
	
	public BlogUser(){
		
	}
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	
	
}
