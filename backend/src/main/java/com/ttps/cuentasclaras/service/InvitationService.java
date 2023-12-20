package com.ttps.cuentasclaras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.repository.InvitationRepository;

@Service
public class InvitationService {
	
	@Autowired
	private InvitationRepository invitationRepository;

	public boolean sendInvitation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rejectInvitation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean acceptInvitation() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
