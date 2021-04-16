package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.AuthenticationFailedException;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;

public interface ICommunityService {
	public Community addCommunity(Community community, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException;

	public Community updateCommunity(Community community, int communityId, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException, AuthenticationFailedException;

	public void deleteCommunity(int communityId, int moderatorId)
			throws CommunityNotFoundException, BloggerNotFoundException, AuthenticationFailedException;

	public List<Community> getAllCommunitiesBySearchString(String searchString);

	public Set<Community> getAllCommunitiesByBlogger(int bloggerId) throws BloggerNotFoundException;
	
	public List<String> getAllCommunities();

	public Community getCommunity(int communityId);
	
	

}
