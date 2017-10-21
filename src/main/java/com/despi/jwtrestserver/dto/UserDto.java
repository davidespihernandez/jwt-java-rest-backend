package com.despi.jwtrestserver.dto;

import com.despi.jwtrestserver.domain.UserInfo;

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
}
