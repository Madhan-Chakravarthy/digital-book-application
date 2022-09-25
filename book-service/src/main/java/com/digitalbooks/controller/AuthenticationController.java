package com.digitalbooks.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.digitalbooks.entity.Author;
import com.digitalbooks.entity.ERole;
import com.digitalbooks.entity.LoginRequest;
import com.digitalbooks.entity.LoginResponse;
import com.digitalbooks.entity.Role;
import com.digitalbooks.entity.SignupRequest;
import com.digitalbooks.entity.User;
import com.digitalbooks.security.jwt.JwtUtility;
import com.digitalbooks.service.impl.AuthorServiceImpl;
import com.digitalbooks.service.impl.UserDetailsImpl;
import com.digitalbooks.service.impl.UserServiceImpl;

import lombok.extern.slf4j.Slf4j;
/**
 * 
 * @author madhan AuthenticationController contains the end point for Login and sign up
 *
 */
@RestController
@RequestMapping("/auth")
@Slf4j
@CrossOrigin
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserServiceImpl userSevice;
	@Autowired
	private JwtUtility jwtUtility;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthorServiceImpl authorService;
	/**
	 * End point for user Login
	 * @param login
	 * @return ResponseEntity<LoginResponse> 
	 * @throws Exception
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponse>  authenticateUser(@Valid @RequestBody LoginRequest login) throws Exception {


			Authentication authentication =authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();	
		log.info("security {}", userDetails.toString());
		log.info("security ****************** {}", userDetails.toString());
		final String token = jwtUtility.generateToken(userDetails);
		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());
		log.info("roles {}", roles);
		LoginResponse loginResponse = new LoginResponse();
		loginResponse.setId(userDetails.getId());
		loginResponse.setToken(token);
		loginResponse.setRoles(roles);
	   
		return new ResponseEntity<LoginResponse>(loginResponse,HttpStatus.OK);

	}
	/**
	 * End point for Sign up
	 * @param signUp
	 * @return  ResponseEntity<?>
	 */
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody SignupRequest signUp) {
		log.debug(signUp.toString());
		if (userSevice.validateUserEmail(signUp.getEmail())) {
			return new ResponseEntity<>("Email already exist!",HttpStatus.BAD_REQUEST);
		}
		if (userSevice.validateUserName(signUp.getUsername())) {
			return new ResponseEntity<>("User name already exist!",HttpStatus.BAD_REQUEST);
		}
		if (signUp.getRole().isEmpty()) {
			return new ResponseEntity<>("Select atleast one role!",HttpStatus.BAD_REQUEST);
		}else {
			User user = new User();
			user.setUsername(signUp.getUsername());
			user.setEmail(signUp.getEmail());
			user.setPassword(passwordEncoder.encode(signUp.getPassword()));
			Set<Role> roles = new HashSet<>();
			signUp.getRole().forEach(role -> {
				switch (role) {
				case "reader":
					Role readerRole =userSevice.findRole(ERole.ROLE_READER);
					roles.add(readerRole);
					user.setRoles(roles);
					break;
				case "author":
					Role authorRole =userSevice.findRole(ERole.ROLE_AUTHOR);
					roles.add(authorRole);
					user.setRoles(roles);
					  Author author = new Author();
					  author.setName(signUp.getName());
					  author.setAboutAuthor(signUp.getAboutAuthor());
					  Author savedAuthor=authorService.saveAuthor(author);
					  if(savedAuthor!=null) {
						  user.setAuthor(author);
					  }					  
					 
					break;
				}
			});
			userSevice.signup(user);
		}
 
		
		return  new ResponseEntity<>(HttpStatus.OK);

	}

}
