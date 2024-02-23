package com.ttps.cuentasclaras.dto;

import java.time.LocalDate;

public class UserContactResponse {
    private Integer id;
    private UserAltDTO user;
    private LocalDate contactSince;

    public UserContactResponse() {
        super();
    }

    public UserContactResponse(Integer id, UserAltDTO user, LocalDate contactSince) {
        this.id = id;
        this.user = user;
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

    public LocalDate getContactSince() {
        return contactSince;
    }

    public void setContactSince(LocalDate contactSince) {
        this.contactSince = contactSince;
    }
}
