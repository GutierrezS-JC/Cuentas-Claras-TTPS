package com.ttps.cuentasclaras.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps.cuentasclaras.model.Invitation;

public interface InvitationRepository extends JpaRepository<Invitation, Integer> {

}
