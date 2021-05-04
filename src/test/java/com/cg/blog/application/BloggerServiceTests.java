package com.cg.blog.application;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cg.blog.application.entities.Blogger;
import com.cg.blog.application.exceptions.BloggerNotFoundException;
import com.cg.blog.application.repositories.IBloggerRepository;
import com.cg.blog.application.services.BloggerServiceImpl;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BloggerServiceTests {

	@Autowired
	BloggerServiceImpl bloggerService;

	@Autowired
	IBloggerRepository bloggerRepository;

	Blogger blogger;

	@BeforeEach
	void setUp() {
		blogger = new Blogger();
		blogger.setId(1);
		blogger.setUsername("name");
		blogger.setPassword("aaaaaaaa");
		blogger.setEmail("xyz@gmail.com");
		blogger = bloggerRepository.saveAndFlush(blogger);
	}

	@Test
	@DisplayName(value = "Test for addBlogger")
	void testAddBlogger() {
		assertThat(bloggerService.addBlogger(blogger)).isEqualTo(blogger);
	}

	@Test
	@DisplayName(value = "Test for addBlogger when adding another blogger")
	void testAddBloggerFail() {
		Blogger otherBlogger = new Blogger();
		otherBlogger.setId(101);
		otherBlogger.setUsername("Pravin");
		otherBlogger.setEmail("xyz@email.com");
		otherBlogger.setPassword("12345678");
		assertNotEquals(blogger, bloggerService.addBlogger(otherBlogger));
	}

	@Test
	@DisplayName(value = "Test for updateBlogger")
	void testUpdateBlogger() {
		Blogger updatedBlogger = new Blogger();
		updatedBlogger.setId(1);
		updatedBlogger.setUsername("Vishal");
		updatedBlogger.setEmail("xyz@email.com");
		updatedBlogger.setPassword("12345678");
		assertEquals(updatedBlogger.getUsername(), bloggerService.updateBlogger(updatedBlogger, 1).getUsername());
	}

	@Test
	@DisplayName(value = "Test for updateBlogger when blogger does not exist")
	void testFailUpdateBlogger() {
		Blogger blogger = new Blogger();
		blogger.setId(101);
		blogger.setUsername("Pravin");
		blogger.setEmail("xyz@email.com");
		blogger.setPassword("12345678");

		assertThrows(BloggerNotFoundException.class, () -> bloggerService.updateBlogger(blogger, 101));
	}

	@Test
	@DisplayName(value = "Test for DeleteBlogger")
	void testDeleteBlogger() {
		Blogger newBlogger = new Blogger();
		newBlogger.setUsername("Vishal");
		newBlogger.setEmail("xyz@email.com");
		newBlogger.setPassword("12345678");
		newBlogger = bloggerRepository.saveAndFlush(newBlogger);
		int bloggerId = newBlogger.getId();
		bloggerService.deleteBlogger(newBlogger.getId());
		assertThrows(BloggerNotFoundException.class, () -> bloggerService.getBlogger(bloggerId));
	}

	@Test
	@DisplayName(value = "Test for DeleteBlogger when blogger does not exist")
	void testDeleteBloggerFail() {
		assertThrows(BloggerNotFoundException.class, () -> bloggerService.deleteBlogger(58));
	}

}