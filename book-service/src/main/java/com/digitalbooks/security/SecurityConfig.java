package com.digitalbooks.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.digitalbooks.entity.ERole;
import com.digitalbooks.security.jwt.JwtFilter;
import com.digitalbooks.service.impl.UserServiceImpl;
/**
 * SecurityConfig implements the WebSecurityConfigurerAdapter and having configuration for spring security
 * @author madhan
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private JwtFilter jwtFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(userService);
	}
	/**
	 * Been for AuthenticationManager
	 */
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	/**
	 * configuration for HttpSecurity to secure the end points 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().authorizeRequests()
				// .anyRequest().permitAll()
				  .antMatchers("/swagger-ui/**").permitAll() .antMatchers("/auth/**")
				  .permitAll().antMatchers("/author/**").hasAuthority(ERole.ROLE_AUTHOR.name())
				  .antMatchers("/reader/**").hasAuthority(ERole.ROLE_READER.name())
				  .anyRequest().authenticated()				 
				.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

	}
}