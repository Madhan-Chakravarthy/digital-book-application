package com.digitalbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbooks.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);

}
