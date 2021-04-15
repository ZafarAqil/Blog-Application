package com.cg.blog.application.services;

import com.cg.blog.application.entities.User;

public interface IUserService {
	
	public User addNewUser(User user);

	public User signIn(User user);

	public User signOut(User user);
}
