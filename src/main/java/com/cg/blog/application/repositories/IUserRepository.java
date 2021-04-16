package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	public User findByName(String name);

}
