package com.cg.blog.application.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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
//	@Override
//	public Post addPost(Post post) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	@Override
	public URI addPost(int id, Post post)  {
		Optional<Blogger> bloggerOptional= bloggerRepository.findById(id);  
		if(!bloggerOptional.isPresent())  
		{  
//		throw new IdNotFoundException("id: "+ id, null);  
		}  
		Blogger blogger=bloggerOptional.get();     
		//map the user to the post  
		post.setCreatedBy(blogger);  
		
		blogger.getPosts().add(post);
		
		bloggerRepository.save(blogger);
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

	@Transactional
	@Override
	public void deletePost(int id) {
		Optional<Post> postOptional= postRepository.findById(id);  
		if(!postOptional.isPresent())  
		{  
//		throw new PostFoundException("id: "+ id, null);  
		}  
	    
		//save post to the database  
		postRepository.delete(postOptional.get()) ;
		//getting the path of the post and append id of the post to the URI   
	
	}

	@Override
	public List<Post> getPostBySearchString(String searchStr) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Post> getPostByBlogger(Blogger blogger) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void upVote(boolean upVote) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Post> getPostByBlogger(int id) {
		List<Post> list = new ArrayList<>();
        Blogger blogger = bloggerRepository.findById(id)
                .orElseThrow(null);
        return blogger.getPosts();
	}

	public URI UpdatePost(int id, Post post) {
		Optional<Post> postOptional= postRepository.findById(id);  
		if(!postOptional.isPresent())  
		{  
//		throw new PostFoundException("id: "+ id, null);  
		}  
	    post.setPostId(id);
		//save post to the database  
		postRepository.saveAndFlush(post) ;
		//getting the path of the post and append id of the post to the URI   
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getPostId()).toUri(); 
		return null;
	}

}
