package com.cg.blog.application.services;

import java.util.List;

import com.cg.blog.application.entities.AwardType;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.VoteType;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.exceptions.InvalidVoteException;
import com.cg.blog.application.exceptions.PostNotFoundException;

public interface IPostService {

	public Post addPost(int communityId, int bloggerId, Post post)
			throws CommunityNotFoundException, BloggerNotFoundException;

	public Post updatePost(int postId, Post post) throws PostNotFoundException;

	public void deletePost(int postId) throws PostNotFoundException;

	public List<Post> getPostBySearchString(String searchString);

	public List<Post> getPostByBlogger(int bloggerId) throws BloggerNotFoundException;

	public void votePost(VoteType voteType, int bloggerId, int postId)
			throws BloggerNotFoundException, PostNotFoundException, InvalidVoteException;

	public void giveAwardPost(AwardType awardType, int bloggerId, int postId)
			throws BloggerNotFoundException, PostNotFoundException;

	public List<Post> getPostsByCommunity(int communityId);
	
	public Post getPostById(int postId);

}
