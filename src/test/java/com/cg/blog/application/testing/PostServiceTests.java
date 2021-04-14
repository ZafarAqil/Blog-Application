package com.cg.blog.application.testing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.entities.Community;
import com.cg.blog.application.entities.Post;
import com.cg.blog.application.entities.PostType;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.repositories.ICommunityRepository;
import com.cg.blog.application.repositories.IPostRepository;
import com.cg.blog.application.services.PostServiceImpl;

@SpringBootTest
public class PostServiceTests {

	@Autowired
	PostServiceImpl postService;

	@MockBean
	IPostRepository postRepo;

	@MockBean
	ICommunityRepository communityRepository;

	@MockBean
	IBloggerRepository bloggerRepository;

	@Test
	public void testAddPost() {
		Community community = new Community();
		community.setCommunityId(1);
//		communityRepository.save(community);
		Blogger blogger = new Blogger();
		blogger.setId(1);
		bloggerRepository.save(blogger);
		Post post = new Post();
		post.setPostId(1);
		post.setTitle("SampleTitle");
		post.setDescription("SomethingUseless");
		post.setContent(PostType.TEXT);
		post.setNotSafeForWork(false);
		post.setSpoiler(false);
		post.setOriginalContent(false);
		post.setFlair("OC");
		post.setCreatedBy(blogger);
		post.setCommunity(community);
		Mockito.when(postRepo.save(post)).thenReturn(post);
		assertThat(postService.addPost(1, 1, post)).isEqualTo(post);
	}

//	@Test
//	public void testAdd() {
//		assertEquals(1, 1);
//	}

}
