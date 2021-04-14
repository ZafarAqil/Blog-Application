package com.cg.blog.application.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Post;

public interface IBloggerRepository extends JpaRepository<Blogger, Integer> {

	List<Post> getPostsById(int id);

//	Optional<Blogger> findByBlogger(Blogger blogger);

	

//	Optional<Blogger> findOne(Blogger createdBy);
//	public Blogger addBlogger(Blogger blogger);
//
//	public Blogger updateBlogger(Blogger blogger) throws IdNotFoundException;
//
//	public Blogger deleteBlogger(Blogger blogger) throws IdNotFoundException;
//
//	public Blogger viewBlogger(int bloggerId) throws IdNotFoundException;
//
//	public List<Blogger> viewAllBloggers();
//
//	public List<Blogger> viewBloggerList(Community community);

}
