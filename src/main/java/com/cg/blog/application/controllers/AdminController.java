package com.cg.blog.application.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.services.AdminServiceImpl;

@RestController
public class AdminController {

	@Autowired
	AdminServiceImpl adminService;

	private final Logger log = LoggerFactory.getLogger(AdminController.class);

	@PostMapping(path = "/admin/signup")
	public ResponseEntity<Object> adminSignUp(@Valid @RequestBody Admin admin) { // NOSONAR
		log.info("Admin Controller -- adminSignUp()");
		Admin createdAdmin = adminService.adminSignUp(admin);
		return ResponseEntity.status(201).body(createdAdmin);
	}

	@PostMapping(path = "/admin/{admin_id}/community/")
	public ResponseEntity<Object> addCommunity(@Valid @RequestBody Community community, // NOSONAR
			@PathVariable(name = "admin_id") int adminId) {
		log.info("Admin Controller -- addCommunity()");
		Community createdCommunity = adminService.addCommunity(community, adminId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@DeleteMapping(path = "/admin/{admin_id}/community/{community_id}")
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "admin_id") int adminId) {
		log.info("Admin Controller -- deleteCommunity()");
		adminService.deleteCommunity(communityId, adminId);
		return ResponseEntity.status(200).body("Community Deleted");
	}

}
