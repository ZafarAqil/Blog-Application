package com.cg.blog.application.services;

import java.util.List;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Post;

public interface IPostService {
	public Post addPost(Post post);

	public Post updatePost(Post post);

	public Post deletePost(int id);

	public List<Post> getPostBySearchString(String searchStr);

	public List<Post> getPostByBlogger(Blogger blogger);

	public void upVote(boolean upVote);

}
