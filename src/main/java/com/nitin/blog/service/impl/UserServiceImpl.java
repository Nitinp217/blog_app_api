package com.nitin.blog.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nitin.blog.entity.User;
import com.nitin.blog.exception.ResourceNotFoundException;
import com.nitin.blog.payloads.UserDto;
import com.nitin.blog.repository.UserRepo;
import com.nitin.blog.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepo repo;

	public UserDto create(UserDto dto) {
		User user = this.dtoToUser(dto);
		User savedUser = repo.save(user);
		return this.userToDto(savedUser);
	}

	public UserDto updateUser(UserDto userDto, Integer userId) {
		UserDto userById = this.getUserById(userId);

		User user = this.dtoToUser(userById);

		user.setName(userDto.getName());
		user.setPassword(userDto.getPassword());
		user.setEmail(userDto.getEmail());
		user.setAbout(userDto.getAbout());

		User updatedUser = repo.save(user);

		return this.userToDto(updatedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = repo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "Id", userId));
		return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = repo.findAll();
		List<UserDto> userDtos = users.stream().map(user -> this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}

	@Override
	public void deleteUser(Integer userId) {

		UserDto userById = this.getUserById(userId);
		repo.delete(this.dtoToUser(userById));
	}

	private User dtoToUser(UserDto dto) {
		User user = new User();
		user.setUser_id(dto.getId());
		user.setName(dto.getName());
		user.setEmail(dto.getEmail());
		user.setPassword(dto.getPassword());
		user.setAbout(dto.getAbout());
		return user;
	}

	private UserDto userToDto(User user) {
		UserDto dto = new UserDto();
		dto.setId(user.getUser_id());
		dto.setName(user.getName());
		dto.setEmail(user.getEmail());
		dto.setPassword(user.getPassword());
		dto.setAbout(user.getAbout());
		return dto;
	}
}
