package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Award;
/**
 * IAwardRepository specific extention of {@link JpaRepository}
 * @author Group4
 *
 */
public interface IAwardRepository extends JpaRepository<Award, Integer>{

}
