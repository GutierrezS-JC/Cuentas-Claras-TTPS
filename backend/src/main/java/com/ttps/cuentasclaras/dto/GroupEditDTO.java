package com.ttps.cuentasclaras.dto;

public class GroupEditDTO {
	private String name;
	private Double totalBalance;
	private Integer groupCategoryId;
	private String description;

	public GroupEditDTO(String name, Double totalBalance, Integer groupCategoryId,
						String description) {
		super();
		this.name = name;
		this.totalBalance = totalBalance;
		this.groupCategoryId = groupCategoryId;
		this.description = description;
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

	public String getDescription() { return description; }

	public void setDescription(String description) { this.description = description; }
}
