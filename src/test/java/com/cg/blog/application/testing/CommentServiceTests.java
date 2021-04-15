package com.cg.blog.application.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.PostType;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommentRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.services.CommentServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommentServiceTests {

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
	public void setUp() {
		community = new Community();
		community.setCommunityId(1);
		community.setTitle("community");
		community.setCommunityDescription("Description");
		List<Post> posts = new ArrayList<Post>();
		community.setPosts(posts);
		community = communityRepository.saveAndFlush(community);

		blogger = new Blogger();
		blogger.setId(1);
		blogger.setName("name");
		blogger.setPassword("aaaaaaaa");
		blogger.setEmail("xyz@gmail.com");
		blogger.setPosts(posts);
		blogger = bloggerRepository.saveAndFlush(blogger);

		post = new Post();
		post.setPostId(1);
		post.setTitle("SampleTitle");
		post.setDescription("SomethingUseless");
		post.setContent(PostType.TEXT);
		post.setNotSafeForWork(false);
		post.setSpoiler(false);
		post.setOriginalContent(false);
		post.setFlair("OC");
		post.setCreatedBy(blogger);
		post.setCommunity(community);
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
	public void testAddComment() {
		assertEquals(comment, commentService.addComment(1,1,comment));
	}

	@Transactional
	@Test
	public void testListAllCommentsByPost() {
		assertEquals(comment, commentService.listAllCommentsByPost(1).get(0));
	}
	
	@Transactional
	@Test
	public void testDeleteCommentById() {
		commentService.deleteCommentById(1);
		assertThrows(NoSuchElementException.class, () -> commentRepository.findById(1).get());
	}

}
