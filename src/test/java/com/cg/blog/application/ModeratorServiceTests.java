package com.cg.blog.application;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.PostType;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.services.BloggerServiceImpl;
import com.cg.blog.application.services.ModeratorServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ModeratorServiceTests {

	@Autowired
	ModeratorServiceImpl moderatorService;

	@Autowired
	IPostRepository postRepo;

	@Autowired
	ICommunityRepository communityRepository;

	@Autowired
	BloggerServiceImpl bloggerService;

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
		Set<Blogger> bloggers = new HashSet<Blogger>();
		community.setBloggers(bloggers);

		blogger = new Blogger();
		blogger.setId(1);
		blogger.setName("name");
		blogger.setPassword("aaaaaaaa");
		blogger.setEmail("xyz@gmail.com");
		blogger.setPosts(posts);
		Set<Community> moderatesCommunities = new HashSet<Community>();
		blogger.setModCommunities(moderatesCommunities);

		community.setModeratedBy(blogger);
		blogger.getModCommunities().add(community);

		community = communityRepository.saveAndFlush(community);
		blogger = bloggerService.addBlogger(blogger);

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
	@DisplayName(value = "Test for deletePost")
	void testDeletePost() {
		moderatorService.deletePost(1, 1);
		assertThrows(NoSuchElementException.class, () -> postRepo.findById(1).get());
	}

}
