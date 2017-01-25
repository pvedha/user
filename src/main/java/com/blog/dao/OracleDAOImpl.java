package com.blog.dao;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.Post;

public class OracleDAOImpl implements DAO {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");

	@Override
	public int postCreate(Post post) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
		em.close();
		return post.getPostId();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Post read(int postId) {
		EntityManager em = factory.createEntityManager();
		Post post = em.find(Post.class, postId); //This is throwing NVarchar abstract error
		em.close();
		return post;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BlogUser readUser(String userid) {
		EntityManager em = factory.createEntityManager();
		BlogUser user = em.find(BlogUser.class, userid); //This is throwing NVarchar abstract error
		em.close();
		return user;
	}

	@Override // We need either update/add comment
	public void update(Post post) {
		EntityManager em = factory.createEntityManager();		
		em.getTransaction().begin();
		em.merge(post);
		em.getTransaction().commit();
		em.close();
	}
	
	public void addComment() {
	
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Post> readAllPost() {
		EntityManager em = factory.createEntityManager();
		ArrayList<Post> posts = (ArrayList<Post>) em.createNativeQuery("select * from post",Post.class).getResultList();
		em.close();
		return posts;
	}


	@Override
	public int userCreate(BlogUser user) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
		return 0;
	}

	@Override
	public int commentCreate(Comments comment) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		em.close();
		return 0;
	}

	@Override
	public ArrayList<Comments> readComments(int postId) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		ArrayList<Comments> comments = null;
				//(ArrayList<Comment>) em.createNativeQuery("select * from Comment",Comment.class).getResultList();
		em.close();
		return comments;
	}
	
		@SuppressWarnings("unchecked")
	@Override
	public ArrayList<BlogUser> readAllUsers() {
		EntityManager em = factory.createEntityManager();
		ArrayList<BlogUser> blogUsers = (ArrayList<BlogUser>) em.createNativeQuery("select * from bloguser",BlogUser.class).getResultList();
		em.close();
		return blogUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> readUserIds() {
		EntityManager em = factory.createEntityManager();
		ArrayList<String> userIds = (ArrayList<String>) em.createNativeQuery("select userid from bloguser").getResultList();
		em.close();
		return userIds;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BlogUser validateLogin(String userId, String password) {
		EntityManager em = factory.createEntityManager();
		String validateQuery = "select * from bloguser where "
				+ " userid = '" + userId + "' and password = '" + password + "'";
		//System.out.println("The query is : " + validateQuery);
		ArrayList<BlogUser> blogUsers = (ArrayList<BlogUser>) em.createNativeQuery(validateQuery, BlogUser.class).getResultList();
		em.close();
		if(blogUsers.size() == 1){
			return blogUsers.get(0);
		}else{
			return null;
		}
	}
}
