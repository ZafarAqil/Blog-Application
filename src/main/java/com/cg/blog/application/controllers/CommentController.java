package com.cg.blog.application.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.services.CommentServiceImpl;

@RestController
public class CommentController {

	@Autowired
	CommentServiceImpl commentService;

	private final Logger log = LoggerFactory.getLogger(CommentController.class);

	@PostMapping(path = "/blogger/{blogger_id}/posts/{post_id}/comment")
	public ResponseEntity<Object> addComment(@PathVariable(name = "blogger_id") int bloggerId,
			@PathVariable(name = "post_id") int postId, @RequestBody Comment comment) { // NOSONAR
		log.info("Comment Controller -- addComment()");
		Comment addedComment = commentService.addComment(bloggerId, postId, comment);
		return ResponseEntity.status(201).body(addedComment);
	}

	@GetMapping(path = "/post/{post_id}/comments")
	public ResponseEntity<Object> listAllCommentsByPost(@PathVariable(name = "post_id") int postId) {
		log.info("Comment Controller -- listAllCommentsByPost()");
		List<Comment> comments = commentService.listAllCommentsByPost(postId);
		return ResponseEntity.status(200).body(comments);
	}

	@Transactional
	@DeleteMapping(path = "/blogger/{blogger_id}/posts/{post_id}/comment/{comment_id}")
	public ResponseEntity<Object> deleteCommentById(@PathVariable(name = "blogger_id") int bloggerId,
			@PathVariable(name = "post_id") int postId, @PathVariable(name = "comment_id") int commentId) {
		log.info("Comment Controller -- deleteCommentById()");
		commentService.deleteCommentById(commentId);
		return ResponseEntity.status(200).body("Comment Successfully Deleted!");
	}

	@PutMapping(path = "/comment/{comment_id}")
	public ResponseEntity<Object> updateComment(@PathVariable(name = "comment_id") int commentId,
			@RequestBody Comment comment) { // NOSONAR
		log.info("Comment Controller -- updateComment()");
		Comment updatedComment = commentService.updateComment(commentId, comment);
		return ResponseEntity.status(201).body(updatedComment);
	}

}
