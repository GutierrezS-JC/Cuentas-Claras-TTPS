package com.ttps.cuentasclaras.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(unique = true)
	private String email;

	@Column(unique = true)
	private String username;

	@Column
	private String password;

	@Column
	private String name;

	@Column
	private String lastName;

	@Column
	private String profilepicBase64;

	// Un usuario puede tener 0 o muchos grupos
	@OneToMany(mappedBy = "owner")
	private Set<Group> ownedGroups;

	// Un usuario puede tener 0 o muchos gastos
	@OneToMany(mappedBy = "owner")
	private Set<Spending> myCreatedSpendings;

	// Un usuario puede tener 0 o muchos pagos (dueño)
	@OneToMany(mappedBy = "owner")
	private Set<Payment> ownedPayments;

	@OneToMany(mappedBy = "user")
	private Set<Payment> payments;

	@ManyToMany
	@JoinTable(name = "user_contact", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "contact_id"))
	private Set<UserContact> contacts;

	@ManyToMany(mappedBy = "users")
	private Set<Group> groups;

	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SpendingUser> spendings;

	public User() {
		super();
	}

	public User(String email, String username, String password, String name, String lastName, String profilepicBase64) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.profilepicBase64 = profilepicBase64;
		this.ownedGroups = new HashSet<>();
		this.myCreatedSpendings = new HashSet<>();
		this.ownedPayments = new HashSet<>();
		this.payments = new HashSet<>();
		this.contacts = new HashSet<>();
		this.groups = new HashSet<>();
		this.spendings = new HashSet<>();
	}

	public Integer getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getName() {
		return name;
	}

	public String getLastName() {
		return lastName;
	}

	public String getProfilepicBase64() {
		return profilepicBase64;
	}

	public Set<Group> getOwnedGroups() {
		return ownedGroups;
	}

	public Set<Spending> getMyCreatedSpendings() {
		return myCreatedSpendings;
	}

	public Set<Payment> getOwnedPayments() {
		return ownedPayments;
	}

	public Set<Payment> getPayments() {
		return payments;
	}

	public Set<UserContact> getContacts() {
		return contacts;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public Set<SpendingUser> getSpendings() {
		return spendings;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setProfilepicBase64(String profilepicBase64) {
		this.profilepicBase64 = profilepicBase64;
	}

}
