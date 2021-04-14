package com.cg.blog.application.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Comment;

public interface ICommentRepository extends JpaRepository<Comment,Integer> {
//	public Comment addComment(Comment comment);
//
//	public void deleteComment(Comment comment);
//
//	public List<Comment> listAllCommentsByPost(Post post);
//
//	public void upVote(boolean upVote);

}
