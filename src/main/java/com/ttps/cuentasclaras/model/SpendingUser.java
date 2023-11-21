package com.ttps.cuentasclaras.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SpendingUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private User user;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "spending_id")
	private Spending spending;

	@Column
	private Double amount;
	
	@Column
	private LocalDateTime created_at;
	
	@Column
	private LocalDateTime updated_at;

	public SpendingUser() {
		super();
	}

	public SpendingUser(Integer id, User user, Spending spending, Double amount) {
		super();
		this.id = id;
		this.user = user;
		this.spending = spending;
		this.amount = amount;
		this.created_at = LocalDateTime.now();
		this.updated_at = null;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Spending getSpending() {
		return spending;
	}

	public void setSpending(Spending spending) {
		this.spending = spending;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
