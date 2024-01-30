package com.ttps.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer>{
	boolean existsByUsernameOrEmail(String username, String email);
	
	User findByUsernameAndPassword(String username, String password);
	
    List<User> findTop3ByUsernameContainingAndIdNot(String username, Integer id);

    Optional<User> findByUsername(String username);
}
