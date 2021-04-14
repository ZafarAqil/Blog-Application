package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;

public interface IBloggerService {
	public Blogger addBlogger(Blogger blogger);

	public Blogger updateBlogger(Blogger blogger, int bloggerId) throws BloggerNotFoundException;

	public void deleteBlogger(int bloggerId) throws BloggerNotFoundException;

	public Blogger getBlogger(int bloggerId) throws BloggerNotFoundException;

	public List<Blogger> getAllBloggers();

	public Set<Blogger> getBloggerList(int communityId) throws CommunityNotFoundException;

	public void joinCommunity(int communityId, int bloggerId)
			throws CommunityNotFoundException, BloggerNotFoundException;

}
