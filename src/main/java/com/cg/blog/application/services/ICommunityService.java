package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;

public interface ICommunityService {
	public Community addCommunity(Community community, int moderatorId);

	public Community updateCommunity(Community community, int communityId, int moderatorId) throws CommunityNotFoundException;

	public void deleteCommunity(int communityId, int moderatorId) throws CommunityNotFoundException;

	public List<Community> listAllCommunities(String searchString);

	public Set<Community> listAllCommunitiesByBlogger(int bloggerId) throws BloggerNotFoundException;
}
