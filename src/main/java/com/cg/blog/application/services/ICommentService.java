package com.cg.blog.application.services;

import java.util.List;

import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Post;

public interface ICommentService {
	public Comment addComment(int id, int pid, Comment comment);

	public void deleteCommentById(int id);

	public List<Comment> listAllCommentsByPost(int pid);

	public void upVote(boolean upVote);

}
