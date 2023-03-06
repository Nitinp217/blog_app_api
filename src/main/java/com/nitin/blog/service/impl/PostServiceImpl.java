package com.nitin.blog.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nitin.blog.entity.Category;
import com.nitin.blog.entity.Post;
import com.nitin.blog.entity.User;
import com.nitin.blog.exception.ResourceNotFoundException;
import com.nitin.blog.payloads.PostDto;
import com.nitin.blog.payloads.PostResponse;
import com.nitin.blog.repository.ICategoryRepo;
import com.nitin.blog.repository.IPostRepo;
import com.nitin.blog.repository.IUserRepo;
import com.nitin.blog.service.IPostService;

@Service
public class PostServiceImpl implements IPostService {

	@Autowired
	private IPostRepo postRepo;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ICategoryRepo categoryRepo;

	@Autowired
	private IUserRepo userRepo;

	@Override
	public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Post post = mapper.map(postDto, Post.class);
		post.setUser(user);
		post.setCategory(category);
		post.setImageName("default.png");
		post.setAddedDate(new Date());

		Post updatedPost = postRepo.save(post);
		return mapper.map(updatedPost, PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, Integer postId, Integer userId, Integer categoryId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));

		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));

		Post post = mapper.map(this.getPostById(postId), Post.class);
		post.setTitle(postDto.getTitle());
		post.setContent(postDto.getContent());
		post.setUser(user);
		post.setCategory(category);
		post.setAddedDate(new Date());
		return mapper.map(postRepo.save(post), PostDto.class);
	}

	@Override
	public void deletePost(Integer postId) {
		PostDto postById = this.getPostById(postId);
		postRepo.delete(mapper.map(postById, Post.class));
	}

	@Override
	public PostDto getPostById(Integer postId) {
		Post post = postRepo.findById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));
		return mapper.map(post, PostDto.class);
	}

	@Override
	public PostResponse getAllPosts(Integer pageNumber, Integer pageSize) {

		Pageable p = PageRequest.of(pageNumber, pageSize);

		Page<Post> pagePost = postRepo.findAll(p);
		List<Post> posts = pagePost.getContent();
		List<PostDto> postDtos = posts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		
		PostResponse postResponse = new PostResponse();
		
		postResponse.setContent(postDtos);
		postResponse.setPageNumber(pagePost.getNumber());
		postResponse.setPageSize(pagePost.getSize());
		postResponse.setTotalElement(pagePost.getTotalElements());
		postResponse.setTotalPages(pagePost.getTotalPages());
		postResponse.setLastPage(pagePost.isLast());
		
		return postResponse;
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category", "category id", categoryId));
		List<Post> posts = postRepo.findByCategory(category);
		List<PostDto> postDtos = posts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> getPostByUser(Integer userId) {
		User user = userRepo.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User", "user id", userId));
		List<Post> posts = postRepo.findByUser(user);
		List<PostDto> postDtos = posts.stream().map((post) -> mapper.map(post, PostDto.class))
				.collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<PostDto> serchPosts(String keyword) {
		return null;
	}

}
