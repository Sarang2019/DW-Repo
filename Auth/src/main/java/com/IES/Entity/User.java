package com.IES.Entity;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.validation.annotation.Validated;

import com.IES.Enum.Role;
import com.IES.Security.CustomerUserService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Entity
@Validated
@Slf4j
public class User implements CustomerUserService {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "name", nullable = false, length = 100)
	@NotBlank(message = "name is required")
	@Size(min = 2, max = 100, message = "name must be 3-100 char")
	private String name;

	@Column(name = "email", unique = true, nullable = false, length = 150)
	@Email(message = "invalid email format")
	@NotBlank(message = "email is required")
	private String email;

	@Column(name = "phno")
	@NotNull(message = "phone number required")
	@Digits(integer = 10, fraction = 0, message = "phone number must be 10 digit")
	private long phno;

	@Column(name = "pwd", nullable = false)
	@NotBlank(message = "password required")
	@Size(min = 6, message = "password must be at least 6 character")
	private String pwd;

	@Enumerated(EnumType.STRING)
	@NotNull(message = "role is required")
	@Column(name = "role", nullable = false, length = 20)
	private Role role;

	@CreationTimestamp
	@Column(name = "created_date", updatable = false)
	private LocalDateTime createdDate;

	@UpdateTimestamp
	@Column(name = "updated_date")
	private LocalDateTime updatedDate;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		log.debug("getAuthorities()");
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role.name().toString());
		return Collections.singletonList(authority);
	}

	@Override
	public boolean isAccountNonExpired() {
		log.debug("isAccountNonExpired()");
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		log.debug("isAccountNonLocked()");
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		log.debug("isCredentialsNonExpired()");
		return true;
	}

	@Override
	public boolean isEnabled() {
		log.debug("isEnabled()");
		return true;
	}

	@Override
	public String getPassword() {
		return this.pwd;
	}

	@Override
	public String getUsername() {
		return this.name;
	}

}
