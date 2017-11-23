package com.despi.jwtrestserver.factory;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.domain.UserInfo;
import com.despi.jwtrestserver.dto.UserDto;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UserFactory extends EntityFactory<User> {

	@Override
	public User build() {
		return new User();
	}

	public User create() {
		User user = build();
		create(user, u -> {
			u.setName(UUID.randomUUID().toString());
			u.setEmail(UUID.randomUUID().toString() + "@gmail.com");
			u.setPassword("password");
			u.setLastPasswordResetDate(null);
			u.setUserInfo(new UserInfo());
		});
		return user;
	}

	public User createWithNameAndEmail(String name, String email) {
		User user = build();
		create(user, u -> {
			u.setName(name);
			u.setEmail(email);
			u.setPassword("password");
			u.setLastPasswordResetDate(null);
			u.setUserInfo(new UserInfo());
		});
		return user;
	}

	public User createWithNameAndEmailAndDetails(String name, String email, UserInfo userInfo) {
		User user = build();
		create(user, u -> {
			u.setName(name);
			u.setEmail(email);
			u.setPassword("password");
			u.setLastPasswordResetDate(null);
			u.setUserInfo(userInfo);
		});
		return user;
	}

	public static UserDto createDto() {
		UserDto userDto = new UserDto();
		userDto.setName(UUID.randomUUID().toString());
		userDto.setEmail(UUID.randomUUID().toString() + "@gmail.com");
		userDto.setPassword("password");
		userDto.setUserInfo(new UserInfo());
		return userDto;
	}

	public static UserDto createDto(User user) {
		UserDto userDto = new UserDto();
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setUserInfo(user.getUserInfo());
		userDto.setId(user.getId());
		return userDto;
	}

}
