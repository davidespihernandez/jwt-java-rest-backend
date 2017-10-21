package com.despi.jwtrestserver.service;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.dto.UserDto;
import com.despi.jwtrestserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getById(Long id) {
		return userRepository.getOne(id);
	}

	public User createOrUpdateUser(UserDto userDto) {
		boolean isNew = userDto.getId() == null;
		User user;
		if (isNew) {
			user = new User();
		} else {
			user = userRepository.getOne(userDto.getId());
		}
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setUserInfo(userDto.getUserInfo());
		userRepository.save(user);
		return user;
	}
}
