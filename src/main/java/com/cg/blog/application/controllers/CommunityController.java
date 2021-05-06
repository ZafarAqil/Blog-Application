package com.cg.blog.application.controllers;

import java.util.List;
import java.util.Set;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Community;
import com.cg.blog.application.services.CommunityServiceImpl;

/**
 * <p>
 * The controller for Community REST endpoints
 * </p>
 * <p>
 * This class handles the CRUD operations for Community entity
 * </p>
 * 
 * @author Group4
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommunityController {

	@Autowired
	CommunityServiceImpl communityService;

	private final Logger log = LoggerFactory.getLogger(CommunityController.class);

	/**
	 * This method is used to add community
	 * 
	 * @param community   object of community entity
	 * @param moderatorId of blogger
	 * @return createdCommunity when community added
	 */
	@PostMapping(path = "/community/{moderator_id}")
	public ResponseEntity<Object> addCommunity(@Valid @RequestBody Community community,
			@PathVariable(name = "moderator_id") int moderatorId) { // NOSONAR
		log.info("Community Controller -- addCommunity()");
		Community createdCommunity = communityService.addCommunity(community, moderatorId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	/**
	 * This method is used to update community
	 * 
	 * @param community   object of community entity
	 * @param communityId of Community
	 * @param moderatorId of blogger
	 * @return createdCommunity when community updated
	 */
	@PutMapping(path = "/community/{community_id}/{moderator_id}")
	public ResponseEntity<Object> updateCommunity(@RequestBody Community community,
			@PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "moderator_id") int moderatorId) { // NOSONAR
		log.info("Community Controller -- updateCommunity()");
		Community createdCommunity = communityService.updateCommunity(community, communityId, moderatorId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	/**
	 * This method is used to delete community
	 * 
	 * @param communityId of community
	 * @param moderatorId of blogger
	 * @return "Community Succesfully Deleted" when community deleted successfully
	 */
	@DeleteMapping(path = "/community/{community_id}/{moderator_id}")
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "moderator_id") int moderatorId) {
		log.info("Community Controller -- deleteCommunity()");
		communityService.deleteCommunity(communityId, moderatorId);
		return ResponseEntity.status(200).body("Community Succesfully Deleted");
	}

	/**
	 * This method is used to get all communities by search string
	 * 
	 * @param searchString
	 * @return list of communities
	 */
	@GetMapping(path = "/community/search/{search_string}/")
	public ResponseEntity<Object> getAllCommunitiesBySearchString(
			@PathVariable(name = "search_string") String searchString) {
		log.info("Community Controller -- getAllCommunitiesBySearchString()");
		List<Community> matchedCommunities = communityService.getAllCommunitiesBySearchString(searchString);
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	/**
	 * This method is used to get set of all communities subscribed by blogger
	 * 
	 * @param bloggerId of Blogger
	 * @return set of communities
	 */
	@GetMapping(path = "/community/blogger/{blogger_id}")
	public ResponseEntity<Object> getAllCommunitiesSubscribedByBlogger(
			@PathVariable(name = "blogger_id") int bloggerId) {
		log.info("Community Controller -- getAllCommunitiesSubscribedByBlogger()");
		Set<Community> matchedCommunities = communityService.getAllCommunitiesByBlogger(bloggerId);
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	/**
	 * This method is used to get all communities
	 * 
	 * @return list of all communities
	 */
	@GetMapping(path = "/communities")
	public ResponseEntity<Object> getAllCommunities() {
		log.info("Community Controller -- getAllCommunities()");
		List<Community> matchedCommunities = communityService.getAllCommunities();
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	/**
	 * This method is used to return community by community Id
	 * 
	 * @param communityId of community
	 * @return community object
	 */
	
	@GetMapping(path = "/community/{community_id}")
//	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<Object> getCommunity(@PathVariable(name = "community_id") int communityId) {
		log.info("Community Controller -- getCommunity()");
		Community community = communityService.getCommunity(communityId);
		return ResponseEntity.status(200).body(community);
	}

}