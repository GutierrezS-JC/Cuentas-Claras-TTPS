package com.ttps.cuentasclaras.dto;

import java.time.LocalDate;

public class InvitationUserContactDTO {
    private Integer id;
    private LocalDate timestamp;
    private LocalDate contactSince;
    private UserAltDTO senderUser;
    private UserAltDTO contactUser;

    public InvitationUserContactDTO() {
        super();
    }

    public InvitationUserContactDTO(Integer id, LocalDate timestamp, LocalDate contactSince,
                                    UserAltDTO senderUser, UserAltDTO contactUser) {
        super();
        this.id = id;
        this.timestamp = timestamp;
        this.contactSince = contactSince;
        this.senderUser = senderUser;
        this.contactUser = contactUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public LocalDate getContactSince() {
        return contactSince;
    }

    public void setContactSince(LocalDate contactSince) {
        this.contactSince = contactSince;
    }

    public UserAltDTO getSenderUser() {
        return senderUser;
    }

    public void setSenderUser(UserAltDTO senderUser) {
        this.senderUser = senderUser;
    }

    public UserAltDTO getContactUser() {
        return contactUser;
    }

    public void setContactUser(UserAltDTO contactUser) {
        this.contactUser = contactUser;
    }
}
