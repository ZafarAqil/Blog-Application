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
	public Comment addComment(int id, int pid, Comment comment) throws PostNotFoundException, BloggerNotFoundException {
		Blogger blogger = bloggerRepository.findById(id)
				.orElseThrow(() -> new BloggerNotFoundException("Blogger Not Found"));
		Post post = postRepository.findById(pid).orElseThrow(() -> new PostNotFoundException("Post Not Found"));

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
	public void deleteCommentById(int id) throws CommentNotFoundException {
		Comment comment = commentRepository.findById(id)
				.orElseThrow(() -> new CommentNotFoundException("Comment Not Found"));

		commentRepository.delete(comment);
		comment.getBlogger().getComments().remove(comment);
		comment.getPost().getComments().remove(comment);

	}

	@Override
	public List<Comment> listAllCommentsByPost(int pid) throws PostNotFoundException {
		Post post1 = postRepository.findById(pid).orElseThrow(() -> new PostNotFoundException("Post Not Found"));
		return post1.getComments();
	}

	// TODO: updateComment

}
