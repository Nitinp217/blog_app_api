package com.nitin.blog.service;

import java.util.List;

import com.nitin.blog.payloads.UserDto;

public interface IUserService {
	
	UserDto create(UserDto dto);

	UserDto updateUser(UserDto user, Integer userID);

	UserDto getUserById(Integer userId);

	List<UserDto> getAllUsers();

	void deleteUser(Integer userId);
}
