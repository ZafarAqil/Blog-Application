package com.cg.blog.application.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.services.ModeratorServiceImpl;

/**
 * <p>
 * The controller for Moderator REST endpoints
 * </p>
 * <p>
 * This class handles the Delete operation for Moderator entity
 * </p>
 * 
 * @author Group4
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ModeratorController {

	@Autowired
	ModeratorServiceImpl moderatorService;

	private final Logger log = LoggerFactory.getLogger(ModeratorController.class);

	/**
	 * This method is used to delete post
	 * 
	 * @param moderatorId moderatorId of Moderator
	 * @param postId      postId of Post
	 * @return
	 */
	@DeleteMapping(path = "/moderator/{moderator_id}/posts/{post_id}")
	public ResponseEntity<Object> deletePost(@PathVariable(name = "moderator_id") int moderatorId,
			@PathVariable(name = "post_id") int postId) {
		log.info("Moderator Controller -- deletePost()");
		moderatorService.deletePost(moderatorId, postId);
		return ResponseEntity.status(200).body("Post Successfully Deleted");
	}
}