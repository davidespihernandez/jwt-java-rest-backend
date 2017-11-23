package com.despi.jwtrestserver.service;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.dto.UserDto;
import com.despi.jwtrestserver.exception.ApplicationException;
import com.despi.jwtrestserver.mapper.UserMapper;
import com.despi.jwtrestserver.repository.UserRepository;
import com.despi.jwtrestserver.security.JwtUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	public UserService(UserRepository userRepository,
					   UserMapper userMapper,
					   PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.userMapper = userMapper;
		this.passwordEncoder = passwordEncoder;
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
		userDto.assertIsValid();
		User user;
		if (isNew) {
			user = new User();
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
		} else {
			user = userRepository.findOne(userDto.getId());
			if (user == null) {
				throw ApplicationException.entityNotFound(userDto.getId());
			}
		}
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setUserInfo(userDto.getUserInfo());
		try {
			userRepository.save(user);
		} catch (Exception e) {
			throw new ApplicationException("Error saving used: " + e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
		}
		userRepository.save(user);
		return user;
	}

	public User getCurrentLoggedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			Object principal = authentication.getPrincipal();
			if (principal != null && principal.getClass().isAssignableFrom(JwtUser.class)) {
				JwtUser jwtUser = (JwtUser) principal;
				return userRepository.findOne(jwtUser.getId());
			}
		}
		return null;
	}
}
