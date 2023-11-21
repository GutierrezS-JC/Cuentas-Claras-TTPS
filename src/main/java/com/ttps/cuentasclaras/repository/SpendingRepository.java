package com.ttps.cuentasclaras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.Spending;

public interface SpendingRepository extends JpaRepository<Spending, Integer> {

}
