package com.cg.blog.application.services;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.IdNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;

@Service
public class BloggerServiceImpl implements IBloggerService{

	@Autowired
	IBloggerRepository bloggerRepository;
	@Autowired
	ICommunityRepository communityRepository;
	@Override
	public Blogger addBlogger(Blogger blogger) {		
		return bloggerRepository.save(blogger);
	}

	@Override
	public Blogger updateBlogger(Blogger blogger, int bloggerId) throws IdNotFoundException {
		bloggerRepository.findById(bloggerId).orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		blogger.setId(bloggerId);
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
	public Set<Blogger> getBloggerList(int communityId) {
		Community community = communityRepository.findById(communityId).orElseThrow(() -> new IdNotFoundException("Community Not Found"));
		return community.getBloggers();
	}

	@Override
	public void joinCommunity(int communityId, int bloggerId) {
		Community community = communityRepository.findById(communityId).orElseThrow(() -> new IdNotFoundException("Community Not Found"));
		Blogger blogger = bloggerRepository.findById(bloggerId).orElseThrow(() -> new IdNotFoundException("Blogger Not Found"));
		community.getBloggers().add(blogger);
		blogger.getCommunities().add(community);
		community.setTotalMembers(community.getTotalMembers()+1);
		bloggerRepository.save(blogger);
		communityRepository.save(community);
	}
	

}
