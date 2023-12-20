package com.ttps.cuentasclaras.dto;

import java.util.List;

import com.ttps.cuentasclaras.model.GroupCategory;

public class GroupDetailsDTO {
	private Integer groupId;
	private String name;
	private Double totalBalance;
	private GroupCategory groupCategory;
	private UserAltDTO owner;
	private List<UserAltDTO> members;
	private List<InvitationDTO> invitations;

	public GroupDetailsDTO() {
		super();
	}

	public GroupDetailsDTO(Integer groupId, String name, Double totalBalance, GroupCategory groupCategory,
			UserAltDTO owner, List<UserAltDTO> members, List<InvitationDTO> invitations) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.totalBalance = totalBalance;
		this.groupCategory = groupCategory;
		this.owner = owner;
		this.members = members;
		this.invitations = invitations;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(Double totalBalance) {
		this.totalBalance = totalBalance;
	}

	public GroupCategory getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(GroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}

	public UserAltDTO getOwner() {
		return owner;
	}

	public void setOwner(UserAltDTO owner) {
		this.owner = owner;
	}

	public List<UserAltDTO> getMembers() {
		return members;
	}

	public void setMembers(List<UserAltDTO> members) {
		this.members = members;
	}

	public List<InvitationDTO> getInvitations() {
		return invitations;
	}

	public void setInvitations(List<InvitationDTO> invitations) {
		this.invitations = invitations;
	}

}
