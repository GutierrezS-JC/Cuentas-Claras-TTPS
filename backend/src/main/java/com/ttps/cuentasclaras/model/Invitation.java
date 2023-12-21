package com.ttps.cuentasclaras.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Invitation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "sender_user_id")
	private User senderUser;

	@ManyToOne
	@JoinColumn(name = "receiver_user_id")
	private User receiverUser;

	@ManyToOne
	@JoinColumn(name = "group_id")
	private Group group;

	@Enumerated(EnumType.STRING)
	private InvitationStatus status;

	public Invitation() {
		super();
	}

	public Invitation(User senderUser, User receiverUser, Group group, InvitationStatus status) {
		super();
		this.senderUser = senderUser;
		this.receiverUser = receiverUser;
		this.group = group;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getSenderUser() {
		return senderUser;
	}

	public void setSenderUser(User senderUser) {
		this.senderUser = senderUser;
	}

	public User getReceiverUser() {
		return receiverUser;
	}

	public void setReceiverUser(User receiverUser) {
		this.receiverUser = receiverUser;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public InvitationStatus getStatus() {
		return status;
	}

	public void setStatus(InvitationStatus status) {
		this.status = status;
	}

}
