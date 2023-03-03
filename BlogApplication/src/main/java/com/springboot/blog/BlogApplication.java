package com.springboot.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.springboot.blog.entity.Category;
import com.springboot.blog.entity.Post;
import com.springboot.blog.payload.CategoryDto;
import com.springboot.blog.payload.PostDto;
import com.springboot.blog.repository.CategoryRepository;
import com.springboot.blog.repository.PostRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private PostRepository postRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// CAtegory
		CategoryDto categoryDto = new CategoryDto(1L, "Java-Blog", "Java Knowledge Blog");
		categoryRepository.save(modelMapper().map(categoryDto, Category.class));
		categoryDto = new CategoryDto(2L, "Machine-Learning-Blog", "ML Knowledge Blog");
		categoryRepository.save(modelMapper().map(categoryDto, Category.class));
		
		// POSTS
		PostDto postDto = new PostDto();
		postDto.setId(1L);
		postDto.setCategoryId(1L);
		postDto.setTitle("Java JVM Architecture");
		postDto.setDescription("How JVM Works – JVM Architecture?");
		postDto.setContent("JVM(Java Virtual Machine) acts as a run-time engine to run Java applications. "
				+ "	JVM is the one that actually calls the main method present in a java code. JVM is a part of JRE(Java Runtime Environment).");
		postRepository.save(modelMapper().map(postDto, Post.class));
		
		postDto.setId(2L);
		postDto.setCategoryId(1L);
		postDto.setTitle("Memory Management in Java");
		postDto.setDescription("What is Memory Management In Java?");
		postDto.setContent("Memory management in Java is the process of allocating working memory space to new objects and "
				+ "properly removing unreferenced objects to create space for those new object allocations.");
		postRepository.save(modelMapper().map(postDto, Post.class));
		
		postDto.setId(3L);
		postDto.setCategoryId(2L);
		postDto.setTitle("4 Types of Machine Learning");
		postDto.setDescription("4 Types of Machine Learning and How to Build a Great Career in Each");
		postDto.setContent("JThe four different types of machine learning are:1-Supervised Learning, "
				+ "2-Unsupervised Learning, 3-Semi-Supervised Learning, 4-Reinforced Learning. ");
		postRepository.save(modelMapper().map(postDto, Post.class));
		
	}

}
