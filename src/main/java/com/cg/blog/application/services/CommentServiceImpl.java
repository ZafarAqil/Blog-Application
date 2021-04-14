package com.cg.blog.application.services;

import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.exceptions.IdNotFoundException;
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
	public Comment addComment(int id, int pid, Comment comment) {
		Blogger blogger = bloggerRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Blogger Not Found"));
		Post post= postRepository.findById(pid).orElseThrow(() -> new IdNotFoundException("Post Not Found"));

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
	public void deleteCommentById(int id) {
		Comment comment = commentRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Comment Not Found"));

		commentRepository.delete(comment);
		comment.getBlogger().getComments().remove(comment);
		comment.getPost().getComments().remove(comment);

	}

	@Override
	public List<Comment> listAllCommentsByPost(int pid) {
		Post post1 = postRepository.findById(pid).orElseThrow(null);
		return post1.getComments();
	}

	// TODO: updateComment

}
