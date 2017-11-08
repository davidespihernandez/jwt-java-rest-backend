package com.despi.jwtrestserver.controller;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.dto.UserDto;
import com.despi.jwtrestserver.mapper.UserMapper;
import com.despi.jwtrestserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {
	private final UserService userService;
	private final UserMapper userMapper;

	@Autowired
	public UserController(UserService userService
			, UserMapper userMapper) {
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping("/{id}")
	public UserDto getUser(@PathVariable Long id) {
		return userService.getUserDetail(id);
	}

	@GetMapping
	public List<UserDto> getAllUsers() {
		return userService.getAllUsersDetails();
	}

	@PostMapping
	public UserDto createOrUpdateUser(@RequestBody UserDto userDto) {
		User user = userService.createOrUpdateUser(userDto);
		return userMapper.convertToDto(user);
	}
}
