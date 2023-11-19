package com.ttps.cuentasclaras.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "grupos")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private Double totalBalance;

	@ManyToOne
	@JoinColumn(name = "owner_user_id", nullable = false)
	private User owner;

	// Usuarios que conforman el grupo
	@ManyToMany(mappedBy = "groups")
	private Set<User> users;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private GroupCategory groupCategory;

	@OneToMany(mappedBy = "group")
	private Set<Spending> spendings;

	@OneToMany(mappedBy = "group")
	private Set<Payment> payments;

	public Group() {

	}

	public Group(String name, Double totalBalance, User owner, GroupCategory groupCategory) {
		super();
		this.name = name;
		this.totalBalance = totalBalance;
		this.owner = owner;
		this.users = new HashSet<>();
		this.groupCategory = groupCategory;
		this.spendings = new HashSet<>();
		this.payments = new HashSet<>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	public GroupCategory getGroupCategory() {
		return groupCategory;
	}

	public void setGroupCategory(GroupCategory groupCategory) {
		this.groupCategory = groupCategory;
	}

	public Set<Spending> getSpendings() {
		return spendings;
	}

	public void setSpendings(Set<Spending> spendings) {
		this.spendings = spendings;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public void setPayments(Set<Payment> payments) {
		this.payments = payments;
	}

}
