package com.cg.blog.application.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.services.BloggerServiceImpl;
import com.cg.blog.application.services.IModeratorService;

@RestController
public class ModeratorController {

	@Autowired
	BloggerServiceImpl bloggerService;
	
	@Autowired
	IModeratorService moderatorService;
	
	@RequestMapping(value = "moderator/{moderator_id}/posts/{post_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePost(@PathVariable int moderator_id, @PathVariable int post_id) {
		moderatorService.deletePost(moderator_id, post_id);
		return ResponseEntity.status(200).body("Post Deleted");
	}
	
}
