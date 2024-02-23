package com.ttps.cuentasclaras.dto;

import com.ttps.cuentasclaras.model.UserContact;

import java.time.LocalDate;

public class UserContactDTO {
    private Integer id;
    private UserAltDTO user;
    private UserAltDTO contact;
    private LocalDate contactSince;

    public UserContactDTO() {
        super();
    }

    public UserContactDTO(Integer id, UserAltDTO user, UserAltDTO contact, LocalDate contactSince) {
        this.id = id;
        this.user = user;
        this.contact = contact;
        this.contactSince = contactSince;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserAltDTO getUser() {
        return user;
    }

    public void setUser(UserAltDTO user) {
        this.user = user;
    }

    public UserAltDTO getContact() {
        return contact;
    }

    public void setContact(UserAltDTO contact) {
        this.contact = contact;
    }

    public LocalDate getContactSince() {
        return contactSince;
    }

    public void setContactSince(LocalDate contactSince) {
        this.contactSince = contactSince;
    }

}
