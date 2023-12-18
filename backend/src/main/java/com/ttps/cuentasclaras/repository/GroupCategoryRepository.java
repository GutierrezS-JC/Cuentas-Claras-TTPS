package com.ttps.cuentasclaras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.GroupCategory;

public interface GroupCategoryRepository extends JpaRepository<GroupCategory, Integer> {

	boolean existsByName(String name);

}
