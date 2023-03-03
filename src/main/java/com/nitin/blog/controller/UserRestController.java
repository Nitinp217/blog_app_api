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

import com.nitin.blog.payloads.ApiResponse;
import com.nitin.blog.payloads.UserDto;
import com.nitin.blog.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

	@Autowired
	private UserServiceImpl service;

	// Create User
	@PostMapping("/")
	public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
		UserDto createdUserDto = service.create(userDto);
		return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);
	}

	// Update User
	@PutMapping("/{userId}")
	public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, @PathVariable Integer userId) {
		UserDto updatedUserDto = service.updateUser(userDto, userId);
		
		return ResponseEntity.ok(updatedUserDto);
	}

	// Delete User 
	@DeleteMapping("/{userId}")
	public ResponseEntity<ApiResponse> deleteUserById(@PathVariable Integer userId){
		service.deleteUser(userId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("User Deleted Succefully ..!! ",true),HttpStatus.OK);
	}
	
	// Get All users
	@GetMapping("/")
	public ResponseEntity<List<UserDto>> fettchAllUsers(){
		return ResponseEntity.ok(service.getAllUsers());
	}
	
	// Get one User by Id
	@GetMapping("/{userId}")
	public ResponseEntity<UserDto> fetchUserById(@PathVariable Integer userId){
		return ResponseEntity.ok(service.getUserById(userId));
	}
}
