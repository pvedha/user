package com.blog.dao;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.annotations.Nationalized;

import com.blog.api.Post;

public class OracleDAOImpl implements DAO {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");

	@Override
	public int create(Post post) {
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
		//ArrayList<Post> posts = (ArrayList<Post>) em.createNativeQuery("select * from post",Post.class).getResultList();
		//catch exception here, multiple items
		//System.out.println("Items" + objects.size());
		em.close();
		return post;
		//return posts.get(0);
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
