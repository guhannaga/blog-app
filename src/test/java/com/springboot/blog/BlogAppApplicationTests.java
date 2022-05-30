package com.springboot.blog;

import com.springboot.blog.controller.PostController;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;
import org.assertj.core.api.Assertions;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class BlogAppApplicationTests {

	@Autowired
	PostController postController;
	@Test
	void contextLoads() {
		Assertions.assertThat(postController).isNotNull();
	}

}
