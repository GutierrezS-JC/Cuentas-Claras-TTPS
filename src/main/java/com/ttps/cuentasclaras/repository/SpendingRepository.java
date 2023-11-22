package com.ttps.cuentasclaras.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ttps.cuentasclaras.model.Spending;

public interface SpendingRepository extends JpaRepository<Spending, Integer> {

	@Query(value = "SELECT sp.* FROM spending sp\r\n" + "WHERE sp.owner_user_id = :ownerId AND sp.group_id = :groupId "
			+ "AND sp.spending_category_id = :spendingCategoryId", nativeQuery = true)
	Object searchWithOwnerAndGroupAndCategory(Integer ownerId, Integer groupId, Integer spendingCategoryId);

	@Query(value = "SELECT sp.* FROM spending sp\r\n"
			+ "WHERE sp.owner_user_id = :ownerId AND sp.spending_category_id = :spendingCategoryId", nativeQuery = true)
	Object searchWithOwnerAndCategory(Integer ownerId, Integer spendingCategoryId);

}
