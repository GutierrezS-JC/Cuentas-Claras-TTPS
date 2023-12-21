package com.ttps.cuentasclaras.dto;

public class UserAmountDTO {
	private Integer userId;
	private double amount;

	public UserAmountDTO(Integer userId, double amount) {
		super();
		this.userId = userId;
		this.amount = amount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
