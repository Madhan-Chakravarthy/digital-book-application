package com.digitalbooks.service;

import com.digitalbooks.entity.User;

public interface UserService {
	public User signup(User user);
	public User getUser(String username);
}
