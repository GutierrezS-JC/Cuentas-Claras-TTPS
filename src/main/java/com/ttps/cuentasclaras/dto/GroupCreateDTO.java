package com.ttps.cuentasclaras.dto;

public class GroupCreateDTO {
	private String name;
	private Double totalBalance;
	private Integer userOwnerId;
	private Integer groupCategoryId;

	public GroupCreateDTO() {

	}

	public GroupCreateDTO(String name, Double totalBalance, Integer userOwnerId, Integer groupCategoryId) {
		super();
		this.name = name;
		this.totalBalance = totalBalance;
		this.userOwnerId = userOwnerId;
		this.groupCategoryId = groupCategoryId;
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

	public Integer getUserOwnerId() {
		return userOwnerId;
	}

	public void setUserOwnerId(Integer userOwnerId) {
		this.userOwnerId = userOwnerId;
	}

	public Integer getGroupCategoryId() {
		return groupCategoryId;
	}

	public void setGroupCategoryId(Integer groupCategoryId) {
		this.groupCategoryId = groupCategoryId;
	}

}
