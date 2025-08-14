package com.project.testApp.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.project.testApp.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;

import static com.project.testApp.security.SecurityText.JWT_USER_DETAILS_CREATE_LOG;

@Slf4j
@Getter
@RequiredArgsConstructor
public class JwtUserDetails implements UserDetails {

	private final Long id;
	private final String username;
	private final String password;
	private final String email;
	private final Collection<? extends GrantedAuthority> authorities;


    public static JwtUserDetails create(User user) {
		log.info(JWT_USER_DETAILS_CREATE_LOG, user.getUserName());
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        authoritiesList.add(new SimpleGrantedAuthority("user"));
        return new JwtUserDetails(user.getUserId(), user.getUserName(), user.getEmail(), user.getPassword(), authoritiesList);
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
