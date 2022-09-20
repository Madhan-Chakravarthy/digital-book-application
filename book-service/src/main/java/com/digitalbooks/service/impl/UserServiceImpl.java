package com.digitalbooks.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.digitalbooks.entity.ERole;
import com.digitalbooks.entity.Role;
import com.digitalbooks.entity.User;
import com.digitalbooks.repository.RoleRepository;
import com.digitalbooks.repository.UserRepository;
import com.digitalbooks.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService ,UserDetailsService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleRepository roleRepository;
	@Override
	public User signup(User user) {
		log.info("Saving new user to database",user.getUsername());
		return userRepository.save(user);
	}
	public Boolean validateUserEmail(String email) {
		
		return userRepository.existsByEmail(email);
	}
	
	public Role findRole(ERole role) {
		return roleRepository.findByName(role);
	}

	public Boolean validateUserName(String userName) {
		
		return 	userRepository.existsByUsername(userName);
	}
	@Override
	public User getUser(String username) {
		log.info("fetching user with user name from the database",username);
		return userRepository.findByUsername(username);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if(user==null) {
			log.info("User not found in the database");
			throw new UsernameNotFoundException(username);
		}else {
			log.info("User found in the database :{}",user.toString());
			return UserDetailsImpl.build(user);
		}
	/*ist<SimpleGrantedAuthority> authorities=new ArrayList<>();
	user.getRoles().forEach(role->{
		authorities.add(new SimpleGrantedAuthority(role.getName().name()));
	});
	
		return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),authorities);*/
	}
	public List<User> getUsers() {
		return userRepository.findAll();
	}
}
