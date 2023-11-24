package com.ttps.cuentasclaras.dto;

import java.time.LocalDate;
import java.util.List;

import com.ttps.cuentasclaras.model.DivisionEnum;
import com.ttps.cuentasclaras.model.RecurrentEnum;

public class SpendingDTO {
	private Integer id;
	private String name;
	private String description;
	private Double totalAmount;
	private LocalDate createdAt;
	private LocalDate endingDate;
	private String proofOfPayment;
	private RecurrentEnum recurrence;
	private DivisionEnum division;
	private UserDTO owner;
	private SpendingCategoryDTO category;
	private GroupDTO group;
	private List<SpendingUserDTO> spendingMembers;

	public SpendingDTO(Integer id, String name, String description, Double totalAmount, LocalDate createdAt,
			LocalDate endingDate, String proofOfPayment, RecurrentEnum recurrence, DivisionEnum division,
			UserDTO owner, SpendingCategoryDTO category, GroupDTO group, List<SpendingUserDTO> spendingMembers) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.totalAmount = totalAmount;
		this.createdAt = createdAt;
		this.endingDate = endingDate;
		this.proofOfPayment = proofOfPayment;
		this.recurrence = recurrence;
		this.division = division;
		this.owner = owner;
		this.category = category;
		this.group = group;
		this.spendingMembers = spendingMembers;
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

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
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

	public UserDTO getOwner() {
		return owner;
	}

	public void setOwner(UserDTO owner) {
		this.owner = owner;
	}

	public SpendingCategoryDTO getCategory() {
		return category;
	}

	public void setCategory(SpendingCategoryDTO category) {
		this.category = category;
	}

	public GroupDTO getGroup() {
		return group;
	}

	public void setGroup(GroupDTO group) {
		this.group = group;
	}

	public List<SpendingUserDTO> getSpendingMembers() {
		return spendingMembers;
	}

	public void setSpendingMembers(List<SpendingUserDTO> spendingMembers) {
		this.spendingMembers = spendingMembers;
	}

}
