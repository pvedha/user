package com.blog.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.exception.SQLGrammarException;

import com.blog.api.BlogUser;
import com.blog.api.Category;
import com.blog.api.Comments;
import com.blog.api.Favourite;
import com.blog.api.Post;
import com.blog.dto.NewPost;

@SuppressWarnings("unchecked")
public class OracleDAOImpl implements DAO {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("blog");

	@Override
	public int postCreate(Post post) {
		EntityManager em = factory.createEntityManager();
		System.out.println("Beginning post persistance " + post.getPostId() + post.getPostedBy().getUserid());
		em.getTransaction().begin();
		System.out.println("Post Id for submitting " + post.getPostId());
		System.out.println("User id is " + post.getPostedBy().getUserid());
		BlogUser user = em.find(BlogUser.class, post.getPostedBy().getUserid());
		System.out.println("Received user id " + user.getUserid());
		em.merge(user);
		post.setPostedBy(user);
		em.persist(post);
		System.out.println("did persist");
		em.getTransaction().commit();
		System.out.println("committed");
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
		BlogUser user = em.find(BlogUser.class, userid);
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

	private void tryThis() {
		EntityManager em = factory.createEntityManager();
		System.out.println("starting transaction");
		em.getTransaction().begin();
		em.createNativeQuery("insert into dummy values (2)").executeUpdate();
		em.getTransaction().commit();
		System.out.println("Committed dummy entry");
		em.close();
	}

	@Override
	public BlogUser validateLogin(String userId, String password) {

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
		em.getTransaction().begin();
		int result = em
				.createNativeQuery("insert into post values((select max(post_id)+1 from post),"
						+ ":title,:message,:userid,sysdate,:tags,:category)")
				.setParameter("title", newPost.getTitle()).setParameter("message", newPost.getMessage())
				.setParameter("userid", newPost.getUserId()).setParameter("tags", newPost.getTags())
				.setParameter("category", newPost.getCategory()).executeUpdate();
		em.getTransaction().commit();
		em.close();
		return result;
	}

	@SuppressWarnings("null")
	@Override
	public ArrayList<Post> searchPost(ArrayList<String> keys) {
		EntityManager em = factory.createEntityManager();
		ArrayList<Post> posts = null;
		for (String key : keys) {
			ArrayList<Post> temp = (ArrayList<Post>) em
					.createNativeQuery("select * from post where title like :key or message like :key'", Post.class)
					.setParameter("key", "%" + key + "%").getResultList();
			if (!temp.isEmpty())
				posts.addAll(temp);
		}
		em.close();
		return posts;
	}

	@Override
	public ArrayList<String> readCategory() {
		EntityManager em = factory.createEntityManager();
		ArrayList<Category> categories = (ArrayList<Category>) em
				.createNativeQuery("select * from category", Category.class).getResultList();
		ArrayList<String> categoryString = new ArrayList<>();
		for (Category category : categories) {
			categoryString.add(category.getCategory());
		}
		return categoryString;
	}

	@Override
	public ArrayList<Post> searchByCategory(String category) {
		EntityManager em = factory.createEntityManager();
		System.out.println("Search by category 1 " + category);
		try {
			ArrayList<Post> posts = (ArrayList<Post>) em
					.createNativeQuery("select * from post where category = :cat", Post.class)
					.setParameter("cat", category).getResultList();
			return posts;
		} catch (SQLGrammarException sqlE) {
			System.out.println("Caught SQL Grammer exception");
			return new ArrayList<Post>();
		} catch (javax.persistence.PersistenceException sqlP) {
			System.out.println("Caught SQL Grammer exception");
			return new ArrayList<Post>();
		} catch (Exception e) {
			System.out.println("Exception for empty list ");
			return new ArrayList<Post>();
		}
	}

	@Override
	public boolean addFavourite(String userId, int postId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();		
		Favourite favourite = new Favourite(userId, postId);
		em.persist(favourite);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
	@Override
	public boolean removeFavourite(String userId, int postId){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();		
		Favourite favourite = new Favourite(userId, postId);
		em.remove(favourite);
		em.getTransaction().commit();
		em.close();
		return true;
	}
	
}
