package com.despi.jwtrestserver;

import com.despi.jwtrestserver.domain.User;
import com.despi.jwtrestserver.domain.UserInfo;
import com.despi.jwtrestserver.dto.UserDto;
import com.despi.jwtrestserver.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	@Autowired
	UserService userService;

	@Test
	public void createUser() {
		UserDto userDto = new UserDto();
		String randomString = UUID.randomUUID().toString();
		userDto.setEmail(randomString);
		userDto.setName(randomString);
		userDto.setPassword(randomString);
		UserInfo userInfo = new UserInfo();
		userInfo.setFullAddress("Address");
		userInfo.setTitle(UserInfo.TITLE.MR);
		userInfo.setTimezone("CET");
		userDto.setUserInfo(userInfo);
		User user = userService.createOrUpdateUser(userDto);
		assertThat(user).isNotNull();
		assertThat(user.getName()).isEqualTo(randomString);
	}
}
