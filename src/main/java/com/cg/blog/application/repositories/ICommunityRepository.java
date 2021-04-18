package com.cg.blog.application.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Community;
/**
 * ICommunityRepository specific extention of {@link JpaRepository}
 * @author Group4
 *
 */
public interface ICommunityRepository extends JpaRepository<Community, Integer>{

	public List<Community> findByTitleContainsIgnoreCase(String searchString);

}
