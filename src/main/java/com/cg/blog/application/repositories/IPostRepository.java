package com.cg.blog.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Post;

public interface IPostRepository extends JpaRepository<Post,Integer> {

	public List<Post> findByTitleContainsIgnoreCase(String searchStr);

}
