package com.cg.blog.application.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.Vote;

@Repository
public interface IVoteRepository extends JpaRepository<Vote, Long>{

	public Optional<Vote> findTopByPostAndBloggerOrderByVoteIdDesc(Post post, Blogger blogger);

}
