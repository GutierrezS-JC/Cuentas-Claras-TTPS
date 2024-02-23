package com.ttps.cuentasclaras.controller;

import com.ttps.cuentasclaras.dto.*;
import com.ttps.cuentasclaras.model.UserContact;
import com.ttps.cuentasclaras.service.UserContactService;
import com.ttps.cuentasclaras.service.UserService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@CrossOrigin
@RequestMapping("/userContacts")
public class UserContactController {

    @Autowired
    UserContactService userContactService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<UserAltDTO>> getUserContacts(@PathVariable Integer userId) {
        List<UserAltDTO> listResponse = userContactService.getUserContacts(userId);
        return ResponseEntity.ok(listResponse);
    }

    @GetMapping("user/{userId}/search")
    public ResponseEntity<List<UserAltDTO>> searchUsers(@PathVariable Integer userId,
                                                            @RequestParam String searchString) {
        List<UserAltDTO> searchResults = userContactService.searchUsers(userId, searchString);
        return new ResponseEntity<>(searchResults, HttpStatus.OK);
    }

    @PostMapping("/sendFriendRequest")
    public ResponseEntity<InvitationsUserContactDTO> sendContactInvitations(@RequestBody InvitationContactDTO invitationReq) {
        InvitationsUserContactDTO response = userContactService.sendInvitations(invitationReq);
        if (response != null) {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/user/{userId}/invitations")
    public ResponseEntity<InvitationsUserContactDTO> getInvitations(@PathVariable Integer userId) {
        InvitationsUserContactDTO response = userContactService.getInvitations(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/acceptSolicitud/{solicitudId}/user/{userId}")
    public ResponseEntity<InvitationsUserContactDTO> acceptSolicitud(@PathVariable Integer solicitudId,
                                                                    @PathVariable Integer userId) {
        InvitationsUserContactDTO response = userContactService.acceptSolicitud(solicitudId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/deleteSolicitud/{solicitudId}/user/{userId}")
    public ResponseEntity<InvitationsUserContactDTO> deleteSolicitud(@PathVariable Integer solicitudId,
                                                                     @PathVariable Integer userId) {
        InvitationsUserContactDTO response = userContactService.deleteSolicitud(solicitudId, userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
