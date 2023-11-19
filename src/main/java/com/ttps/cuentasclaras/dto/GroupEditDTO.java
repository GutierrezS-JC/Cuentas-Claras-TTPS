package com.ttps.cuentasclaras.dto;

public class GroupEditDTO {
	private Integer groupId;
	private String name;
	private Double totalBalance;
	private Integer groupCategoryId;

	public GroupEditDTO(Integer groupId, String name, Double totalBalance, Integer groupCategoryId) {
		super();
		this.groupId = groupId;
		this.name = name;
		this.totalBalance = totalBalance;
		this.groupCategoryId = groupCategoryId;
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

}
