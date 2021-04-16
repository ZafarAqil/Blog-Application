package com.cg.blog.application.controllers;

import java.util.List;

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

import com.cg.blog.application.entities.AwardType;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.VoteType;
import com.cg.blog.application.services.PostServiceImpl;

@RestController
public class PostController {

	@Autowired
	PostServiceImpl postService;

	@PostMapping(path = "/community/{community_id}/blogger/{blogger_id}/post")
	public ResponseEntity<Object> addPost(@Valid @PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "blogger_id") int bloggerId, @RequestBody Post post) { // NOSONAR
		Post createdPost = postService.addPost(communityId, bloggerId, post);
		return ResponseEntity.status(201).body(createdPost);
	}

	@GetMapping(path = "/community/blogger/{blogger_id}/posts")
	public ResponseEntity<List<Post>> getPostByBlogger(@PathVariable(name = "blogger_id") int bloggerId) {
		List<Post> postList = postService.getPostByBlogger(bloggerId);
		return ResponseEntity.status(200).body(postList);
	}

	@PutMapping(path = "/community/blogger/post/{post_id}")
	public ResponseEntity<Object> updatePost(@PathVariable(name = "post_id") int postId, @RequestBody Post post) { // NOSONAR
		Post updatedPost = postService.updatePost(postId, post);
		return ResponseEntity.status(201).body(updatedPost);
	}

	@DeleteMapping(path = "/community/blogger/post/{post_id}")
	public ResponseEntity<Object> deletePost(@PathVariable(name = "post_id") int postId) {
		postService.deletePost(postId);
		return ResponseEntity.status(200).body("Post Deleted");
	}

	@PutMapping(path = "/community/blogger/{blogger_id}/post/{post_id}/vote")
	public ResponseEntity<Object> votePost(@RequestBody VoteType voteType,
			@PathVariable(name = "blogger_id") int bloggerId, @PathVariable(name = "post_id") int postId) {
		postService.votePost(voteType, bloggerId, postId);
		return ResponseEntity.status(200).body("Vote Submitted");
	}

	@PutMapping(path = "/community/blogger/{blogger_id}/post/{post_id}/award")
	public ResponseEntity<Object> giveAward(@RequestBody AwardType awardType,
			@PathVariable(name = "blogger_id") int bloggerId, @PathVariable(name = "post_id") int postId) {
		postService.giveAwardPost(awardType, bloggerId, postId);
		return ResponseEntity.status(200).body("Award Given");
	}

	@GetMapping(path = "/community/posts/{search_string}")
	public ResponseEntity<List<Post>> getPostBySearchString(@PathVariable(name = "search_string") String searchString) {
		List<Post> matchedPosts = postService.getPostBySearchString(searchString);
		return ResponseEntity.status(200).body(matchedPosts);
	}

	@GetMapping(path = "/community/{community_id}/posts")
	public ResponseEntity<List<Post>> getPostsByCommunity(@PathVariable(name = "community_id") int communityId) {
		List<Post> postList = postService.getPostsByCommunity(communityId);
		return ResponseEntity.status(200).body(postList);
	}
}
