package com.cg.blog.application.services;

import java.net.URI;
import java.util.List;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Post;

public interface IPostService {


	public Post updatePost(Post post);

	public Post deletePost(int id);

	public List<Post> getPostBySearchString(String searchStr);

	public List<Post> getPostByBlogger(int id);

	public void upVote(boolean upVote);


	URI addPost(int id, Post post);

}
