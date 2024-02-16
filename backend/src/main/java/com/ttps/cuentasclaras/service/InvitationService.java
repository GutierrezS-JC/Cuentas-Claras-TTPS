package com.ttps.cuentasclaras.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.ttps.cuentasclaras.dto.GroupDetailsDTO;
import com.ttps.cuentasclaras.dto.InvitationCreateDTO;
import com.ttps.cuentasclaras.exception.ResourceNotFoundException;
import com.ttps.cuentasclaras.model.Group;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.dto.InvitationDTO;
import com.ttps.cuentasclaras.model.Invitation;
import com.ttps.cuentasclaras.model.InvitationStatus;
import com.ttps.cuentasclaras.repository.InvitationRepository;

@Service
public class InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;

	@Autowired
	private GroupService groupService;

	@Autowired
	private UserService userService;

	public GroupDetailsDTO sendInvitations(InvitationCreateDTO invitationRequest) {
		try {
			Set<Invitation> invitations = new HashSet<>();
			for (Integer receiverId : invitationRequest.getReceiverListId()) {
				Invitation invitation = new Invitation();
				invitation.setGroup(groupService.findById(invitationRequest.getGroupId()));
				invitation.setSenderUser(userService.findUserById(invitationRequest.getSenderId()));
				invitation.setReceiverUser(userService.findUserById(receiverId));
				invitation.setStatus(InvitationStatus.PENDING);
				invitations.add(invitation);
			}
			invitationRepository.saveAll(invitations);
			Group groupUpdated = groupService.findById(invitationRequest.getGroupId());
			return groupService.mapGroupDetailsDTO(groupUpdated);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean rejectInvitation(Integer invitationId, Integer userId) {
		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
		if (userId == invitation.getReceiverUser().getId() && invitation.getStatus() == InvitationStatus.PENDING) {
			invitation.setStatus(InvitationStatus.REJECTED);
			invitationRepository.save(invitation);
			return true;
		}
		return false;
	}

	public boolean acceptInvitation(Integer invitationId, Integer userId) {

		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
		if (invitation != null) {
			// Si el userId (el usuario que solicito la peticion) es igual al usuario al que
			// le llego la solicitud (es decir, aquel que deberia aceptar)
			if (userId == invitation.getReceiverUser().getId() && invitation.getStatus() == InvitationStatus.PENDING) {
				// El usuario acepta la solicitud y se registra en la invitacion
				invitation.setStatus(InvitationStatus.ACCEPTED);

				// Se registra el usuario como integrante del grupo (member)
				boolean result = groupService.addMember(userId, invitation.getGroup());
				if (result)
					invitationRepository.save(invitation);

				return result;
			}
		}
		return false;
	}

	public List<InvitationDTO> getInvitations() {
		List<Invitation> list = invitationRepository.findAll();
		List<InvitationDTO> listResponse = new ArrayList<>();

		for (Invitation invitation : list) {
			listResponse.add(new InvitationDTO(invitation.getId(), userService.mapUserAlt(invitation.getSenderUser()),
					userService.mapUserAlt(invitation.getReceiverUser()), invitation.getStatus()));
		}
		return listResponse;
	}

	public boolean cancelInvitation(Integer invitationId, Integer userId) {
		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
		if (userId == invitation.getSenderUser().getId() && invitation.getStatus() == InvitationStatus.PENDING) {
			invitation.setStatus(InvitationStatus.CANCELLED);
			invitationRepository.save(invitation);
			return true;
		}
		return false;
	}
}
