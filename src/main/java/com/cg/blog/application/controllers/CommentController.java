package com.cg.blog.application.controllers;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.services.CommentServiceImpl;

@RestController
public class CommentController {

	@Autowired
	CommentServiceImpl commentService;

	@RequestMapping(value = "/blogger/{blogger_id}/posts/{post_id}/comment", method = RequestMethod.POST)
	public ResponseEntity<Object> addComment(@PathVariable(name = "blogger_id") int bloggerId, @PathVariable(name = "post_id") int postId,
			@RequestBody Comment comment) {
		Comment addedComment = commentService.addComment(bloggerId, postId, comment);
		return ResponseEntity.status(201).body(addedComment);
	}

	@RequestMapping(value = "/post/{post_id}/comments", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllCommentsByPost(@PathVariable(name = "post_id") int postId) {
		List<Comment> comments = commentService.listAllCommentsByPost(postId);
		return ResponseEntity.status(200).body(comments);
	}

	@Transactional
	@RequestMapping(value = "/comment/{comment_id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCommentById(@PathVariable(name = "comment_id") int commentId) {
		commentService.deleteCommentById(commentId);
		return ResponseEntity.status(200).body("Comment Successfully Deleted!");
	}

	@RequestMapping(value = "/comment/{comment_id}", method = RequestMethod.PUT)
	public ResponseEntity<Object> updateComment(@PathVariable(name = "comment_id") int commentId, @RequestBody Comment comment) {
		Comment updatedComment = commentService.updateComment(commentId, comment);
		return ResponseEntity.status(201).body(updatedComment);
	}

}
