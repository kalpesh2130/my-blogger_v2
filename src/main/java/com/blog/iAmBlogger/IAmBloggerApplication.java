package com.blog.iAmBlogger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.blog.*"})
public class IAmBloggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(IAmBloggerApplication.class, args);
	}

}
