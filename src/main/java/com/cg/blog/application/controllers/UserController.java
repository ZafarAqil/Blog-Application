package com.cg.blog.application.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

	@PostMapping("/signUp")
	public ResponseEntity<User> addBlogger(@Valid @RequestBody User user) {
		User savedUser = userService.addNewUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@PostMapping("/signIn")
	public ResponseEntity<User> signIn(@RequestBody User user) {
		User savedUser = userService.signIn(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

}
