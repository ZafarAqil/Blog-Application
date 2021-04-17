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

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.exceptions.AdminNotFoundException;
import com.cg.blog.application.repositories.IAdminRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.services.AdminServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class AdminServiceTests {

	@Autowired
	IAdminRepository adminRepository;

	@Autowired
	ICommunityRepository communityRepository;

	@Autowired
	AdminServiceImpl adminService;

	Community community;
	Admin admin;

	@BeforeAll
	void setUp() {

		admin = new Admin(1, "admin", "admin@gmail.com", "password", "8888888888");
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
	@DisplayName(value = "Test for adminSignUp")
	void testAdminSignUp() {
		assertEquals(admin, adminService.adminSignUp(admin));
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for addCommunity")
	void testAddCommunity() {
		assertEquals(community, adminService.addCommunity(community, 1));
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for deleteCommunity")
	void testDeleteCommunity() {
		adminService.deleteCommunity(1, 1);
		assertThrows(NoSuchElementException.class, () -> communityRepository.findById(1).get());
	}

	@Transactional
	@Test
	@DisplayName(value = "Test for AdmitNotFoundException")
	void testAdminNotFoundException() {
		assertThrows(AdminNotFoundException.class, () -> adminService.deleteCommunity(1,5));
	}

}
