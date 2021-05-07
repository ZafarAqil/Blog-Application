package com.cg.blog.application.controllers;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.services.BloggerServiceImpl;

/**
 * 
 * <p>
 * The controller for Blogger REST endpoints
 * </p>
 * <p>
 * This class handles the CRUD operations for Blogger entity
 * </p>
 * 
 * @author Group4
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BloggerController {

	@Autowired
	BloggerServiceImpl bloggerService;

	private final Logger log = LoggerFactory.getLogger(BloggerController.class);

	/**
	 * This method is used to add Blogger data
	 * 
	 * @param blogger object of Blogger entity
	 * @return admin object
	 */
	@PostMapping(path = "/blogger")
	public ResponseEntity<Object> addBlogger(@Valid @RequestBody Blogger blogger) { // NOSONAR
		log.info("Blogger Controller -- addBlogger()");
		Blogger addedBlogger = bloggerService.addBlogger(blogger);
		return ResponseEntity.status(201).body(addedBlogger);
	}

	/**
	 * This method is used to update blogger data
	 * 
	 * @param blogger   object of Blogger entity
	 * @param bloggerId of Registered Blogger
	 * @return blogger object of created Blogger
	 */

	@PutMapping(path = "/blogger/{blogger_id}")
	public ResponseEntity<Object> updateBlogger(@Valid @RequestBody Blogger blogger, // NOSONAR
			@PathVariable(name = "blogger_id") int bloggerId) {
		log.info("Blogger Controller -- updateBlogger()");
		Blogger updatedBlogger = bloggerService.updateBlogger(blogger, bloggerId);
		return ResponseEntity.status(201).body(updatedBlogger);
	}

	/**
	 * This method is used to delete Blogger data
	 * 
	 * @param bloggerId of created blogger
	 * @return "Blogger Deleted" when blogger deleted successfully
	 */
	@DeleteMapping(path = "/blogger/{blogger_id}")
	public ResponseEntity<Object> deleteBlogger(@PathVariable(name = "blogger_id") int bloggerId) {
		log.info("Blogger Controller -- deleteBlogger()");
		bloggerService.deleteBlogger(bloggerId);
		return ResponseEntity.status(200).body("Blogger successfully deleted");
	}

	/**
	 * This method is used to get Blogger data
	 * 
	 * @param bloggerId of created blogger
	 * @return blogger object of registered blogger
	 */
	@GetMapping(path = "/blogger/{blogger_id}")
	public ResponseEntity<Object> getBlogger(@PathVariable(name = "blogger_id") int bloggerId) {
		log.info("Blogger Controller -- getBlogger()");
		Blogger blogger = bloggerService.getBlogger(bloggerId);
		return ResponseEntity.status(200).body(blogger);
	}

	/**
	 * This method is used to get list of all Bloggers data
	 * 
	 * @return List<Blogger> list of bloggers
	 */
	@GetMapping(path = "/bloggers")
	public ResponseEntity<Object> getAllBloggers() {
		log.info("Blogger Controller -- getAllBloggers()");
		List<Blogger> bloggers = bloggerService.getAllBloggers();
		return ResponseEntity.status(200).body(bloggers);
	}

	/**
	 * This method is used to join Community
	 * 
	 * @param bloggerId   of created blogger
	 * @param communityId of created community
	 * @return "Joined Community" when community joined successfully
	 */
	@PutMapping(path = "/blogger/{community_id}/{blogger_id}")
	public ResponseEntity<Object> joinCommunity(@PathVariable(name = "blogger_id") int bloggerId,
			@PathVariable(name = "community_id") int communityId) {
		log.info("Blogger Controller -- joinCommunity()");
		bloggerService.joinCommunity(communityId, bloggerId);
		return ResponseEntity.status(201).body("Joined Community");
	}

	/**
	 * This method is used to get set of Bloggers
	 * 
	 * @param communityId of created community
	 * @return set of bloggers
	 */
	@GetMapping(path = "/community/{community_id}/bloggers")
	public ResponseEntity<Object> getBloggerList(@PathVariable("community_id") int communityId) {
		log.info("Blogger Controller -- getBloggerList()");
		Set<Blogger> bloggers = bloggerService.getBloggerList(communityId);
		return ResponseEntity.status(200).body(bloggers);
	}

	/**
	 * This method is used to get Blogger data
	 * 
	 * @param bloggerName of created blogger
	 * @return blogger object of registered blogger
	 */
	@GetMapping(path = "/bloggers/{blogger_name}")
	public ResponseEntity<Object> getBloggerByName(@PathVariable(name = "blogger_name") String bloggerName) {
		log.info("Blogger Controller -- getBloggerByName()");
		Blogger blogger = bloggerService.getBloggerByName(bloggerName);
		return ResponseEntity.status(200).body(blogger);
	}

}