package com.ttps.cuentasclaras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.SpendingCategory;

public interface SpendingCategoryRepository extends JpaRepository<SpendingCategory, Integer> {

	boolean existsByName(String name);

}
