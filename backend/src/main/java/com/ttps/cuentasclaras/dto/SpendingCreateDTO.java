package com.ttps.cuentasclaras.dto;

import java.time.LocalDate;
import java.util.List;

import com.ttps.cuentasclaras.model.DivisionEnum;
import com.ttps.cuentasclaras.model.RecurrentEnum;

public class SpendingCreateDTO {
	private String name;
	private String description;
	private Double totalAmount;
	private LocalDate endingDate;
	private String proofOfPayment;
	private RecurrentEnum recurrence;
	private DivisionEnum division;
	private Integer groupId;
	private Integer ownerId;
	private Integer spendingCategoryId;

	// Si users = 1 --> Gasto individual (directo) // users >= 1 --> Gasto grupal
	private List<UserAmountDTO> usersWithAmount;

	public SpendingCreateDTO() {

	}

	// Constructor Gasto particular (SIN GRUPO)
	public SpendingCreateDTO(String name, String description, Double totalAmount, LocalDate endingDate,
			String proofOfPayment, RecurrentEnum recurrence, DivisionEnum division, Integer ownerId,
			Integer spendingCategoryId, List<UserAmountDTO> usersWithAmount) {
		super();
		this.name = name;
		this.description = description;
		this.totalAmount = totalAmount;
		this.endingDate = endingDate;
		this.proofOfPayment = proofOfPayment;
		this.recurrence = recurrence;
		this.division = division;
		this.ownerId = ownerId;
		this.spendingCategoryId = spendingCategoryId;
		this.usersWithAmount = usersWithAmount;
	}

	// Constructor Gasto Grupal (CON GRUPO)
	public SpendingCreateDTO(String name, String description, Double totalAmount, LocalDate endingDate,
			String proofOfPayment, RecurrentEnum recurrence, DivisionEnum division, Integer groupId, Integer ownerId,
			Integer spendingCategoryId, List<UserAmountDTO> usersWithAmount) {
		super();
		this.name = name;
		this.description = description;
		this.totalAmount = totalAmount;
		this.endingDate = endingDate;
		this.proofOfPayment = proofOfPayment;
		this.recurrence = recurrence;
		this.division = division;
		this.groupId = groupId;
		this.ownerId = ownerId;
		this.spendingCategoryId = spendingCategoryId;
		this.usersWithAmount = usersWithAmount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public LocalDate getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}

	public String getProofOfPayment() {
		return proofOfPayment;
	}

	public void setProofOfPayment(String proofOfPayment) {
		this.proofOfPayment = proofOfPayment;
	}

	public RecurrentEnum getRecurrence() {
		return recurrence;
	}

	public void setRecurrence(RecurrentEnum recurrence) {
		this.recurrence = recurrence;
	}

	public DivisionEnum getDivision() {
		return division;
	}

	public void setDivision(DivisionEnum division) {
		this.division = division;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}

	public Integer getSpendingCategoryId() {
		return spendingCategoryId;
	}

	public void setSpendingCategoryId(Integer spendingCategoryId) {
		this.spendingCategoryId = spendingCategoryId;
	}

	public List<UserAmountDTO> getUsersWithAmount() {
		return usersWithAmount;
	}

	public void setUsersWithAmount(List<UserAmountDTO> usersWithAmount) {
		this.usersWithAmount = usersWithAmount;
	}

}
