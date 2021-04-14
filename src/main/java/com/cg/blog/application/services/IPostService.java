package com.cg.blog.application.services;

import java.util.List;

import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.VoteType;

public interface IPostService {


	public Post updatePost(int id, Post post);

	public void deletePost(int id);

	public List<Post> getPostBySearchString(String searchStr);

	public List<Post> getPostByBlogger(int id);

	public void votePost(VoteType voteType, int bloggerId, int postId);


	Post addPost(int communityId, int bloggerId, Post post);

}
