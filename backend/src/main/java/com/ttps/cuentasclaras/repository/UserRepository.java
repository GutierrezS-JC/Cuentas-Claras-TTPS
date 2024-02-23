package com.ttps.cuentasclaras.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Integer>{
	boolean existsByUsernameOrEmail(String username, String email);
	
	User findByUsernameAndPassword(String username, String password);
	
    List<User> findTop3ByUsernameContainingAndIdNot(String username, Integer id);

    @Query(value = "SELECT * FROM User u WHERE u.username = :username COLLATE utf8mb4_bin", nativeQuery = true)
    Optional<User> findByUsername(@Param("username") String username);

    @Query("SELECT DISTINCT u FROM User u " +
            "WHERE (LOWER(CONCAT(u.name, ' ', u.lastName)) LIKE LOWER(CONCAT('%', :searchString, '%')) " +
            "       OR LOWER(u.username) LIKE LOWER(CONCAT('%', :searchString, '%'))) " +
            "       AND u.id <> :userId" +
            "       AND u.id NOT IN (SELECT uc.contact.id FROM UserContact uc WHERE uc.user.id = :userId)" +
            "       AND u.id NOT IN (SELECT uc.user.id FROM UserContact uc WHERE uc.contact.id = :userId)")
    List<User> searchUsers(@Param("searchString") String searchString, @Param("userId") Integer userId);
}
