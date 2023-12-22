package com.ttps.cuentasclaras.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttps.cuentasclaras.dto.InvitationCreateDTO;
import com.ttps.cuentasclaras.dto.InvitationDTO;
import com.ttps.cuentasclaras.service.InvitationService;

@RestController
@RequestMapping("/invitations")
@CrossOrigin
public class InvitationController {

	@Autowired
	InvitationService invitationService;

	@GetMapping
	public ResponseEntity<List<InvitationDTO>> getInvitations() {
		List<InvitationDTO> invitations = invitationService.getInvitations();
		if (invitations.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(invitations, HttpStatus.OK);
	}
	
//	@GetMapping("/groupInvitations/{groupId}")
//	public ResponseEntity<List<InvitationDTO>> getGroupInvitations(@PathVariable Integer groupId) {
//		List<InvitationDTO> invitations = invitationService.getGroupInvitations(groupId);
//		if (invitations.isEmpty()) {
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//		}
//		return new ResponseEntity<>(invitations, HttpStatus.OK);
//	}


	@PostMapping("/send")
	public ResponseEntity<Void> sendInvitation(@RequestBody InvitationCreateDTO invitationRequest) {
		if (invitationService.sendInvitation()) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@PostMapping("/accept/{invitationId}/{userId}")
	public ResponseEntity<Void> acceptInvitation(@PathVariable Integer invitationId, @PathVariable Integer userId) {
		if (invitationService.acceptInvitation(invitationId, userId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

	@PostMapping("/reject/{invitationId}/{userId}")
	public ResponseEntity<Void> rejectInvitation(@PathVariable Integer invitationId, @PathVariable Integer userId) {
		if (invitationService.rejectInvitation(invitationId, userId)) {
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}

}
