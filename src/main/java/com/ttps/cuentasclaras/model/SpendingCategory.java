package com.ttps.cuentasclaras.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "spending_category")
public class SpendingCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String base64Image;

	@OneToMany(mappedBy = "spendingCategory")
	private Set<Spending> spendings;

	public SpendingCategory() {

	}

	public SpendingCategory(String name, String base64Image) {
		super();
		this.name = name;
		this.base64Image = base64Image;
		this.spendings = new HashSet<>();
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

	public String getBase64Image() {
		return base64Image;
	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Set<Spending> getSpendings() {
		return spendings;
	}

	public void setSpendings(Set<Spending> spendings) {
		this.spendings = spendings;
	}

}
