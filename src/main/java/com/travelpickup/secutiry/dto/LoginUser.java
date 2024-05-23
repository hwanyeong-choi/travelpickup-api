package com.travelpickup.secutiry.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginUser implements UserDetails {

	private Long id;

	private String role;

	@Builder
	public LoginUser(Long id, String role) {
		this.id = id;
		this.role = role;
	}

	public static LoginUser of(Long id, String role) {
		return LoginUser.builder().id(id).role(role).build();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>(List.of((GrantedAuthority)this::getRole));
	}

	@Override
	public String getPassword() {
		return null;
	}

	@Override
	public String getUsername() {
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
