package com.ttps.cuentasclaras.dto;

import java.util.List;

public class InvitationContactDTO {
    private Integer senderId;

    private List<Integer> receiverListId;

    public InvitationContactDTO() {
        super();
    }

    public InvitationContactDTO(Integer senderId, List<Integer> receiverListId) {
        super();
        this.senderId = senderId;
        this.receiverListId = receiverListId;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public List<Integer> getReceiverListId() {
        return receiverListId;
    }

    public void setReceiverListId(List<Integer> receiverListId) {
        this.receiverListId = receiverListId;
    }

}
