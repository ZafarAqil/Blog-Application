package com.cg.blog.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.services.BloggerServiceImpl;

@RestController
public class BloggerController {
	
	@Autowired
	BloggerServiceImpl bloggerService;
	
	@RequestMapping(value = "/blogger",method = RequestMethod.POST)
	public ResponseEntity<Object> addBlogger(@RequestBody Blogger blogger)  {
		Blogger addedBlogger = bloggerService.addBlogger(blogger);
		return ResponseEntity.status(201).body(addedBlogger);
	}

	@RequestMapping(value = "/blogger/{blogger_id}",method = RequestMethod.PUT)
	public ResponseEntity<Object> updateBlogger(@RequestBody Blogger blogger, @PathVariable(name = "blogger_id") int bloggerId)  {
		Blogger updatedBlogger = bloggerService.updateBlogger(blogger, bloggerId);
		return ResponseEntity.status(201).body(updatedBlogger);
	}

	@RequestMapping(value = "/blogger/{blogger_id}",method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteBlogger(@PathVariable(name = "blogger_id") int bloggerId)  {
		bloggerService.deleteBlogger(bloggerId);
		return ResponseEntity.status(201).body("Blogger successfully deleted");
	}

	@RequestMapping(value = "/blogger/{blogger_id}",method = RequestMethod.GET)
	public ResponseEntity<Object> getBlogger(@PathVariable(name = "blogger_id") int bloggerId)  {
		Blogger blogger = bloggerService.getBlogger(bloggerId);
		return ResponseEntity.status(201).body(blogger);
	}
	
	@RequestMapping(value = "/blogger",method = RequestMethod.GET)
	public ResponseEntity<Object> getAllBloggers()  {
		List<Blogger> bloggers = bloggerService.getAllBloggers();
		return ResponseEntity.status(201).body(bloggers);
	}

}
