package com.ttps.cuentasclaras.controller;

import java.util.List;

import com.ttps.cuentasclaras.dto.GroupDetailsDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ttps.cuentasclaras.dto.InvitationCreateDTO;
import com.ttps.cuentasclaras.dto.InvitationDTO;
import com.ttps.cuentasclaras.service.InvitationService;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin
@RequestMapping("/invitations")
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

	@PostMapping("/send")
	public ResponseEntity<GroupDetailsDTO> sendInvitations(@RequestBody InvitationCreateDTO invitationRequest) {
		GroupDetailsDTO groupResult = invitationService.sendInvitations(invitationRequest);
		if (groupResult != null) {
			return new ResponseEntity<>(groupResult, HttpStatus.OK);
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

	@PostMapping("/cancel/{invitationId}/{userId}")
	public ResponseEntity<Boolean> cancelInvitation(@PathVariable Integer invitationId,
													@PathVariable Integer userId) {
		if (invitationService.cancelInvitation(invitationId, userId)) {
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
		return new ResponseEntity<>(false, HttpStatus.CONFLICT);
	}
}
