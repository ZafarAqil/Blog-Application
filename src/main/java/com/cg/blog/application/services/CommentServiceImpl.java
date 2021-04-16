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
				.orElseThrow(() -> new BloggerNotFoundException("Blogger Not Found"));
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post Not Found"));

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
				.orElseThrow(() -> new CommentNotFoundException("Comment Not Found"));

		commentRepository.delete(comment);
		comment.getBlogger().getComments().remove(comment);
		comment.getPost().getComments().remove(comment);

	}

	@Override
	public List<Comment> listAllCommentsByPost(int postId) throws PostNotFoundException {
		Post returnedPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException("Post Not Found"));
		return returnedPost.getComments();
	}

	@Transactional
	@Override
	public Comment updateComment(int commentId, Comment comment) {
		Comment oldComment = commentRepository.findById(commentId).orElseThrow(() -> new CommentNotFoundException("Comment Not Found"));
		
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
