package com.cg.blog.application.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.services.BloggerServiceImpl;

@RestController
public class BloggerController {

	@Autowired
	BloggerServiceImpl bloggerService;

	@PostMapping(path = "/blogger")
	public ResponseEntity<Object> addBlogger(@Valid @RequestBody Blogger blogger) { // NOSONAR
		Blogger addedBlogger = bloggerService.addBlogger(blogger);
		return ResponseEntity.status(201).body(addedBlogger);
	}

	@PutMapping(path = "/blogger/{blogger_id}")
	public ResponseEntity<Object> updateBlogger(@Valid @RequestBody Blogger blogger, // NOSONAR
			@PathVariable(name = "blogger_id") int bloggerId) {
		Blogger updatedBlogger = bloggerService.updateBlogger(blogger, bloggerId);
		return ResponseEntity.status(201).body(updatedBlogger);
	}

	@DeleteMapping(path = "/blogger/{blogger_id}")
	public ResponseEntity<Object> deleteBlogger(@PathVariable(name = "blogger_id") int bloggerId) {
		bloggerService.deleteBlogger(bloggerId);
		return ResponseEntity.status(200).body("Blogger successfully deleted");
	}

	@GetMapping(path = "/blogger/{blogger_id}")
	public ResponseEntity<Object> getBlogger(@PathVariable(name = "blogger_id") int bloggerId) {
		Blogger blogger = bloggerService.getBlogger(bloggerId);
		return ResponseEntity.status(200).body(blogger);
	}

	@GetMapping(path = "/bloggers")
	public ResponseEntity<Object> getAllBloggers() {
		List<Blogger> bloggers = bloggerService.getAllBloggers();
		return ResponseEntity.status(200).body(bloggers);
	}

	@PutMapping(path = "/blogger/{community_id}/{blogger_id}")
	public ResponseEntity<Object> joinCommunity(@PathVariable(name = "blogger_id") int bloggerId,
			@PathVariable(name = "community_id") int communityId) {
		bloggerService.joinCommunity(communityId, bloggerId);
		return ResponseEntity.status(201).body("Joined Community");
	}

	@GetMapping(path = "/community/{community_id}/bloggers")
	public ResponseEntity<Object> getBloggerList(@PathVariable("community_id") int communityId) {
		Set<Blogger> bloggers = bloggerService.getBloggerList(communityId);
		return ResponseEntity.status(200).body(bloggers);
	}

}
