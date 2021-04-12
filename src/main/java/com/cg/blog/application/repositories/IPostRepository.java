package com.cg.blog.application.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Post;

public interface IPostRepository extends JpaRepository<Post,Integer> {

//	public Post addPost(Post post);
//
//	public Post updatePost(Post post);
//
//	public Post deletePost(int id);
//
//	public List<Post> getPostBySearchString(String searchStr);
//
//	public List<Post> getPostByBlogger(Blogger blogger);
//
//	public void upVote(boolean upVote);

}
