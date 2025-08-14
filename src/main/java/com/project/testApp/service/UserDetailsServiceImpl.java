package com.project.testApp.service;

import com.project.testApp.model.entity.User;
import com.project.testApp.exception.userException.UserNotFoundException;
import com.project.testApp.model.repository.UserRepository;
import com.project.testApp.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.project.testApp.service.ServiceText.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info(USER_DETAILS_SERVICE_LOG, username);

		User user = userRepository.findByUsername(username).orElseThrow(() -> {
			log.error(USER_NOT_FOUND_LOG, username);
			return new UserNotFoundException(username);
		});

		List<GrantedAuthority> authoritiesList = new ArrayList<>();
		authoritiesList.add(new SimpleGrantedAuthority("user"));

		return new JwtUserDetails(user.getUserId(), user.getUserName(), user.getPassword(), user.getEmail(), authoritiesList);
	}

	public UserDetails loadUserById(Long id) {
		log.info(USER_ID_FETCH_LOG, id);

		User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		return JwtUserDetails.create(user);
	}
}
