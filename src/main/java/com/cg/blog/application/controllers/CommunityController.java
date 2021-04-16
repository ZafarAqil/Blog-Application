package com.cg.blog.application.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Community;
import com.cg.blog.application.services.CommunityServiceImpl;

@RestController
public class CommunityController {

	@Autowired
	CommunityServiceImpl communityService;

	@RequestMapping(value = "/community/{moderator_id}", method = RequestMethod.POST)
	public ResponseEntity<Object> addCommunity(@Valid @RequestBody Community community,	@PathVariable(name = "moderator_id") int moderatorId) { //NOSONAR
		Community createdCommunity = communityService.addCommunity(community, moderatorId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@RequestMapping(value = "/community/{community_id}/{moderator_id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCommunity(@RequestBody Community community, @PathVariable(name = "community_id") int communityId, @PathVariable(name = "moderator_id") int moderatorId) { //NOSONAR
		Community createdCommunity = communityService.updateCommunity(community, communityId, moderatorId);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@RequestMapping(value = "/community/{community_id}/{moderator_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "moderator_id") int moderatorId) {
		communityService.deleteCommunity(communityId, moderatorId);
		return ResponseEntity.status(200).body("Community Succesfully Deleted");
	}

	@RequestMapping(value = "/community/search/{search_string}/", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCommunitiesBySearchString(
			@PathVariable(name = "search_string") String searchString) {
		List<Community> matchedCommunities = communityService.getAllCommunitiesBySearchString(searchString);
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	@RequestMapping(value = "/community/blogger/{blogger_id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCommunitiesSubscribedByBlogger(@PathVariable(name = "blogger_id") int bloggerId) {
		Set<Community> matchedCommunities = communityService.getAllCommunitiesByBlogger(bloggerId);
		return ResponseEntity.status(200).body(matchedCommunities);
	}

	@RequestMapping(value = "/communities", method = RequestMethod.GET)
	public ResponseEntity<Object> getAllCommunities() {
		List<String> matchedCommunities = communityService.getAllCommunities();
		return ResponseEntity.status(200).body(matchedCommunities);
	}
	
	@RequestMapping(value = "/community/{community_id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getCommunity(@PathVariable(name = "community_id") int communityId) {
		Community community = communityService.getCommunity(communityId);
		return ResponseEntity.status(200).body(community);
	}

}
