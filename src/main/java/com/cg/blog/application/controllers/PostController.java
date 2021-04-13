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
	
	@RequestMapping(value = "post/{community_id}/{blogger_id}",method = RequestMethod.POST)
	public ResponseEntity<Object> addPost(@PathVariable(name = "community_id") int communityId, @PathVariable(name = "blogger_id") int bloggerId,  @RequestBody Post post)  {
		Post post1 = postService.addPost(communityId,bloggerId,post);
		return ResponseEntity.status(201).body(post1);
	}
	
//	@RequestMapping(value = "/posts",method = RequestMethod.POST)
//	public ResponseEntity<Object> addPost( @RequestBody Post post)  {
//		URI location = postService.addPost(post);
//		return ResponseEntity.created(location).build(); 
//	}
	
//	@RequestMapping(value = "blogger/posts/{id}",method = RequestMethod.GET)
//	public List<Post> getPostByBlogger(@PathVariable int id)  {
//		List<Post> list= postService.getPostByBlogger(id);
//		return list;
//	}
	
	@RequestMapping(value = "blogger/posts/{id}",method = RequestMethod.GET)
	public ResponseEntity<List<Post>> getPostByBlogger(@PathVariable int id)  {
		List<Post> list= postService.getPostByBlogger(id);
		return  ResponseEntity.status(200).body(list);
	}
	
	@RequestMapping(value = "blogger/{id}/posts",method = RequestMethod.PUT)
	public ResponseEntity<Object> UpdatePost(@PathVariable int id, @RequestBody Post post)  {
		URI location = postService.UpdatePost(id,post);
		return ResponseEntity.created(location).build(); 
	}
	
	@RequestMapping(value = "blogger/{id}/posts",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deletePost(@PathVariable int id)  {
		 postService.deletePost(id);
		return (ResponseEntity<Object>) ResponseEntity.status(200); 
	}
}
