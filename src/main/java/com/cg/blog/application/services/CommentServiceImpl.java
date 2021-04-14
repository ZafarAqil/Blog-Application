package com.cg.blog.application.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Comment;
import com.cg.blog.application.entities.Post;
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
		Optional<Blogger> bloggerOptional = bloggerRepository.findById(id);
		if (!bloggerOptional.isPresent()) {
//		throw new IdNotFoundException("id: "+ id, null);  
		}
		Blogger blogger = bloggerOptional.get();

		Optional<Post> postOptional = postRepository.findById(pid);
		if (!postOptional.isPresent()) {
//		throw new IdNotFoundException("id: "+ id, null);  
		}
		Post post = postOptional.get();

		// map the user to the post
		comment.setBlogger(blogger);
		comment.setPost(post);

		blogger.getComments().add(comment);
		post.getComments().add(comment);

		commentRepository.save(comment);

		bloggerRepository.save(blogger);
		// save post to the database
		postRepository.save(post);
		// getting the path of the post and append id of the post to the URI
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getPostId())
//				.toUri();
		return comment;
	}

	@Transactional
	@Override
	public void deleteCommentById(int id) {
		// TODO Auto-generated method stub
		Optional<Comment> commentOptional = commentRepository.findById(id);
		if (!commentOptional.isPresent()) {
//		throw new PostFoundException("id: "+ id, null);  
		}

		// save post to the database
		commentRepository.delete(commentOptional.get());
		commentOptional.get().getBlogger().getComments().remove(commentOptional.get());
		commentOptional.get().getPost().getComments().remove(commentOptional.get());
		// getting the path of the post and append id of the post to the URI

	}

	@Override
	public List<Comment> listAllCommentsByPost(int pid) {
		// TODO Auto-generated method stub
		Post post1 = postRepository.findById(pid).orElseThrow(null);
		return post1.getComments();
	}

	@Override
	public void upVote(boolean upVote) {
		// TODO Auto-generated method stub

	}

	// TODO: updateComment

}
