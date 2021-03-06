package com.despi.jwtrestserver.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by stephan on 20.03.16.
 */
public class JwtUser implements UserDetails {

	private final Long id;
	private final String username;
	private final String name;
	private final String firstname;
	private final String lastname;
	private final String password;
	private final String email;
	private final Collection<? extends GrantedAuthority> authorities;
	private final boolean enabled;
	private final Timestamp lastPasswordResetDate;

	public JwtUser(
			Long id,
			String username,
			String name,
			String firstname,
			String lastname,
			String email,
			String password, Collection<? extends GrantedAuthority> authorities,
			boolean enabled,
			Timestamp lastPasswordResetDate
	) {
		this.id = id;
		this.username = username;
		this.name = name;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.authorities = authorities;
		this.enabled = enabled;
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	@JsonIgnore
	public Long getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@JsonIgnore
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getEmail() {
		return email;
	}

	@JsonIgnore
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@JsonIgnore
	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public String getName() {
		return name;
	}
}
