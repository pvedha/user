package com.user.dao;

import java.util.ArrayList;

import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;

import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.user.api.BlogUser;
import com.user.api.Chats;
import com.user.api.Comments;
import com.user.api.Post;
import com.user.dto.NewChat;
import com.user.dto.NewComment;
import com.user.dto.NewPost;
import com.user.dto.UserDto;

public class MongoDAOImpl implements DAO {
	//static MongoClient mongoClient = new MongoClient("127.0.0.1", 27017);
	static MongoClient mongoClient = new MongoClient("35.161.192.221", 27017);
	
	static MongoDatabase db = mongoClient.getDatabase("user-db");
	

	@Override
	public int userCreate(BlogUser user) {
		MongoCollection<Document> collection = db.getCollection("users");
		Document document = new Document();
		document.put("userid", user.getUserid());
		document.put("name", user.getName());
		document.put("password", user.getPassword());
		document.put("about", user.getAbout());
		collection.insertOne(document);
		return 0;
	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<BlogUser> readAllUsers() {
		MongoCollection<Document> collection = db.getCollection("users");
		ArrayList<BlogUser> users = new ArrayList<BlogUser>();
		FindIterable<Document> documents = collection.find();
		for(Document d : documents) {
		users.add(
				new BlogUser(d.getString("userid"),
						d.getString("name"),
						d.getString("password"),
						d.getString("about")
						)
				);	
		}
		return users;
	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<String> readUserIds() {
		MongoCollection<Document> collection = db.getCollection("users");
		ArrayList<String> userIds = new ArrayList<String>();
		
		FindIterable<Document> documents = collection.find();
		for(Document d : documents) {
			userIds.add(d.getString("userid"));		
		}
		return userIds;
	}

	@Override
	public BlogUser validateLogin(String userId, String password) {
		MongoCollection<Document> collection = db.getCollection("users");

	    Document query = new Document("userid", userId);
	    query.append("password", password);
	    Document d = collection.find(query).first();
		if (d != null) {
			return new BlogUser(d.getString("userid"),
					d.getString("name"),
					d.getString("password"),
					d.getString("about"));
		} else {
			return null;
		}
	}

	@Override
	public BlogUser getUser(String userId) {
		MongoCollection<Document> collection = db.getCollection("users");
		// We maintain just one user ID so searching for 1st instance is enough
		Document d = collection.find(eq("userid", userId)).first();
		if (d == null){
			System.out.println("User not found");
			return null;
		}
		return new BlogUser(d.getString("userid"),
				d.getString("name"),
				d.getString("password"),
				d.getString("about"));
		
		    //forEach((Document d) -> System.out.println(d.toJson()));
		
		
		//return null;
	}

	@Override
	public boolean updateUser(UserDto user) {
		MongoCollection<Document> collection = db.getCollection("users");
		Document d = new Document();
		d.put("userid", user.getUserId());
		d.put("name", user.getUserName());
		d.put("password", user.getPassword());
		d.put("about", user.getAbout());
		collection.updateOne(eq("userid", user.getUserId()), new Document("$set", d));
		return true;
	}


}
