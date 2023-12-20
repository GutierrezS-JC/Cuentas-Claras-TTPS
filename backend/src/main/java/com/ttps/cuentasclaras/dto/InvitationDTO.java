package com.ttps.cuentasclaras.dto;

import com.ttps.cuentasclaras.model.InvitationStatus;

public class InvitationDTO {
	private Integer id;
	private UserAltDTO senderUser;
	private UserAltDTO receiverUser;
	private InvitationStatus status;

	public InvitationDTO() {
		super();
	}

	public InvitationDTO(Integer id, UserAltDTO senderUser, UserAltDTO receiverUser, InvitationStatus status) {
		super();
		this.id = id;
		this.senderUser = senderUser;
		this.receiverUser = receiverUser;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
