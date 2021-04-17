package com.cg.blog.application.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.blog.application.entities.User;
import com.cg.blog.application.services.IUserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	IUserService userService;

	private final Logger log = LoggerFactory.getLogger(UserController.class);

	@PostMapping("/signUp")
	public ResponseEntity<User> addBlogger(@Valid @RequestBody User user) { // NOSONAR
		log.info("User Controller -- addBlogger()");
		User savedUser = userService.addNewUser(user);
		return ResponseEntity.status(201).body(savedUser);
	}

	@PostMapping("/signIn")
	public ResponseEntity<User> signIn(@RequestBody User user) { // NOSONAR
		log.info("User Controller -- signIn()");
		User savedUser = userService.signIn(user);
		return ResponseEntity.status(200).body(savedUser);
	}

}
