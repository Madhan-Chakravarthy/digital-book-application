package com.digitalbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digitalbooks.entity.ERole;
import com.digitalbooks.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(ERole name);
}
