package com.ttps.cuentasclaras.repository;

import com.ttps.cuentasclaras.model.UserContact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserContactRepository extends JpaRepository<UserContact, Integer> {

    List<UserContact> findByUserIdAndContactSinceIsNull(Integer userId);

    List<UserContact> findByContactIdAndContactSinceIsNull(Integer contactId);

}