package com.blog.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.blog.api.BlogUser;
import com.blog.api.Comment;
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
	public int commentCreate(Comment comment) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(comment);
		em.getTransaction().commit();
		em.close();
		return 0;
	}

	@Override
	public ArrayList<Comment> readComments(int postId) {
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		ArrayList<Comment> comments = null;
				//(ArrayList<Comment>) em.createNativeQuery("select * from Comment",Comment.class).getResultList();
		em.close();
		return comments;
	}
}
