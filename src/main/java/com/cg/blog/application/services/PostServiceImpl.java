package com.cg.blog.application.services;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.repositories.IUserRepository;

@Service
public class PostServiceImpl implements IPostService{
	
	@Autowired
	IPostRepository postRepository;
	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IBloggerRepository bloggerRepository;
	@Override
	public Post addPost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public URI addPost(int id, Post post) {
		Optional<Blogger> bloggerOptional= bloggerRepository.findById(id);  
		if(!bloggerOptional.isPresent())  
		{  
//		throw new UserNotFoundException("id: "+ id);  
		}  
		Blogger blogger=bloggerOptional.get();     
		//map the user to the post  
		post.setCreatedBy(blogger);  
		//save post to the database  
		postRepository.save(post);  
		//getting the path of the post and append id of the post to the URI   
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getPostId()).toUri(); 
		return null;
	}

	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post deletePost(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostBySearchString(String searchStr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostByBlogger(Blogger blogger) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void upVote(boolean upVote) {
		// TODO Auto-generated method stub
		
	}

}
