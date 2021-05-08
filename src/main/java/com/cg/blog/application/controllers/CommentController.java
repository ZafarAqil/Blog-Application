package com.cg.blog.application.controllers;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

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

import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.services.CommentServiceImpl;

/**
 * 
 * <p>
 * The controller for Comment REST endpoints
 * </p>
 * <p>
 * This class handles the CRUD operations for Comment entity
 * </p>
 * 
 * @author Group4
 *
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CommentController {

	@Autowired
	CommentServiceImpl commentService;

	private final Logger log = LoggerFactory.getLogger(CommentController.class);

	/**
	 * This method is used to add Comment data
	 * 
	 * @param bloggerId of Registered Blogger
	 * @param postId    of Post
	 * @param comment   object of comment entity
	 * @return comment object when comment added
	 */
	@PostMapping(path = "/blogger/{blogger_id}/posts/{post_id}/comment")
	public ResponseEntity<Object> addComment(@PathVariable(name = "blogger_id") int bloggerId,
			@PathVariable(name = "post_id") int postId, @RequestBody Comment comment) { // NOSONAR
		log.info("Comment Controller -- addComment()");
		Comment addedComment = commentService.addComment(bloggerId, postId, comment);
		return ResponseEntity.status(201).body(addedComment);
	}

	/**
	 * This method is used to return all comments of specific post
	 * 
	 * @param postId of post
	 * @return list of all comments
	 */
	@GetMapping(path = "/post/{post_id}/comments")
	public ResponseEntity<Object> listAllCommentsByPost(@PathVariable(name = "post_id") int postId) {
		log.info("Comment Controller -- listAllCommentsByPost()");
		List<Comment> comments = commentService.listAllCommentsByPost(postId);
		return ResponseEntity.status(200).body(comments);
	}

	/**
	 * This method is used to delete comment by commentId
	 * 
	 * @param bloggerId of Registered blogger
	 * @param postId    of post
	 * @param commentId of comment
	 * @return "Comment Successfully Deleted!" when comment deleted successfully
	 */
	@Transactional
	@DeleteMapping(path = "/blogger/{blogger_id}/posts/{post_id}/comment/{comment_id}")
	public ResponseEntity<Object> deleteCommentById(@PathVariable(name = "blogger_id") int bloggerId,
			@PathVariable(name = "post_id") int postId, @PathVariable(name = "comment_id") int commentId) {
		log.info("Comment Controller -- deleteCommentById()");
		commentService.deleteCommentById(commentId);
		return ResponseEntity.status(200).body(Arrays.asList("Comment Successfully Deleted!"));
	}

	/**
	 * This method is used to update comment by commentId
	 * 
	 * @param commentId of comment
	 * @param comment   object of comment entity
	 * @return
	 */
	@PutMapping(path = "/comment/{comment_id}")
	public ResponseEntity<Object> updateComment(@PathVariable(name = "comment_id") int commentId,
			@RequestBody Comment comment) { // NOSONAR
		log.info("Comment Controller -- updateComment()");
		Comment updatedComment = commentService.updateComment(commentId, comment);
		return ResponseEntity.status(201).body(updatedComment);
	}

}