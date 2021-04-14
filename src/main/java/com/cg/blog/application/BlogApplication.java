package com.cg.blog.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import com.cg.blog.application.config.SwaggerConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Import(SwaggerConfiguration.class)
@SpringBootApplication
@EnableSwagger2
public class BlogApplication {

	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

}
