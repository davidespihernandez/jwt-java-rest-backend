package com.despi.jwtrestserver.domain;

import com.despi.jwtrestserver.domain.jsonb.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class User extends StampedEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(columnDefinition = "serial")
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String email;

	@Type(type = "jsonb")
	@Column(columnDefinition = "jsonb")
	private UserInfo userInfo = new UserInfo();

	@Column(length = 100)
	@NotNull
	@Size(min = 4, max = 100)
	private String password;

	@NotNull
	private Boolean enabled = true;

	private Timestamp lastPasswordResetDate;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "user_authority",
			joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
			inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
	private List<Authority> authorities;

	public User() {
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Timestamp getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Timestamp lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}

	public List<Authority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User)) return false;

		User user = (User) o;

		return getEmail().equals(user.getEmail());
	}

	@Override
	public int hashCode() {
		return getEmail().hashCode();
	}
}