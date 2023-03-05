package com.nitin.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nitin.blog.payloads.PostDto;
import com.nitin.blog.service.IPostService;

@RestController
@RequestMapping("/api")
public class PostRestController {

	@Autowired
	private IPostService service;

	// create
	@PostMapping("/user/{userId}/category/{categoryId}/posts")
	public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId,
			@PathVariable Integer categoryId) {
		PostDto createdPostDto = service.createPost(postDto, userId, categoryId);
		return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
	}

	// get posts by user Id
	@GetMapping("/user/{userId}/posts")
	public ResponseEntity<List<PostDto>> fetchPostByUser(@PathVariable Integer userId) {
		List<PostDto> postDtos = service.getPostByUser(userId);
		return ResponseEntity.ok(postDtos);
	}

	// get posts by category id
	@GetMapping("/category/{categoryId}/posts")
	public ResponseEntity<List<PostDto>> fetchPostByCategory(@PathVariable Integer categoryId) {
		List<PostDto> postDtos = service.getPostByCategory(categoryId);
		return ResponseEntity.ok(postDtos);
	}

	// Get single post by id
	@GetMapping("/post/{postId}")
	public ResponseEntity<PostDto> fetchOnePost(@PathVariable Integer postId) {
		return ResponseEntity.ok(service.getPostById(postId));
	}

	// get all posts
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> fetchAllPosts() {
		return ResponseEntity.ok(service.getAllPosts());
	}

	// update post
	@PutMapping("/user/{userId}/category/{categoryId}/post/{postId}")
	public ResponseEntity<PostDto> editPost(@RequestBody PostDto postDto, @PathVariable Integer postId,
			@PathVariable Integer userId, @PathVariable Integer categoryId) {
		PostDto updatedPost = service.updatePost(postDto, postId, userId, categoryId);
		return new ResponseEntity<PostDto>(updatedPost, HttpStatus.OK);

	}

	// delete post
	@DeleteMapping("/post/{postId}")
	public ResponseEntity<String> deletePost(@PathVariable Integer postId) {
		service.deletePost(postId);
		return ResponseEntity.ok("Post of id " + postId + " deleted successfully .... !!");
	}

}
