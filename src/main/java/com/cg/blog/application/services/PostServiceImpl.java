package com.cg.blog.application.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.blog.application.entities.Award;
import com.cg.blog.application.entities.AwardType;
import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.Vote;
import com.cg.blog.application.entities.VoteType;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.exceptions.CommunityNotFoundException;
import com.cg.blog.application.exceptions.InvalidVoteException;
import com.cg.blog.application.exceptions.PostNotFoundException;
import com.cg.blog.application.repositories.IAwardRepository;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.repositories.IUserRepository;
import com.cg.blog.application.repositories.IVoteRepository;

/**
 * PostServiceImpl specific implementation of {@link IPostService}
 * <p>
 * This Service class for PostController
 * </p>
 * 
 * @author Group4
 *
 */
@Service
public class PostServiceImpl implements IPostService {

	private static final String BLOGGER_NOT_FOUND = "Blogger Not Found";
	private static final String COMMUNITY_NOT_FOUND = "Community Not Found";
	private static final String POST_NOT_FOUND = "Post Not Found";

	private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

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

	/**
	 * This method is used to add post data into database
	 * 
	 * @param communityId of community
	 * @param bloggerId   of registered blogger
	 * @return post data
	 * @throws BloggerNotFoundException,CommunityNotFoundException
	 */
	@Override
	public Post addPost(int communityId, int bloggerId, Post post)
			throws CommunityNotFoundException, BloggerNotFoundException {
		log.info("Post Service -- addPost()");
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));

		post.setCreatedBy(blogger);
		community.getPosts().add(post);
		blogger.getPosts().add(post);
		post.setCommunity(community);

		bloggerRepository.save(blogger);
		communityRepository.save(community);
		return postRepository.save(post);
	}

	/**
	 * This method is used to delete post data from database
	 * 
	 * @param postId of post
	 * @throws PostNotFoundException
	 */
	@Transactional
	@Override
	public void deletePost(int postId) throws PostNotFoundException {
		log.info("Post Service -- deletePost()");
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));
		postRepository.delete(post);
		post.getCreatedBy().getPosts().remove(post);
	}

	/**
	 * This method is used to get post by search string from database
	 * 
	 * @param searchString
	 * @return List<Post> list of post
	 */
	@Override
	public List<Post> getPostBySearchString(String searchString) {
		log.info("Post Service -- getPostBySearchString()");
		return postRepository.findByTitleContainsIgnoreCase(searchString);
	}

	/**
	 * This method is used to add vote for specific post
	 * 
	 * @param voteType  Type of vote
	 * @param bloggerId of registered blogger
	 * @param postId    of post
	 * @throws BloggerNotFoundException,PostNotFoundException,InvalidVoteException
	 */
	@Override
	public void votePost(VoteType voteType, int bloggerId, int postId)
			throws BloggerNotFoundException, PostNotFoundException, InvalidVoteException {
		log.info("Post Service -- votePost()");
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

		Optional<Vote> recentVote = voteRepository.findTopByPostAndBloggerOrderByVoteIdDesc(post, blogger);
		if (recentVote.isPresent() && recentVote.get().getVoteType().equals(voteType)) {
			throw new InvalidVoteException("You have already " + voteType + "D this post");
		}
		if (VoteType.UPVOTE.equals(voteType)) {
			post.setVotes(post.getVotes() + 1);
			post.getCreatedBy().setKarma(post.getCreatedBy().getKarma() + 1);
		} else {
			post.setVotes(post.getVotes() - 1);
			post.getCreatedBy().setKarma(post.getCreatedBy().getKarma() - 1);
		}
		
		Vote vote = new Vote();
		vote.setBlogger(blogger);
		vote.setPost(post);
		vote.setVoteType(voteType);
		voteRepository.save(vote);
	}

	/**
	 * This method is used to get all posts from specific blogger
	 * 
	 * @param bloggerId of registered blogger
	 * @return List<Post> List of post
	 * @throws BloggerNotFoundException
	 */
	@Override
	public List<Post> getPostByBlogger(int bloggerId) throws BloggerNotFoundException {
		log.info("Post Service -- getPostByBlogger()");
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		return blogger.getPosts();
	}

	/**
	 * This method is used to update post
	 * 
	 * @param postId of post
	 * @param post   data
	 * @throws PostNotFoundException
	 */
	@Override
	public Post updatePost(int postId, Post post) throws PostNotFoundException {
		log.info("Post Service -- updatePost()");
		Post oldPost = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

		post.setPostId(postId);
		post.setCreatedBy(oldPost.getCreatedBy());
		post.setComments(oldPost.getComments());
		post.setCommunity(oldPost.getCommunity());
		post.setVotes(oldPost.getVotes());
		post.setAwardsReceived(oldPost.getAwardsReceived());

		oldPost.getCommunity().getPosts().add(post);
		oldPost.getCreatedBy().getPosts().add(post);

		bloggerRepository.save(oldPost.getCreatedBy());
		communityRepository.save(oldPost.getCommunity());
		postRepository.saveAndFlush(post);
		return post;
	}

	/**
	 * This method is used to give Award on specific post
	 * 
	 * @param awardType Type of Award
	 * @param bloggerId of registered blogger
	 * @param postId    of post
	 * 
	 * @throws BloggerNotFoundException,PostNotFoundException
	 */
	@Override
	public void giveAwardPost(AwardType awardType, int bloggerId, int postId)
			throws BloggerNotFoundException, PostNotFoundException {
		log.info("Post Service -- giveAwardPost()");
		Blogger blogger = bloggerRepository.findById(bloggerId)
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		Post post = postRepository.findById(postId).orElseThrow(() -> new PostNotFoundException(POST_NOT_FOUND));

		Award award = new Award();
		award.setAwardType(awardType);
		award.setBlogger(blogger);
		award.setPost(post);
		awardRepository.save(award);

		post.getAwardsReceived().add(award);
		postRepository.save(post);

		Blogger receivingBlogger = bloggerRepository.findById(post.getCreatedBy().getId())
				.orElseThrow(() -> new BloggerNotFoundException(BLOGGER_NOT_FOUND));
		receivingBlogger.getAwardsReceived().add(award);
		bloggerRepository.save(receivingBlogger);

	}

	/**
	 * This method is used to get posts from specific community
	 * 
	 * @param communityId of community
	 * @return list<post> list of post
	 * @throws CommunityNotFoundException
	 */
	@Override
	public List<Post> getPostsByCommunity(int communityId) {
		log.info("Post Service -- getPostsByCommunity()");
		Community community = communityRepository.findById(communityId)
				.orElseThrow(() -> new CommunityNotFoundException(COMMUNITY_NOT_FOUND));
		return community.getPosts();
	}

}
