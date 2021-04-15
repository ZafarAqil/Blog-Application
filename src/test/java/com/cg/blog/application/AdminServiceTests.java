package com.cg.blog.application;

import static org.assertj.core.api.Assertions.assertThat;
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
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.repositories.IAdminRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.services.AdminServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AdminServiceTests {

	@Autowired
	IAdminRepository adminRepository;

	@Autowired
	ICommunityRepository communityRepository;

	@Autowired
	AdminServiceImpl adminService;

	
	Community community;
	Admin admin;

	@BeforeAll
	public void setUp() {
		
		admin = new Admin(1,"admin","admin@gmail.com","password","8888888888");		
		adminRepository.saveAndFlush(admin);
		
		community = new Community();
		community.setCommunityId(1);
		community.setTitle("community");
		community.setCommunityDescription("Description");
		List<Post> posts = new ArrayList<Post>();
		community.setPosts(posts);
		Set<Blogger> bloggers = new HashSet<Blogger>();
		community.setBloggers(bloggers);
		communityRepository.saveAndFlush(community);
	}
	
	@Test
	public void testAdminSignUp() {
		assertEquals(admin, adminService.adminSignUp(admin));
	}

	@Transactional
	@Test
	public void testAddCommunity() {
		assertEquals(community, adminService.addCommunity(community,1));
	}
	
	@Transactional
	@Test
	public void testDeleteCommunity() {
		adminService.deleteCommunity(1,1);
		assertThrows(NoSuchElementException.class, () -> communityRepository.findById(1).get());
	}

	
}
