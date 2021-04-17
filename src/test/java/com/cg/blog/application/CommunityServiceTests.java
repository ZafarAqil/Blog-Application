package com.cg.blog.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.services.BloggerServiceImpl;
import com.cg.blog.application.services.CommunityServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CommunityServiceTests {

	@Autowired
	CommunityServiceImpl communityService;

	@Autowired
	ICommunityRepository communityRepository;

	@Autowired
	BloggerServiceImpl bloggerService;

	@Autowired
	IBloggerRepository bloggerRepository;

	Community community;
	Blogger blogger;

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

		community.setModeratedBy(blogger);
		moderatesCommunities.add(community);

		blogger.setModCommunities(moderatesCommunities);

		blogger = bloggerRepository.saveAndFlush(blogger);
		community = communityRepository.saveAndFlush(community);

	}

	@Transactional
	@Test
	@DisplayName(value = "Test for addCommunity")
	void testAddCommunity() {
		assertThrows(BloggerNotFoundException.class, () -> communityService.addCommunity(community, 2));

	}

	@Transactional
	@Test
	@DisplayName(value = "Test for updateCommunity")
	void testUpdateCommunity() {
		Community otherCommunity = new Community();
		otherCommunity.setCommunityId(1);
		otherCommunity.setTitle("NewTitle");
		otherCommunity.setCommunityDescription("Description");
		List<Post> posts = new ArrayList<Post>();
		otherCommunity.setPosts(posts);
		otherCommunity.setModeratedBy(blogger);
		Set<Blogger> bloggers = new HashSet<Blogger>();
		otherCommunity.setBloggers(bloggers);
		communityRepository.saveAndFlush(otherCommunity);

		assertEquals("NewTitle", communityService.updateCommunity(otherCommunity, 1, 1).getTitle());
	}

	@Test
	@DisplayName(value = "Test for getAllCommunitiesBySearchString")
	void testGetAllCommunitiesBySearchString() {
		assertEquals(community, communityService.getAllCommunitiesBySearchString("community").get(0));
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for getAllCommunitiesByBlogger")
	void testGetAllCommunitiesByBlogger() {
		bloggerService.joinCommunity(1, 1);
		assertEquals(1, communityService.getAllCommunitiesByBlogger(1).size());
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for getAllCommunities")
	void testGetAllCommunities() {
		List<String> list = new ArrayList<String>();
		list.add(community.getTitle());
		assertEquals(list, communityService.getAllCommunities());

	}

	@Transactional
	@Test
	@DisplayName(value = "Test for getCommunity")
	void testGetCommunity() {

		assertEquals(community, communityService.getCommunity(1));

	}

	@Transactional
	@Test
	@DisplayName(value = "Test for deleteCommunity")
	void testDeleteCommunity() {
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
