package com.cg.blog.application.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Post;
/**
 * IBloggerRepository specific extention of {@link JpaRepository}
 * @author Group4
 *
 */
public interface IBloggerRepository extends JpaRepository<Blogger, Integer> {

	List<Post> getPostsById(int id);
	Optional<Blogger> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
