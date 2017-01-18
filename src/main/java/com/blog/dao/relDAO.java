package com.blog.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.blog.api.Post;

public class relDAO implements DAO {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("bank");

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
		// TODO Auto-generated method stub
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.merge(post);
		em.getTransaction().commit();
		em.close();
	}
	
	public void addComment() {
	
	}

}
