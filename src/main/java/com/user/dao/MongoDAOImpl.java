package com.user.dao;

import java.util.ArrayList;

import org.bson.Document;
import static com.mongodb.client.model.Filters.eq;
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
	static MongoClient mongoClient = new MongoClient("localhost", 27017);
	static MongoDatabase db = mongoClient.getDatabase("user-db");
	
	@Override
	public int postCreate(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	public MongoDAOImpl() {
		
	}

	@Override
	public Post readPost(int p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> readAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Post post) {
		// TODO Auto-generated method stub

	}

	@Override
	public int getNextPostId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int postCreate(NewPost newPost) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Post> searchPost(ArrayList<String> keys) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Post> searchByCategory(String category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int commentCreate(Comments comment) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Comments> readComments(int postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Comments> readComments(ArrayList<Integer> postIds) {
		// TODO Auto-generated method stub
		return null;
	}

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

	@Override
	public ArrayList<BlogUser> readAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> readUserIds() {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public ArrayList<String> readCategory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int commentAdd(NewComment newPost) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean addFavourite(String userId, int postId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeFavourite(String userId, int postId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Integer> readFavourites(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int chatAdd(NewChat chat) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Chats> getTopChats() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int initDB() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<Post> readLimitedPosts(int offset) {
		// TODO Auto-generated method stub
		return null;
	}

}
