package com.ttps.cuentasclaras.dto;

import java.time.LocalDate;

import com.ttps.cuentasclaras.model.DivisionEnum;
import com.ttps.cuentasclaras.model.RecurrentEnum;

public class SpendingUpdateDTO {
	private Integer id;
	private String name;
	private String description;
	private Double totalAmount;
	private LocalDate endingDate;
	private String proofOfPayment;
	private RecurrentEnum recurrence;
	private DivisionEnum division;
	private Integer spendingCategoryId;

	public SpendingUpdateDTO(Integer id, String name, String description, Double totalAmount, LocalDate endingDate,
			String proofOfPayment, RecurrentEnum recurrence, DivisionEnum division, Integer spendingCategoryId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.totalAmount = totalAmount;
		this.endingDate = endingDate;
		this.proofOfPayment = proofOfPayment;
		this.recurrence = recurrence;
		this.division = division;
		this.spendingCategoryId = spendingCategoryId;
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

	public Integer getSpendingCategoryId() {
		return spendingCategoryId;
	}

	public void setSpendingCategoryId(Integer spendingCategoryId) {
		this.spendingCategoryId = spendingCategoryId;
	}

}
