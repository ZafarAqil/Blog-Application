package com.cg.blog.application.controllers;

import java.util.Arrays;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Admin;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.services.AdminServiceImpl;

/**
 * 
 * <p>
 * The controller for admin REST endpoints
 * </p>
 * <p>
 * This class handles the CRUD operations for admin entity
 * </p>
 * 
 * @author Group4
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {

	@Autowired
	AdminServiceImpl adminService;

	private final Logger log = LoggerFactory.getLogger(AdminController.class);

	/**
	 * This method is used to add Admin data
	 * 
	 * @param admin object of Admin entity
	 * @return admin object
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(path = "/admin/signup")
	public ResponseEntity<Object> adminSignUp(@Valid @RequestBody Admin admin) { // NOSONAR
		log.info("Admin Controller -- adminSignUp()");
		Admin createdAdmin = adminService.adminSignUp(admin);
		return ResponseEntity.status(201).body(createdAdmin);
	}

	/**
	 * This method is used to create community
	 * 
	 * @param community object of Community entity
	 * @param adminId   of Registered admin
	 * @return community object of created community
	 */

	@PostMapping(path = "/admin/{admin_id}/community/")
	public ResponseEntity<Community> addCommunity(@Valid @RequestBody Community community, // NOSONAR
			@PathVariable(name = "admin_id") int adminId) {
		log.info("Admin Controller -- addCommunity()");
		return ResponseEntity.status(201).body(adminService.addCommunity(community, adminId));
	}

	/**
	 * 
	 * @param communityId of created community
	 * @param adminId     of Registered admin
	 * @return "Community Deleted" when community deleted successfully
	 */

	@DeleteMapping(path = "/admin/{admin_id}/community/{community_id}")
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "admin_id") int adminId) {
		log.info("Admin Controller -- deleteCommunity()");
		adminService.deleteCommunity(communityId, adminId);
		return ResponseEntity.status(200).body(Arrays.asList("Community Deleted"));
	}
	
	@GetMapping(path="/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<String> access() {
	
		return ResponseEntity.status(200).body("Admin");
	}

}
