package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.IdNotFoundException;

public interface IBloggerService {
	public Blogger addBlogger(Blogger blogger);

	public Blogger updateBlogger(Blogger blogger, int bloggerId) throws IdNotFoundException;

	public void deleteBlogger(int bloggerId) throws IdNotFoundException;

	public Blogger getBlogger(int bloggerId) throws IdNotFoundException;

	public List<Blogger> getAllBloggers();

	public Set<Blogger> getBloggerList(int communityId);
	// public List<Customer> viewCustomerList(int theatreid);

	public void joinCommunity(int communityId, int bloggerId);

}
