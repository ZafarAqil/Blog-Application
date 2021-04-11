package com.cg.blog.application.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.exceptions.IdNotFoundException;

public interface IBloggerRepository extends JpaRepository<Blogger, Integer> {
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
