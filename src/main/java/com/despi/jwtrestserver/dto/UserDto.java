package com.despi.jwtrestserver.dto;

import com.despi.jwtrestserver.domain.UserInfo;
import com.despi.jwtrestserver.exception.ApplicationException;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDto implements Dto{
	Long id;
	String name;
	String email;
	UserInfo userInfo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	@Override
	public String getDtoObjectName() {
		return "users";
	}

	@Override
	public void assertIsValid() {
		List<String> errors = getErrors();
		if (errors.size() > 0) {
			throw new ApplicationException("Errors on user validation: " + errors.stream().collect(Collectors.joining(", ")));
		}
	}

	@Override
	public boolean isValid() {
		if(getErrors().size() == 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<String> getErrors() {
		List<String> errors = new ArrayList<>();
		if (StringUtils.isEmpty(name)) {
			errors.add("Name is empty");
		}
		if (StringUtils.isEmpty(email)) {
			errors.add("Email is empty");
		}
		return errors;
	}


}
