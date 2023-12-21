package com.ttps.cuentasclaras.dto;

import com.ttps.cuentasclaras.model.GroupCategory;

public class GroupDTO {
	private Integer groupId;
	private String name;
	private Double totalBalance;
	private GroupCategory groupCategory;
	private Integer ownerId;

	public GroupDTO(Integer groupId, String name, Double totalBalance, GroupCategory groupCategory, Integer ownerId) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.totalBalance = totalBalance;
		this.groupCategory = groupCategory;
		this.ownerId = ownerId;
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

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

}
