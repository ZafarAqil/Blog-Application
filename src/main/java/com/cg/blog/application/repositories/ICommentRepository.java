package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Comment;

public interface ICommentRepository extends JpaRepository<Comment,Integer> {

}
