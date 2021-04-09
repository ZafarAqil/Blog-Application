package com.cg.blog.application.repositories;

import com.cg.blog.application.entities.User;

public interface IUserRepository {
	public User addNewUser(User user);

	public User signIn(User user);

	public User signOut(User user);
}
