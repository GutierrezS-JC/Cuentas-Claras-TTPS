package com.ttps.cuentasclaras.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	boolean existsByUsernameOrEmail(String username, String email);
	
	User findByUsernameAndPassword(String username, String password);
	
    List<User> findTop3ByUsernameContainingAndIdNot(String username, Integer id);
}
