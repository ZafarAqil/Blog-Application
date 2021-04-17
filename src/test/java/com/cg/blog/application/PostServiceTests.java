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

import com.cg.blog.application.entities.AwardType;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.PostType;
import com.cg.blog.application.entities.VoteType;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.exceptions.InvalidVoteException;
import com.cg.blog.application.exceptions.PostNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.services.PostServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PostServiceTests {

	@Autowired
	PostServiceImpl postService;

	@Autowired
	IPostRepository postRepo;

	@Autowired
	ICommunityRepository communityRepository;

	@Autowired
	IBloggerRepository bloggerRepository;

	Community community;
	Blogger blogger;
	Post post;

	@BeforeAll
	void setUp() {
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

	}

	@Transactional
	@Test
	@DisplayName(value = "Test for addPost")
	void testAddPost() {
		assertEquals(post, postService.addPost(1, 1, post));
	}

	@Test
	@DisplayName(value = "Test for addPost when community does not exists")
	void testFailAddPost() {
		assertThrows(CommunityNotFoundException.class, () -> postService.addPost(99, 1, post));
	}



	@Transactional
	@Test
	@DisplayName(value = "Test for updatePost")
	void testUpdatePost() {
		Post otherPost = new Post(1, "NewTitle", "Description", blogger, PostType.TEXT, null, null, null, 0, false, false, false, "OC", community);
		assertEquals("NewTitle", postService.updatePost(1, otherPost).getTitle());
	}

	@Test
	@DisplayName(value = "Test for getPostBySearchString")
	void testGetPostBySearchString() {
		assertEquals("SampleTitle", postService.getPostBySearchString("SampleTitle").get(0).getTitle());
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for getPostByBlogger")
	void testGetPostByBlogger() {
		assertEquals(post, postService.getPostByBlogger(1).get(0));
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for giveAwardPost")
	void testGiveAwardPost() {
		postService.giveAwardPost(AwardType.GOLD, 1, 1);
		assertEquals(AwardType.GOLD, postRepo.findById(1).get().getAwardsReceived().get(0).getAwardType());
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for votePost")
	void testVotePost() {
		postService.votePost(VoteType.UPVOTE, 1, 1);
		assertEquals(1, postRepo.findById(1).get().getVotes());
	}
	@Transactional
	@Test
	@DisplayName(value = "Test for deletePost")
	void testDeletePost() {
		postRepo.deleteById(1);
		assertThrows(NoSuchElementException.class, () -> postRepo.findById(1).get());
	}
	@Transactional
	@Test
	@DisplayName(value = "Test for InvalidVoteException")
	void testInvalidVoteException() {
		postService.votePost(VoteType.UPVOTE, 1, 1);
		assertThrows(InvalidVoteException.class, () -> postService.votePost(VoteType.UPVOTE, 1, 1));
	}
	@Transactional
	@Test
	@DisplayName(value = "Test for PostNotFoundException")
	void testPostNotFoundException() {
		assertThrows(PostNotFoundException.class, () -> postService.deletePost(11));
	}

}