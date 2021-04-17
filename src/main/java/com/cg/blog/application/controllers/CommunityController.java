package com.cg.blog.application.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Community;
import com.cg.blog.application.services.CommunityServiceImpl;

@RestController
public class CommunityController {

	@Autowired
	CommunityServiceImpl communityService;

	private final Logger log = LoggerFactory.getLogger(CommunityController.class);

	@PostMapping(path = "/community/{moderator_id}")
	public ResponseEntity<Object> addCommunity(@Valid @RequestBody Community community, @PathVariable(name = "moderator_id") int moderatorId) { // NOSONAR
		log.info("Community Controller -- addCommunity()");
		Community createdCommunity = communityService.addCommunity(community, moderatorId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@PutMapping(path = "/community/{community_id}/{moderator_id}")
	public ResponseEntity<Object> updateCommunity(@RequestBody Community community, @PathVariable(name = "community_id") int communityId, @PathVariable(name = "moderator_id") int moderatorId) { // NOSONAR
		log.info("Community Controller -- updateCommunity()");
		Community createdCommunity = communityService.updateCommunity(community, communityId, moderatorId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@DeleteMapping(path = "/community/{community_id}/{moderator_id}")
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "moderator_id") int moderatorId) {
		log.info("Community Controller -- deleteCommunity()");
		communityService.deleteCommunity(communityId, moderatorId);
		return ResponseEntity.status(200).body("Community Succesfully Deleted");
	}

	@GetMapping(path = "/community/search/{search_string}/")
	public ResponseEntity<Object> getAllCommunitiesBySearchString(
			@PathVariable(name = "search_string") String searchString) {
		log.info("Community Controller -- getAllCommunitiesBySearchString()");
		List<Community> matchedCommunities = communityService.getAllCommunitiesBySearchString(searchString);
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	@GetMapping(path = "/community/blogger/{blogger_id}")
	public ResponseEntity<Object> getAllCommunitiesSubscribedByBlogger(
			@PathVariable(name = "blogger_id") int bloggerId) {
		log.info("Community Controller -- getAllCommunitiesSubscribedByBlogger()");
		Set<Community> matchedCommunities = communityService.getAllCommunitiesByBlogger(bloggerId);
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	@GetMapping(path = "/communities")
	public ResponseEntity<Object> getAllCommunities() {
		log.info("Community Controller -- getAllCommunities()");
		List<String> matchedCommunities = communityService.getAllCommunities();
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	@GetMapping(path = "/community/{community_id}")
	public ResponseEntity<Object> getCommunity(@PathVariable(name = "community_id") int communityId) {
		log.info("Community Controller -- getCommunity()");
		Community community = communityService.getCommunity(communityId);
		return ResponseEntity.status(200).body(community);
	}

}
