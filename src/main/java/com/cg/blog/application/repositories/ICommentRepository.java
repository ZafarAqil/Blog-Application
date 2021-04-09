package com.cg.blog.application.repositories;

import java.util.List;

import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Post;

public interface ICommentRepository {
	public Comment addComment(Comment comment);

	public void deleteComment(Comment comment);

	public List<Comment> listAllCommentsByPost(Post post);

	public void upVote(boolean upVote);

}
