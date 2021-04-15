package com.cg.blog.application.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.services.AdminServiceImpl;

@RestController
public class AdminController {

	@Autowired
	AdminServiceImpl adminService;

	@RequestMapping(value = "/admin/signup", method = RequestMethod.POST)
	public ResponseEntity<Object> adminSignUp(@Valid @RequestBody Admin admin) {
		Admin createdAdmin = adminService.adminSignUp(admin);
		return ResponseEntity.status(201).body(createdAdmin);
	}
	@RequestMapping(value = "/admin/{admin_id}", method = RequestMethod.POST)
	public ResponseEntity<Object> addCommunity(@Valid @RequestBody Community community, @PathVariable(name = "admin_id") int adminId) {
		Community createdCommunity = adminService.addCommunity(community, adminId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@RequestMapping(value = "/admin/{admin_id}/{community_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId,  @PathVariable(name = "admin_id") int adminId) {
		adminService.deleteCommunity(communityId, adminId);
		return ResponseEntity.status(200).body("Community Deleted");
	}

	
}
