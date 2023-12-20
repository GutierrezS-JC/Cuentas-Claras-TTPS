package com.ttps.cuentasclaras.dto;

import com.ttps.cuentasclaras.model.InvitationStatus;

public class InvitationDTO {
	private UserAltDTO senderUser;
	private UserAltDTO receiverUser;
	private InvitationStatus status;

	public InvitationDTO() {
		super();
	}

	public InvitationDTO(UserAltDTO senderUser, UserAltDTO receiverUser, InvitationStatus status) {
		super();
		this.senderUser = senderUser;
		this.receiverUser = receiverUser;
		this.status = status;
	}

	public UserAltDTO getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(UserAltDTO senderUser) {
		this.senderUser = senderUser;
	}

	public UserAltDTO getReceiverUser() {
		return receiverUser;
	}

	public void setReceiverUser(UserAltDTO receiverUser) {
		this.receiverUser = receiverUser;
	}

	public InvitationStatus getStatus() {
		return status;
	}

	public void setStatus(InvitationStatus status) {
		this.status = status;
	}

}
