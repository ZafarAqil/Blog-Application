package com.cg.blog.application.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.AwardType;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.VoteType;
import com.cg.blog.application.services.PostServiceImpl;

@RestController
public class PostController {

	@Autowired
	PostServiceImpl postService;

	@RequestMapping(value = "post/{community_id}/{blogger_id}", method = RequestMethod.POST)
	public ResponseEntity<Object> addPost(@Valid @PathVariable(name = "community_id") int communityId,
			@PathVariable(name = "blogger_id") int bloggerId, @RequestBody Post post) {
		Post createdPost = postService.addPost(communityId, bloggerId, post);
		return ResponseEntity.status(201).body(createdPost);
	}

	@RequestMapping(value = "blogger/posts/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPostByBlogger(@PathVariable int id) {
		List<Post> postList = postService.getPostByBlogger(id);
		return ResponseEntity.status(200).body(postList);
	}

	@RequestMapping(value = "blogger/{id}/posts", method = RequestMethod.PUT)
	public ResponseEntity<Object> updatePost(@PathVariable int id, @RequestBody Post post) {
		Post updatedPost = postService.updatePost(id, post);
		return ResponseEntity.status(201).body(updatedPost);
	}

	@RequestMapping(value = "blogger/{id}/posts", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePost(@PathVariable int id) {
		postService.deletePost(id);
		return ResponseEntity.status(200).body("Post Deleted");
	}

	@RequestMapping(value = "post/vote/{blogger_id}/{post_id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> votePost(@RequestBody VoteType voteType,
			@PathVariable(name = "blogger_id") int bloggerId, @PathVariable(name = "post_id") int postId) {
		postService.votePost(voteType, bloggerId, postId);
		return ResponseEntity.status(200).body("Vote Submitted");
	}

	@RequestMapping(value = "post/award/{blogger_id}/{post_id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> giveAward(@RequestBody AwardType awardType,
			@PathVariable(name = "blogger_id") int bloggerId, @PathVariable(name = "post_id") int postId) {
		postService.giveAwardPost(awardType, bloggerId, postId);
		return ResponseEntity.status(200).body("Award Given");
	}

	@RequestMapping(value = "posts/{search_string}", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPostBySearchString(@PathVariable(name = "search_string") String searchString) {
		List<Post> matchedPosts = postService.getPostBySearchString(searchString);
		return ResponseEntity.status(200).body(matchedPosts);
	}

}
