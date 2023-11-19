package com.ttps.cuentasclaras.dto;

public class GroupDTO {
	private Integer groupId;
	private String name;
	private Double totalBalance;
	private Integer groupCategoryId;
	private Integer ownerId;

	public GroupDTO(Integer groupId, String name, Double totalBalance, Integer groupCategoryId, Integer ownerId) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.totalBalance = totalBalance;
		this.groupCategoryId = groupCategoryId;
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

	public Integer getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(Integer groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

}
