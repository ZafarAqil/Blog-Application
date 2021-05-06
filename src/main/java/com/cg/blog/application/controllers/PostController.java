package com.cg.blog.application.controllers;

import java.util.List;

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

import com.cg.blog.application.entities.AwardType;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.VoteType;
import com.cg.blog.application.services.PostServiceImpl;

/**
 * 
 * <p>
 * The controller for Post REST endpoints
 * </p>
 * <p>
 * This class handles the CRUD operations for Post entity
 * </p>
 * 
 * @author Group4
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {

	@Autowired
	PostServiceImpl postService;

	private final Logger log = LoggerFactory.getLogger(PostController.class);

	/**
	 * This method is used to add post
	 * 
	 * @param communityId of community
	 * @param bloggerId   of Blogger
	 * @param post        object of post
	 * @return
	 */
	@PostMapping(path = "/community/{community_id}/blogger/{blogger_id}/post")
	public ResponseEntity<Object> addPost(@Valid @PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "blogger_id") int bloggerId, @RequestBody Post post) { // NOSONAR
		log.info("Post Controller -- addPost()");
		Post createdPost = postService.addPost(communityId, bloggerId, post);
		return ResponseEntity.status(201).body(createdPost);
	}

	/**
	 * This method is to get List of Post by Blogger
	 * 
	 * @param bloggerId of Blogger
	 * @return List of Post by Blogger
	 */
	@GetMapping(path = "/community/blogger/{blogger_id}/posts")
	public ResponseEntity<List<Post>> getPostByBlogger(@PathVariable(name = "blogger_id") int bloggerId) {
		log.info("Post Controller -- getPostByBlogger()");
		List<Post> postList = postService.getPostByBlogger(bloggerId);
		return ResponseEntity.status(200).body(postList);
	}

	/**
	 * This method is used to update the post
	 * 
	 * @param postId of Post
	 * @param post   object of Post entity
	 * @return updatedPost when post gets updated
	 */
	@PutMapping(path = "/community/blogger/post/{post_id}")
	public ResponseEntity<Object> updatePost(@PathVariable(name = "post_id") int postId, @RequestBody Post post) { // NOSONAR
		log.info("Post Controller -- updatePost()");
		Post updatedPost = postService.updatePost(postId, post);
		return ResponseEntity.status(201).body(updatedPost);
	}

	/**
	 * This method is used to delete the post
	 * 
	 * @param postId of Post
	 * @return "Post Deleted" when post deleted successfully
	 */
	@DeleteMapping(path = "/community/blogger/post/{post_id}")
	public ResponseEntity<Object> deletePost(@PathVariable(name = "post_id") int postId) {
		log.info("Post Controller -- deletePost()");
		postService.deletePost(postId);
		return ResponseEntity.status(200).body("Post Deleted");
	}

	/**
	 * This method is used to vote for a post
	 * 
	 * @param voteType  object of VoteType Entity
	 * @param bloggerId of Blogger
	 * @param postId    of Post
	 * @return "Vote Submitted" when vote gets submitted successfully
	 */
	@PutMapping(path = "/community/blogger/{blogger_id}/post/{post_id}/vote")
	public ResponseEntity<Object> votePost(@RequestBody VoteType voteType,
			@PathVariable(name = "blogger_id") int bloggerId, @PathVariable(name = "post_id") int postId) {
		log.info("Post Controller -- votePost()");
		postService.votePost(voteType, bloggerId, postId);
		return ResponseEntity.status(200).body("Vote Submitted");
	}

	/**
	 * This method is used to give award to a post
	 * 
	 * @param awardType object of AwardType entity
	 * @param bloggerId of Blogger
	 * @param postId    of Post
	 * @return "Award Given" when award given successfully
	 */
	@PutMapping(path = "/community/blogger/{blogger_id}/post/{post_id}/award")
	public ResponseEntity<Object> giveAward(@RequestBody AwardType awardType,
			@PathVariable(name = "blogger_id") int bloggerId, @PathVariable(name = "post_id") int postId) {
		log.info("Post Controller -- giveAward()");
		postService.giveAwardPost(awardType, bloggerId, postId);
		return ResponseEntity.status(200).body("Award Given");
	}

	/**
	 * This method is used to get post by searching String
	 * 
	 * @param searchString object of String
	 * @return List of Posts matched with string
	 */
	@GetMapping(path = "/community/posts/{search_string}")
	public ResponseEntity<List<Post>> getPostBySearchString(@PathVariable(name = "search_string") String searchString) {
		log.info("Post Controller -- getPostBySearchString()");
		List<Post> matchedPosts = postService.getPostBySearchString(searchString);
		return ResponseEntity.status(200).body(matchedPosts);
	}

	/**
	 * This method is used ton search post by community
	 * 
	 * @param communityId of community
	 * @return List of Post by community
	 */
	@GetMapping(path = "/community/{community_id}/posts")
	public ResponseEntity<List<Post>> getPostsByCommunity(@PathVariable(name = "community_id") int communityId) {
		log.info("Post Controller -- getPostsByCommunity()");
		List<Post> postList = postService.getPostsByCommunity(communityId);
		return ResponseEntity.status(200).body(postList);
	}
}
