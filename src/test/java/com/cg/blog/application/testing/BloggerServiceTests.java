package com.cg.blog.application.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.services.BloggerServiceImpl;

@SpringBootTest
public class BloggerServiceTests {

	@Autowired
	BloggerServiceImpl bloggerService;

	@MockBean
	IBloggerRepository bloggerRepository;

	@Test
	public void testAddBlogger() {
		Blogger blogger = new Blogger();
		blogger.setId(100);
		blogger.setName("Pravin");
		blogger.setEmail("xyz@email.com");
		blogger.setPassword("12345678");
		Mockito.when(bloggerRepository.save(blogger)).thenReturn(blogger);
		assertThat(bloggerService.addBlogger(blogger)).isEqualTo(blogger);
	}

	@Test
	public void testUpdateBlogger() {
		Blogger blogger = new Blogger();
		blogger.setId(100);
		blogger.setName("Pravin");
		blogger.setEmail("xyz@email.com");
		blogger.setPassword("12345678");
		Mockito.when(bloggerRepository.findById(blogger.getId())).thenReturn(Optional.of(blogger));
		blogger.setName("Vishal");
		Mockito.when(bloggerRepository.save(blogger)).thenReturn(blogger);
		assertThat(bloggerService.updateBlogger(blogger, 100)).isEqualTo(blogger);
	}

	@Test
	public void testDeleteBlogger() {
		Blogger blogger = new Blogger();
		blogger.setId(100);
		blogger.setName("Pravin");
		blogger.setEmail("xyz@email.com");
		blogger.setPassword("12345678");
		Mockito.when(bloggerRepository.findById(blogger.getId())).thenReturn(Optional.of(blogger));
		Mockito.when(bloggerRepository.existsById(blogger.getId())).thenReturn(false);
		assertFalse(bloggerRepository.existsById(blogger.getId()));
	}

}