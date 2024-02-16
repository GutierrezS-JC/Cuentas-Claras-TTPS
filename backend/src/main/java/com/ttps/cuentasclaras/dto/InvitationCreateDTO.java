package com.ttps.cuentasclaras.dto;

import java.util.List;

public class InvitationCreateDTO {

	private Integer groupId;
	private Integer senderId;
	private List<Integer> receiverListId;

	public InvitationCreateDTO() {
		super();
	}

	public InvitationCreateDTO(Integer groupId, Integer senderId, List<Integer> receiverListId) {
		super();
		this.groupId = groupId;
		this.senderId = senderId;
		this.receiverListId = receiverListId;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getSenderId() {
		return senderId;
	}

	public void setSenderId(Integer senderId) {
		this.senderId = senderId;
	}

	public List<Integer> getReceiverListId() {
		return receiverListId;
	}

	public void setReceiverListId(List<Integer> receiverListId) {
		this.receiverListId = receiverListId;
	}
}
