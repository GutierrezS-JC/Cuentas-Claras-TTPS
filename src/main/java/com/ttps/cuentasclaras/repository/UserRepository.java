package com.ttps.cuentasclaras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	boolean existsByUsernameOrEmail(String username, String email);
}
