package com.ttps.cuentasclaras.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttps.cuentasclaras.model.Invitation;
import com.ttps.cuentasclaras.model.InvitationStatus;
import com.ttps.cuentasclaras.repository.InvitationRepository;

@Service
public class InvitationService {

	@Autowired
	private InvitationRepository invitationRepository;

	@Autowired
	private GroupService groupService;

	public boolean sendInvitation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean rejectInvitation() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean acceptInvitation(Integer invitationId, Integer userId) {

		Invitation invitation = invitationRepository.findById(invitationId).orElse(null);
		if (invitation != null) {
			// Si el userId (el usuario que solicito la peticion) es igual al usuario al que
			// le llego la solicitud (es decir, aquel que deberia aceptar)
			if (userId == invitation.getReceiverUser().getId()) {
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

}
