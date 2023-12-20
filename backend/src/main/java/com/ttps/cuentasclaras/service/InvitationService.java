package com.ttps.cuentasclaras.service;

import java.util.ArrayList;
import java.util.List;

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

	public boolean sendInvitation() {
		// TODO Auto-generated method stub
		return false;
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

}
