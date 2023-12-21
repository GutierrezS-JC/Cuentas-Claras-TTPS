package com.ttps.cuentasclaras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.Group;
import com.ttps.cuentasclaras.model.User;

public interface GroupRepository extends JpaRepository<Group, Integer> {
	boolean existsByOwnerAndName(User owner, String groupName);
}
