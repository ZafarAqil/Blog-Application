package com.cg.blog.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.services.BloggerServiceImpl;
import com.cg.blog.application.services.ModeratorServiceImpl;

@RestController
public class ModeratorController {

	@Autowired
	BloggerServiceImpl bloggerService;
	
	@Autowired
	ModeratorServiceImpl moderatorService;
	
	@RequestMapping(value = "/moderator/{moderator_id}/posts/{post_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePost(@PathVariable(name = "moderator_id") int moderatorId, @PathVariable(name = "post_id") int postId) {
		moderatorService.deletePost(moderatorId, postId);
		return ResponseEntity.status(200).body("Post Successfully Deleted");
	}
	
}
