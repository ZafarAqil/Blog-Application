package com.cg.blog.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.IdNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;

@Service
public class BloggerServiceImpl implements IBloggerService{

	@Autowired
	IBloggerRepository bloggerRepository;

	@Override
	public Blogger addBlogger(Blogger blogger) {		
		return bloggerRepository.save(blogger);
	}

	@Override
	public Blogger updateBlogger(Blogger blogger, int bloggerId) throws IdNotFoundException {
		bloggerRepository.findById(bloggerId).orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		blogger.setUserId(bloggerId);
		Blogger updatedBlogger = bloggerRepository.save(blogger);
		return updatedBlogger;
	}

	@Override
	public void deleteBlogger(int bloggerId) throws IdNotFoundException {
		bloggerRepository.findById(bloggerId).orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		bloggerRepository.deleteById(bloggerId);
	}

	@Override
	public Blogger getBlogger(int bloggerId) throws IdNotFoundException {
		return bloggerRepository.findById(bloggerId).
				orElseThrow(() -> new IdNotFoundException("Id Not Found"));
	}

	@Override
	public List<Blogger> getAllBloggers() {
		return  bloggerRepository.findAll();
	}

	@Override
	public List<Blogger> viewBloggerList(Community community) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
