package com.cg.blog.application.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.services.BloggerServiceImpl;
import com.cg.blog.application.services.CommunityServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CommunityServiceTests {

	@Autowired
	CommunityServiceImpl communityService;

	@Autowired
	ICommunityRepository communityRepository;

	@Autowired
	BloggerServiceImpl bloggerService;

	Community community;
	Blogger blogger;

	@BeforeAll
	public void setUp() {
		community = new Community();
		community.setCommunityId(1);
		community.setTitle("community");
		community.setCommunityDescription("Description");
		List<Post> posts = new ArrayList<Post>();
		community.setPosts(posts);
		Set<Blogger> bloggers = new HashSet<Blogger>();
		community.setBloggers(bloggers);
		community = communityRepository.saveAndFlush(community);

		blogger = new Blogger();
		blogger.setId(1);
		blogger.setName("name");
		blogger.setPassword("aaaaaaaa");
		blogger.setEmail("xyz@gmail.com");
		blogger.setPosts(posts);
		blogger = bloggerService.addBlogger(blogger);

	}

	@Transactional
	@Test
	public void testAddCommunity() {
		assertEquals(community, communityService.addCommunity(community));
	}

	@Transactional
	@Test
	public void testUpdateCommunity() {
		Community otherCommunity = new Community();
		otherCommunity.setCommunityId(1);
		otherCommunity.setTitle("NewTitle");
		otherCommunity.setCommunityDescription("Description");
		List<Post> posts = new ArrayList<Post>();
		otherCommunity.setPosts(posts);
		Set<Blogger> bloggers = new HashSet<Blogger>();
		community.setBloggers(bloggers);
		communityRepository.saveAndFlush(otherCommunity);

		assertEquals("NewTitle", communityService.updateCommunity(otherCommunity, 1).getTitle());
	}

	@Test
	public void testListAllCommunities() {
		assertEquals(community, communityService.listAllCommunities("community").get(0));
	}

	@Transactional
	@Test
	public void testListAllCommunitiesByBlogger() {
		bloggerService.joinCommunity(1, 1);
		assertEquals(1, communityService.listAllCommunitiesByBlogger(1).size());
	}

	@Transactional
	@Test
	public void testDeleteCommunity() {
		Community otherCommunity = new Community();
		otherCommunity.setCommunityId(2);
		otherCommunity.setTitle("NewCommunity");
		otherCommunity.setCommunityDescription("Description");
		List<Post> posts = new ArrayList<Post>();
		otherCommunity.setPosts(posts);
		Set<Blogger> bloggers = new HashSet<Blogger>();
		otherCommunity.setBloggers(bloggers);
		communityRepository.saveAndFlush(otherCommunity);
		communityRepository.delete(otherCommunity);
		assertThrows(NoSuchElementException.class, () -> communityRepository.findById(2).get());
	}

}
