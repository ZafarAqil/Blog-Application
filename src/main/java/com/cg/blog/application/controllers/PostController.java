package com.cg.blog.application.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Post;
import com.cg.blog.application.services.PostServiceImpl;

@RestController
public class PostController {
	
	@Autowired
	PostServiceImpl postService;
	
	@RequestMapping(value = "blogger/{id}/posts",method = RequestMethod.POST)
	public ResponseEntity<Object> addPost(@PathVariable int id, @RequestBody Post post)  {
		URI location = postService.addPost(id,post);
		return ResponseEntity.created(location).build(); 
	}
	
//	@RequestMapping(value = "/posts",method = RequestMethod.POST)
//	public ResponseEntity<Object> addPost( @RequestBody Post post)  {
//		URI location = postService.addPost(post);
//		return ResponseEntity.created(location).build(); 
//	}
	
//	@RequestMapping(value = "blogger/{id}/posts",method = RequestMethod.GET)
//	public ResponseEntity<Object> getPostByBlogger(@PathVariable int id)  {
//		List<Post> = postService.getPostByBlogger(id);
//		return null;
//	}
}
