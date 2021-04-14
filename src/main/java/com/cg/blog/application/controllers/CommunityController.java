package com.cg.blog.application.controllers;

import java.util.List;

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
	
	@RequestMapping(value = "/community",method = RequestMethod.POST)
	public ResponseEntity<Object> addCommunity(@RequestBody Community community)  {
		Community createdCommunity = communityService.addCommunity(community);
		return ResponseEntity.status(201).body(createdCommunity);
	}

	@RequestMapping(value = "/community/{community_id}",method = RequestMethod.PUT)
	public ResponseEntity<Object> updateCommunity(@RequestBody Community community, @PathVariable(name = "community_id") int communityId)  {
		Community createdCommunity = communityService.updateCommunity(community, communityId);
		return ResponseEntity.status(201).body(createdCommunity);
	}
	
	@RequestMapping(value = "/community/{community_id}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCommunity(@PathVariable(name = "community_id") int communityId)  {
		communityService.deleteCommunity(communityId);
		return ResponseEntity.status(200).body("Community Deleted");
	}
	
	@RequestMapping(value = "/community/{search_string}",method = RequestMethod.GET)
	public ResponseEntity<Object> listAllCommunities(@PathVariable(name = "search_string") String searchString)  {
		List<Community> matchedCommunities = communityService.listAllCommunities(searchString);
		return ResponseEntity.status(201).body(matchedCommunities);
	}

}
