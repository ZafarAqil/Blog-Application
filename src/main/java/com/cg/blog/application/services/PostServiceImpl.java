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
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.exceptions.IdNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.repositories.IUserRepository;

@Service
public class PostServiceImpl implements IPostService{
	
	@Autowired
	IPostRepository postRepository;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	ICommunityRepository communityRepository;
	@Autowired
	IBloggerRepository bloggerRepository;
//	@Override
//	public Post addPost(Post post) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
	
	@Override
	public Post addPost(int communityId, int bloggerId, Post post)  {
		
		Community community = communityRepository.findById(communityId).orElseThrow(() -> new IdNotFoundException("Community Not Found"));
		Blogger blogger = bloggerRepository.findById(bloggerId).orElseThrow(() -> new IdNotFoundException("Blogger Not Found"));
		
		//map the user to the post  
		post.setCreatedBy(blogger);  
		community.getPosts().add(post);
		blogger.getPosts().add(post);
		post.setCommunity(community);
		
		bloggerRepository.save(blogger);
		//save post to the database  
		postRepository.save(post);  
		communityRepository.save(community);
		return post;
	}

	@Override
	public Post updatePost(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Transactional
//	@Override
//	public void deletePost(int id) {
//		Optional<Post> postOptional= postRepository.findById(id);  
//		if(!postOptional.isPresent())  
//		{  
////		throw new PostFoundException("id: "+ id, null);  
//		}  
//	    
//		//save post to the database  
//		postRepository.delete(postOptional.get()) ;
//		//getting the path of the post and append id of the post to the URI   
//	
//	}
	
    @Transactional
    @Override
    public void deletePost(int id) {
        Optional<Post> postOptional= postRepository.findById(id);
        if(!postOptional.isPresent())
        {
//        throw new PostFoundException("id: "+ id, null);
        }
       
        //save post to the database
//        postRepository.delete(postOptional.get()) ;
       
        postOptional.get().getCreatedBy().getPosts().remove(postOptional.get());
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
                .orElseThrow(() -> new IdNotFoundException("Id Not Found"));
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
