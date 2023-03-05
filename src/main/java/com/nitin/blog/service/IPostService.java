package com.nitin.blog.service;

import java.util.List;

import com.nitin.blog.payloads.PostDto;

public interface IPostService {

	// create post
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);

	// update post
	PostDto updatePost(PostDto postDto,Integer postId,Integer userId, Integer categoryId);

	// delete post
	void deletePost(Integer postId);

	// get one post
	PostDto getPostById(Integer postId);

	// get all posts
	List<PostDto> getAllPosts();

	// get all posts by category
	List<PostDto> getPostByCategory(Integer categoryId);

	// get all posts by user
	List<PostDto> getPostByUser(Integer userId);

	// search posts
	List<PostDto> serchPosts(String keyword);
}
