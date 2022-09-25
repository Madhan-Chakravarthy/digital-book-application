package com.digitalbooks.service;

import java.util.List;

import com.digitalbooks.entity.User;

public interface UserService {
	public User signup(User user);
	public User getUser(String username);
	public List<User> getUsers();
	public Boolean validateUserName(String userName) ;
	public Boolean validateUserEmail(String email);
}
