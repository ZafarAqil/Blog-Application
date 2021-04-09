package com.cg.blog.application.repositories;

import java.util.List;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;

public interface ICommunityRepository {
	public Community addCommunity(Community community);

	public Community updateCommunity(Community community);

	public Community deleteCommunity(Community community);

	public List<Community> listAllCommunities(String searchString);

	public List<Community> listAllCommunitiesByBlogger(Blogger blogger);
}
