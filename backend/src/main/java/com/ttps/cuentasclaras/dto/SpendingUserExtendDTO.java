package com.ttps.cuentasclaras.dto;

import java.time.LocalDateTime;

public class SpendingUserExtendDTO {
    private Integer spendingMemberId;
    private UserDTO user;
    private Double amount;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Integer spendingId;
    private String nameSpendingId;
    private String description;

    public SpendingUserExtendDTO() {

    }

    public SpendingUserExtendDTO(Integer spendingMemberId, UserDTO user, Double amount, LocalDateTime created_at,
            LocalDateTime updated_at, Integer spendingId, String nameSpendingId, String description) {
        super();
        this.spendingMemberId = spendingMemberId;
        this.user = user;
        this.amount = amount;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.spendingId = spendingId;
        this.nameSpendingId = nameSpendingId;
        this.description = description;
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

    public Integer getSpendingId() {
        return spendingId;
    }

    public void setSpendingId(Integer spendingId) {
        this.spendingId = spendingId;
    }

    public String getNameSpendingId() {
        return nameSpendingId;
    }

    public void setNameSpendingId(String nameSpendingId) {
        this.nameSpendingId = nameSpendingId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}

