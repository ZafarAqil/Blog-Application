package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.User;
/**
 * IUserRepository specific extention of {@link JpaRepository}
 * @author Group4
 *
 */
import java.util.Optional;

import org.springframework.stereotype.Repository;


@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}

