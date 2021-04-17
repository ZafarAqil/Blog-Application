package com.cg.blog.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.services.BloggerServiceImpl;
import com.cg.blog.application.services.ModeratorServiceImpl;

@RestController
public class ModeratorController {

	@Autowired
	BloggerServiceImpl bloggerService;

	@Autowired
	ModeratorServiceImpl moderatorService;

	private final Logger log = LoggerFactory.getLogger(ModeratorController.class);

	@DeleteMapping(path = "/moderator/{moderator_id}/posts/{post_id}")
	public ResponseEntity<Object> deletePost(@PathVariable(name = "moderator_id") int moderatorId,
			@PathVariable(name = "post_id") int postId) {
		log.info("Moderator Controller -- deletePost()");
		moderatorService.deletePost(moderatorId, postId);
		return ResponseEntity.status(200).body("Post Successfully Deleted");
	}
}
