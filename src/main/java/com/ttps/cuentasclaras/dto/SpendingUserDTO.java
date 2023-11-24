package com.ttps.cuentasclaras.dto;

import java.time.LocalDateTime;

public class SpendingUserDTO {
	private Integer spendingMemberId;
	private UserDTO user;
	private Double amount;
	private LocalDateTime created_at;
	private LocalDateTime updated_at;

	public SpendingUserDTO(Integer spendingMemberId, UserDTO user, Double amount, LocalDateTime created_at,
			LocalDateTime updated_at) {
		super();
		this.spendingMemberId = spendingMemberId;
		this.user = user;
		this.amount = amount;
		this.created_at = created_at;
		this.updated_at = updated_at;
	}

	public Integer getSpendingMemberId() {
		return spendingMemberId;
	}

	public void setSpendingMemberId(Integer spendingMemberId) {
		this.spendingMemberId = spendingMemberId;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public LocalDateTime getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(LocalDateTime updated_at) {
		this.updated_at = updated_at;
	}

}
