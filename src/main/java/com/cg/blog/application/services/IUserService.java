package com.cg.blog.application.services;

import com.cg.blog.application.entities.User;
import com.cg.blog.application.repositories.IUserRepository;

public interface IUserService {
	public User addNewUser(User user);

	public User signIn(User user);

	public User signOut(User user);
}
