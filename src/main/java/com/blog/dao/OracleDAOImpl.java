package com.blog.dao;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.blog.api.Post;

public class OracleDAOImpl implements DAO {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");

	@Override
	public int create(Post post) {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(post);
		em.getTransaction().commit();
		em.close();
		return post.getPostId();
	}

	@Override
	public Post read(int postId) {
		EntityManager em = factory.createEntityManager();
		Post post = em.find(Post.class, postId);
		em.close();
		return post;
	}

	@Override
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
		ArrayList<Post> posts = (ArrayList<Post>) em.createNativeQuery("select * from post").getResultList();
		em.close();
		return posts;
	}

}
