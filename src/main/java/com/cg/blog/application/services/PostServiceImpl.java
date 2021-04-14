package com.cg.blog.application.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Award;
import com.cg.blog.application.entities.AwardType;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.Vote;
import com.cg.blog.application.entities.VoteType;
import com.cg.blog.application.exceptions.IdNotFoundException;
import com.cg.blog.application.repositories.IAwardRepository;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.repositories.IUserRepository;
import com.cg.blog.application.repositories.IVoteRepository;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	IPostRepository postRepository;
	@Autowired
	IUserRepository userRepository;
	@Autowired
	ICommunityRepository communityRepository;
	@Autowired
	IBloggerRepository bloggerRepository;
	@Autowired
	IVoteRepository voteRepository;
	@Autowired
	IAwardRepository awardRepository;
	
//	@Override
//	public Post addPost(Post post) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Post addPost(int communityId, int bloggerId, Post post) {

		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new IdNotFoundException("Community Not Found"));
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new IdNotFoundException("Blogger Not Found"));

		// map the user to the post
		post.setCreatedBy(blogger);
		community.getPosts().add(post);
		blogger.getPosts().add(post);
		post.setCommunity(community);

		bloggerRepository.save(blogger);
		// save post to the database
		postRepository.save(post);
		communityRepository.save(community);
		return post;
	}

	@Override
	public Post updatePost(int id, Post post) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Transactional
//	@Override
//	public void deletePost(int id) {
//		Optional<Post> postOptional= postRepository.findById(id);  
//		if(!postOptional.isPresent())  
//		{  
////		throw new PostFoundException("id: "+ id, null);  
//		}  
//	    
//		//save post to the database  
//		postRepository.delete(postOptional.get()) ;
//		//getting the path of the post and append id of the post to the URI   
//	
//	}

	@Transactional
	@Override
	public void deletePost(int id) {
		Optional<Post> postOptional = postRepository.findById(id);
		if (!postOptional.isPresent()) {
//        throw new PostFoundException("id: "+ id, null);
		}

		// save post to the database
//        postRepository.delete(postOptional.get()) ;

		postOptional.get().getCreatedBy().getPosts().remove(postOptional.get());
		// getting the path of the post and append id of the post to the URI

	}

	@Override
	public List<Post> getPostBySearchString(String searchStr) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public List<Post> getPostByBlogger(Blogger blogger) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public void votePost(VoteType voteType, int bloggerId, int postId) {

		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new IdNotFoundException("Blogger Not Found"));
		Post post = postRepository.findById(postId).orElseThrow(() -> new IdNotFoundException("Post not found"));

		Optional<Vote> recentVote = voteRepository.findTopByPostAndBloggerOrderByVoteIdDesc(post, blogger);
		if (recentVote.isPresent() && recentVote.get().getVoteType().equals(voteType)) {
			throw new IdNotFoundException("You have already " + voteType + "'d for this post");
		}
		if(VoteType.UPVOTE.equals(voteType)) {
			post.setVotes(post.getVotes() + 1);
		} else {
			post.setVotes(post.getVotes() - 1);
		}
		Vote vote = new Vote();
		vote.setBlogger(blogger);
		vote.setPost(post);
		vote.setVoteType(voteType);
		voteRepository.save(vote);
	}

	@Override
	public List<Post> getPostByBlogger(int id) {
		Blogger blogger = bloggerRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Id Not Found"));
		return blogger.getPosts();
	}

	public Post UpdatePost(int id, Post post) {
		postRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Post Not Found"));
		post.setPostId(id);
		// save post to the database
		postRepository.saveAndFlush(post);
		// getting the path of the post and append id of the post to the URI
		return post;
	}

	public void giveAwardPost(AwardType awardType, int bloggerId, int postId) {
		Blogger blogger = bloggerRepository.findById(bloggerId).orElseThrow(() -> new IdNotFoundException("Blogger Not Found"));
		Post post = postRepository.findById(postId).orElseThrow(() -> new IdNotFoundException("Post not found"));

		Award award = new Award();
		award.setAwardType(awardType);
		award.setBlogger(blogger);
		award.setPost(post);
		awardRepository.save(award);
		
		Blogger receivingblogger = bloggerRepository.findById(post.getCreatedBy().getId()).orElseThrow(() -> new IdNotFoundException("Blogger Not Found"));
		receivingblogger.getAwardsReceived().add(award);
		bloggerRepository.save(receivingblogger);
		
		
	}

}
