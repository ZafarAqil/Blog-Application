package com.cg.blog.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.services.BloggerServiceImpl;

@SpringBootTest
class BloggerServiceTests {

	@Autowired
	BloggerServiceImpl bloggerService;

	@MockBean
	IBloggerRepository bloggerRepository;

	@Test
	@DisplayName(value = "Test for addBlogger")

	void testAddBlogger() {
		Blogger blogger = new Blogger();
		blogger.setId(100);
		blogger.setName("Pravin");
		blogger.setEmail("xyz@email.com");
		blogger.setPassword("12345678");
		Mockito.when(bloggerRepository.save(blogger)).thenReturn(blogger);
		assertThat(bloggerService.addBlogger(blogger)).isEqualTo(blogger);
	}

	@Test
	@DisplayName(value = "Test for updateBlogger")
	void testUpdateBlogger() {
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
	@DisplayName(value = "Test for DeleteBlogger")
	void testDeleteBlogger() {
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
