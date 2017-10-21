package com.despi.jwtrestserver.factory;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.domain.UserInfo;

import java.util.UUID;

public class UserFactory extends EntityFactory<User> {

	@Override
	public User build() {
		return new User();
	}

	public User create() {
		User user = build();
		create(user, u -> {
			u.setName(UUID.randomUUID().toString());
			u.setEmail(UUID.randomUUID().toString());
			u.setUserInfo(new UserInfo());
		});
		return user;
	}

	public User createWithNameAndEmail(String name, String email) {
		User user = build();
		create(user, u -> {
			u.setName(name);
			u.setEmail(email);
			u.setUserInfo(new UserInfo());
		});
		return user;
	}

	public User createWithNameAndEmailAndDetails(String name, String email, UserInfo userInfo) {
		User user = build();
		create(user, u -> {
			u.setName(name);
			u.setEmail(email);
			u.setUserInfo(userInfo);
		});
		return user;
	}
}
