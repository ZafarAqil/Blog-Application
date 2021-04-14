package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import com.cg.blog.application.entities.Community;

public interface ICommunityService {
	public Community addCommunity(Community community);

	public Community updateCommunity(Community communit, int communityId);

	public void deleteCommunity(int communityId);

	public List<Community> listAllCommunities(String searchString);

	public Set<Community> listAllCommunitiesByBlogger(int bloggerId);
}
