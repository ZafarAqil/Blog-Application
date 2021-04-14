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

	@RequestMapping(value = "blogger/{id}/posts/{pid}/comment", method = RequestMethod.POST)
	public ResponseEntity<Object> addComment(@PathVariable int id, @PathVariable int pid, @RequestBody Comment comment) {
		Comment comment1 = commentService.addComment(id, pid, comment);
		return ResponseEntity.status(201).body(null);
	}

	@RequestMapping(value = "blogger/posts/{pid}/comment", method = RequestMethod.GET)
	public ResponseEntity<Object> listAllCommentsByPost(@PathVariable int pid) {
		List<Comment> comment1 = commentService.listAllCommentsByPost(pid);
		return ResponseEntity.status(201).body(comment1);
	}

	@Transactional
	@RequestMapping(value = "blogger/comment/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteCommentById(@PathVariable int id) {
		commentService.deleteCommentById(id);
		return ResponseEntity.status(201).body("Successfully Delete!");
	}
	
	
}
