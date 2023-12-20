package com.ttps.cuentasclaras.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.cuentasclaras.dto.InvitationCreateDTO;
import com.ttps.cuentasclaras.service.InvitationService;

@RestController
@RequestMapping("/invitations")
@CrossOrigin
public class InvitationController {

	@Autowired
	InvitationService invitationService;

	@PostMapping("/send")
	public ResponseEntity<Void> sendInvitation(@RequestBody InvitationCreateDTO invitationRequest) {
		if (invitationService.sendInvitation()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@PostMapping("/accept/{invitationId}")
	public ResponseEntity<Void> acceptInvitation(@PathVariable Integer invitationId) {
		if (invitationService.acceptInvitation()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@PostMapping("/reject/{invitacionId}")
	public ResponseEntity<Void> rejectInvitation(@PathVariable Integer invitationId){
		if (invitationService.rejectInvitation()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

}
