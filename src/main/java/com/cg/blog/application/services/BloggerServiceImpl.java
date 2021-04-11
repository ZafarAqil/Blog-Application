package com.cg.blog.application.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.IdNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;

public class BloggerServiceImpl implements IBloggerService{

	@Autowired
	IBloggerRepository bloggerRepository;

	@Override
	public Blogger addBlogger(Blogger blogger) {
		// TODO Auto-generated method stub
		return bloggerRepository.save(blogger);
	}

	@Override
	public Blogger updateBlogger(Blogger blogger) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blogger deleteBlogger(Blogger blogger) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Blogger viewBlogger(int bloggerId) throws IdNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blogger> viewAllBloggers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Blogger> viewBloggerList(Community community) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
