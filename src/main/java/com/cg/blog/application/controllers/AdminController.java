package com.cg.blog.application.controllers;

import javax.validation.Valid;

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

	@PostMapping(path = "/admin/signup")
	public ResponseEntity<Object> adminSignUp(@Valid @RequestBody Admin admin) { // NOSONAR
		Admin createdAdmin = adminService.adminSignUp(admin);
		return ResponseEntity.status(201).body(createdAdmin);
	}

	@PostMapping(path = "/admin/{admin_id}/community/")
	public ResponseEntity<Object> addCommunity(@Valid @RequestBody Community community, // NOSONAR
			@PathVariable(name = "admin_id") int adminId) {
		Community createdCommunity = adminService.addCommunity(community, adminId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@DeleteMapping(path = "/admin/{admin_id}/community/{community_id}")
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "admin_id") int adminId) {
		adminService.deleteCommunity(communityId, adminId);
		return ResponseEntity.status(200).body("Community Deleted");
	}

}
