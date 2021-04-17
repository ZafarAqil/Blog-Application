package com.cg.blog.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.PostType;
import com.cg.blog.application.exceptions.CommentNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommentRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.services.CommentServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommentServiceTests {

	@Autowired
	CommentServiceImpl commentService;

	@Autowired
	ICommentRepository commentRepository;

	@Autowired
	IPostRepository postRepo;

	@Autowired
	ICommunityRepository communityRepository;

	@Autowired
	IBloggerRepository bloggerRepository;

	Community community;
	Blogger blogger;
	Post post;
	Comment comment;

	@BeforeAll
	void setUp() {
		List<Post> posts = new ArrayList<Post>();

		blogger = new Blogger(1, "name", "xyz@gmail.com", "12121212", "blogger", 0, posts, null, null, null, null, null);
		blogger = bloggerRepository.saveAndFlush(blogger);

		community = new Community(1, "community", "Description", 0, null, null, null, null, null, null, null, posts, blogger);
		community = communityRepository.saveAndFlush(community);

		post = new Post("NewTitle", "Description", blogger, PostType.TEXT, null, null, null, 0, false, false, false, "OC", community);
		postRepo.saveAndFlush(post);

		comment = new Comment();
		comment.setCommentId(1);
		comment.setCommentDescription("This is comment");
		comment.setBlogger(blogger);
		comment.setPost(post);
		commentRepository.saveAndFlush(comment);
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for addComment")
	void testAddComment() {
		assertEquals(comment, commentService.addComment(1, 1, comment));
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for listAllCommentsByPost")
	void testListAllCommentsByPost() {
		assertEquals(comment, commentService.listAllCommentsByPost(1).get(0));
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for deleteCommentById")
	void testDeleteCommentById() {
		commentService.deleteCommentById(1);
		assertThrows(NoSuchElementException.class, () -> commentRepository.findById(1).get());
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for commentNotFoundException")
	void testCommentNotFoundException() {
		assertThrows(CommentNotFoundException.class, () -> commentService.deleteCommentById(11));
	}

}
