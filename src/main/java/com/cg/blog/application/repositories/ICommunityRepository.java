package com.cg.blog.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Community;

public interface ICommunityRepository extends JpaRepository<Community, Integer>{

	public List<Community> findByCommunityDescriptionContains(String searchString);
//	public Community addCommunity(Community community);
//
//	public Community updateCommunity(Community community);
//
//	public Community deleteCommunity(Community community);
//
//	public List<Community> listAllCommunities(String searchString);
//
//	public List<Community> listAllCommunitiesByBlogger(Blogger blogger);
}
