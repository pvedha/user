package com.blog.biz;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.easymock.EasyMock;
import org.easymock.Mock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.blog.api.BlogUser;
import com.blog.api.DuplicateUserException;
import com.blog.api.InvalidCommentException;
import com.blog.api.InvalidPostException;
import com.blog.api.InvalidSearchKeyException;
import com.blog.api.InvalidUserException;
import com.blog.api.Post;
import com.blog.dao.OracleDAOImpl;
import com.blog.dto.AuthenticationDto;
import com.blog.dto.NewChat;
import com.blog.dto.NewComment;
import com.blog.rest.PostController;

public class BlogTest {
	Blog blog = new Blog();
    @Mock
    OracleDAOImpl dao;
	
    @BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		assertTrue("Trial Test", 7>5);
	}
	
	/*@Test(expected=InvalidSearchKeyException.class)
	public void searchContents() throws InvalidSearchKeyException  {
		ArrayList<String> keys = null;
		keys.add("hello;");
		ArrayList<Post> post = null; 
				post.add((new Post(1,"a","b", null, null, "a", "b")));
		EasyMock.expect(dao.searchPost(keys)).andReturn(post);
		EasyMock.replay(dao);
		blog.searchPost(" ");
	}*/
	
	@Test
	public void testUserValidate1() {
		assertFalse(blog.validateToken("abc ", "def "));		
	}
	
	@Test
	public void testUserValidate2() {
		assertFalse(blog.validateToken(" ", " "));		
	}
	
	@Test
	public void testUserValidate3() {
		assertTrue(blog.validateToken("hello", new AuthenticationDto("hello", "a", "b").getToken()));		
	}
	
	@Test(expected=InvalidSearchKeyException.class)
	public void searchContents() throws InvalidSearchKeyException  {
		blog.searchPost(" ");
	}
	
	@Test(expected=InvalidSearchKeyException.class)
	public void searchContents2() throws InvalidSearchKeyException  {
		blog.searchPost(null);
	}
	
	@Test(expected=InvalidSearchKeyException.class)
	public void searchContents3() throws InvalidSearchKeyException  {
		blog.searchPost("");
	}
	
	@Test(expected=InvalidUserException.class)
	public void usercreate() throws InvalidUserException, DuplicateUserException  {
		blog.createUser(null);
	}
	
	@Test(expected=InvalidUserException.class)
	public void usercreate2() throws InvalidUserException, DuplicateUserException  {
		blog.createUser(new BlogUser());
	}
	
	@Test(expected=InvalidCommentException.class)
	public void chatcreate2() throws InvalidCommentException {
		blog.addChat(null);
	}
	@Test(expected=InvalidCommentException.class)
	public void chatcreate3() throws InvalidCommentException {
		blog.addChat(new NewChat());
	}
	
	@Test(expected=InvalidCommentException.class)
	public void commentcreate2() throws InvalidCommentException {
		blog.addComment(null);
	}
	@Test(expected=InvalidCommentException.class)
	public void commentcreate3() throws InvalidCommentException {
		blog.addComment(new NewComment());
	}
	
	
	
	
	

}
