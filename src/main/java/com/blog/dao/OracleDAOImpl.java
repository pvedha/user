package com.blog.dao;

import java.awt.List;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.stream.events.Comment;

import com.blog.api.BlogUser;
import com.blog.api.Comments;
import com.blog.api.Post;
import com.blog.dto.NewPost;

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
	public Post readPost(int postId) {
		EntityManager em = factory.createEntityManager();
		Post post = em.find(Post.class, postId); 		
		em.close();
		return post;
	}

	@SuppressWarnings("unchecked")
	@Override
	public BlogUser readUser(String userid) {
		EntityManager em = factory.createEntityManager();
		BlogUser user = em.find(BlogUser.class, userid); // This is throwing
															// NVarchar abstract
															// error
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
		ArrayList<Post> posts = (ArrayList<Post>) em.createNativeQuery("select * from post", Post.class)
				.getResultList();
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

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Comments> readComments(int postId) {
		EntityManager em = factory.createEntityManager();
		ArrayList<Comments> comments = (ArrayList<Comments>) em
		.createNativeQuery("select * from comments where post_id = :id", Comments.class)
		.setParameter("id", postId).getResultList();
		em.close();
		return comments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Comments> readComments(ArrayList<Integer> postIds) {
		EntityManager em = factory.createEntityManager();
		ArrayList<Comments> comments = (ArrayList<Comments>) em
				.createNativeQuery("select * from comments where post_id in :ids", Comments.class)
				.setParameter("ids", postIds).getResultList();
		return comments;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<BlogUser> readAllUsers() {
		EntityManager em = factory.createEntityManager();
		ArrayList<BlogUser> blogUsers = (ArrayList<BlogUser>) em
				.createNativeQuery("select * from bloguser", BlogUser.class).getResultList();
		em.close();
		return blogUsers;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<String> readUserIds() {
		EntityManager em = factory.createEntityManager();
		ArrayList<String> userIds = (ArrayList<String>) em.createNativeQuery("select userid from bloguser")
				.getResultList();
		em.close();
		return userIds;
	}

	private void tryThis(){
		EntityManager em = factory.createEntityManager();
		System.out.println("starting transaction");
		em.getTransaction().begin();
		em.createNativeQuery("insert into dummy values (2)").executeUpdate();
		em.getTransaction().commit();
		System.out.println("Committed dummy entry");
		em.close();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public BlogUser validateLogin(String userId, String password) {
		
		tryThis();
		
		EntityManager em = factory.createEntityManager();
		String validateQuery = "select * from bloguser where " + " userid = '" + userId + "' and password = '"
				+ password + "'";
		// System.out.println("The query is : " + validateQuery);
		ArrayList<BlogUser> blogUsers = (ArrayList<BlogUser>) em.createNativeQuery(validateQuery, BlogUser.class)
				.getResultList();
		em.close();
		if (blogUsers.size() == 1) {
			return blogUsers.get(0);
		} else {
			return null;
		}
	}

	@Override
	public int getNextPostId() {
		EntityManager em = factory.createEntityManager();
		Object postId = em.createNativeQuery("select max(post_id)+1 from post").getSingleResult();
		System.out.println("Object to string " + postId.toString());
		System.out.println("The next post id is " + postId);
		return 5;
	}

	@Override
	public int postCreate(NewPost newPost) {
		EntityManager em = factory.createEntityManager();
		String query = "insert into post values((select max(post_id)+1 from post),'"
				+ newPost.getTitle() + "','" + newPost.getMessage() + "','" 
				+ newPost.getUserId() + "',sysdate, '" + newPost.getTags() + "','"
				+ newPost.getCategory() + "')";
		em.getTransaction().begin();
		System.out.println("The query formed is " + query);
		String query2 = "insert into post values (6,'6','6','pvedha',sysdate,'t','c')";
		int result = em.createNativeQuery(query).executeUpdate();
		System.out.println("Execution completed");
//		
//		int resultx = em.createNativeQuery("insert into post values((select max(post_id)+1 from post),"
//				+ ":title,:message,:userid,sysdate,:tags,:category)")
//				.setParameter("title", newPost.getTitle())
//				.setParameter("message", newPost.getMessage())
//				.setParameter("userid", newPost.getTitle())
//				.setParameter("tags", newPost.getTitle())
//				.setParameter("category", newPost.getTitle())
//				.executeUpdate();
		em.getTransaction().commit();
		em.close();
		return result;
	}

	@SuppressWarnings({ "unchecked", "null" })
	@Override
	public ArrayList<Post> searchPost(ArrayList<String> keys) {
		EntityManager em = factory.createEntityManager();
		ArrayList<Post> posts = null;
		for (String key : keys) {
			ArrayList<Post> temp = (ArrayList<Post>) em
				.createNativeQuery("select * from post where title like :key or message like :key'", Post.class)
				.setParameter("key", "%"+key+"%").getResultList();
			if (!temp.isEmpty()) posts.addAll(temp);
		}
		em.close();
		return posts;		
	}

}
