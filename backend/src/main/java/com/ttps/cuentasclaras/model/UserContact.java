package com.ttps.cuentasclaras.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "user_contact")
public class UserContact {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "contact_id")
    private User contact;
    
    @Column
    private LocalDate contactSince;

	@Column
	private LocalDate timestamp;
    
	public UserContact() {
		
	}

	public UserContact(Integer id, User user, User contact, LocalDate contactSince, LocalDate timestamp) {
		super();
		this.id = id;
		this.user = user;
		this.contact = contact;
		this.contactSince = contactSince;
		this.timestamp = timestamp;
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

	public User getContact() {
		return contact;
	}

	public void setContact(User contact) {
		this.contact = contact;
	}

	public LocalDate getContactSince() {
		return contactSince;
	}

	public void setContactSince(LocalDate contactSince) {
		this.contactSince = contactSince;
	}

	public LocalDate getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDate timestamp) {
		this.timestamp = timestamp;
	}
}
