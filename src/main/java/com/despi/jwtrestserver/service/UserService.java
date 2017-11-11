package com.despi.jwtrestserver.service;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.dto.UserDto;
import com.despi.jwtrestserver.exception.ApplicationException;
import com.despi.jwtrestserver.mapper.UserMapper;
import com.despi.jwtrestserver.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	public UserService(UserRepository userRepository, UserMapper userMapper) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public List<UserDto> getAllUsersDetails() {
		return getAllUsers()
				.stream()
				.map(userMapper::convertToDto)
				.collect(Collectors.toList());
	}

	public User getById(Long id) {
		return userRepository.findOne(id);
	}

	public UserDto getUserDetail(Long id) {
		User user = getById(id);
		if (user == null) {
			throw new ApplicationException(String.format("User with id %d doesn't exist", id), HttpStatus.NOT_FOUND);
		}
		return userMapper.convertToDto(user);
	}

	@Transactional
	public User createOrUpdateUser(UserDto userDto) {
		boolean isNew = userDto.getId() == null;
		User user;
		if (isNew) {
			user = new User();
		} else {
			user = userRepository.findOne(userDto.getId());
			if (user == null) {
				throw ApplicationException.entityNotFound(userDto.getId());
			}
		}
		userDto.assertIsValid();
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setUserInfo(userDto.getUserInfo());
		userRepository.save(user);
		return user;
	}
}
