package com.cg.blog.application.services;

import java.util.List;

import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommentNotFoundException;
import com.cg.blog.application.exceptions.PostNotFoundException;

public interface ICommentService {

	public Comment addComment(int bloggerId, int postId, Comment comment)
			throws PostNotFoundException, BloggerNotFoundException;

	public void deleteCommentById(int commentId) throws CommentNotFoundException;

	public List<Comment> listAllCommentsByPost(int postId) throws PostNotFoundException;

}
