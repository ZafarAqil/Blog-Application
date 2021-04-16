package com.cg.blog.application.services;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommentNotFoundException;
import com.cg.blog.application.exceptions.PostNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommentRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.repositories.IUserRepository;

@Service
public class CommentServiceImpl implements ICommentService {

	private static final String BLOGGER_NOT_FOUND = "Blogger Not Found";
	private static final String POST_NOT_FOUND = "Post Not Found";
	private static final String COMMENT_NOT_FOUND = "Comment Not Found";

	@Autowired
	IPostRepository postRepository;

	@Autowired
	IUserRepository userRepository;

	@Autowired
	IBloggerRepository bloggerRepository;

	@Autowired
	ICommentRepository commentRepository;

	@Override
	public Comment addComment(int bloggerId, int postId, Comment comment)
			throws PostNotFoundException, BloggerNotFoundException {
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

		comment.setBlogger(blogger);
		comment.setPost(post);

		blogger.getComments().add(comment);
		post.getComments().add(comment);

		commentRepository.save(comment);
		bloggerRepository.save(blogger);
		postRepository.save(post);

		return comment;
	}

	@Transactional
	@Override
	public void deleteCommentById(int commentId) throws CommentNotFoundException {
		Comment comment = commentRepository.findById(commentId)
				.orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));

		commentRepository.delete(comment);
		comment.getBlogger().getComments().remove(comment);
		comment.getPost().getComments().remove(comment);

	}

	@Override
	public List<Comment> listAllCommentsByPost(int postId) throws PostNotFoundException {
		Post returnedPost = postRepository.findById(postId)
				.orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
		return returnedPost.getComments();
	}

	@Transactional
	@Override
	public Comment updateComment(int commentId, Comment comment) throws CommentNotFoundException {
		Comment oldComment = commentRepository.findById(commentId)
				.orElseThrow(() -> new CommentNotFoundException(COMMENT_NOT_FOUND));

		comment.setCommentId(commentId);
		comment.setBlogger(oldComment.getBlogger());
		comment.setPost(oldComment.getPost());

		oldComment.getBlogger().getComments().remove(oldComment);
		oldComment.getBlogger().getComments().add(comment);
		oldComment.getPost().getComments().remove(oldComment);
		oldComment.getPost().getComments().add(comment);

		bloggerRepository.save(oldComment.getBlogger());
		postRepository.save(oldComment.getPost());
		return commentRepository.save(comment);
	}

}
