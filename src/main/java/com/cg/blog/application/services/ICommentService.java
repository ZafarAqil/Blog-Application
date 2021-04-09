package com.cg.blog.application.services;

import java.util.List;

import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Post;

public interface ICommentService {
	public Comment addComment(Comment comment);

	public void deleteComment(Comment comment);

	public List<Comment> listAllCommentsByPost(Post post);

	public void upVote(boolean upVote);

}
