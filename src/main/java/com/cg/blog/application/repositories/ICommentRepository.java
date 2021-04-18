package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Comment;
/**
 * ICommentRepository specific extention of {@link JpaRepository}
 * @author Group4
 *
 */
public interface ICommentRepository extends JpaRepository<Comment,Integer> {

}
