package com.ttps.cuentasclaras.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Spending {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String name;

	@Column
	private String description;

	// Monto total del gasto
	@Column
	private Double totalAmount;

	@Column
	private LocalDate createdAt;

	@Column
	private LocalDate endingDate;

	@Column
	private String proofOfPayment;

	@Enumerated(EnumType.STRING)
	private RecurrentEnum recurrence;

	@Enumerated(EnumType.STRING)
	private DivisionEnum division;

	@ManyToOne
	@JoinColumn(name = "owner_user_id", nullable = false)
	private User owner;

	@ManyToOne
	@JoinColumn(name = "spending_category_id", nullable = false)
	private SpendingCategory spendingCategory;

	// Si es un NULL significa que este gasto no es grupal sino que es solo con una
	// persona
	@ManyToOne
	@JoinColumn(name = "group_id", nullable = true)
	private Group group;

	// "Un gasto esta compuesto por varios usuarios y un usuario puede tener muchos
	// gastos"
	// Si quiero saber cuanto es el monto que aporto cada usuario debo guardar esos
	// datos en una nueva entidad.
	// No alcanza solo con una lista de usuarios (integrantes del gasto) por si solo
	@OneToMany(mappedBy = "spending", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<SpendingUser> users;

	// Constructor para un gasto grupal
	public Spending(String name, String description, Double totalAmount, LocalDate createdAt, LocalDate endingDate,
			String proofOfPayment, RecurrentEnum recurrence, DivisionEnum division, User owner,
			SpendingCategory spendingCategory, Group group) {
		super();
		this.name = name;
		this.description = description;
		this.totalAmount = totalAmount;
		this.createdAt = createdAt;
		this.endingDate = endingDate;
		this.proofOfPayment = proofOfPayment;
		this.recurrence = recurrence;
		this.division = division;
		this.owner = owner;
		this.spendingCategory = spendingCategory;
		this.group = group;
		this.users = new HashSet<>();

	}

	// Constructor sin grupo (Es un gasto individual, es decir, gasto directo con
	// otra persona)
	public Spending(String name, String description, Double totalAmount, LocalDate createdAt, LocalDate endingDate,
			String proofOfPayment, RecurrentEnum recurrence, DivisionEnum division, User owner,
			SpendingCategory spendingCategory) {
		super();
		this.name = name;
		this.description = description;
		this.totalAmount = totalAmount;
		this.createdAt = createdAt;
		this.endingDate = endingDate;
		this.proofOfPayment = proofOfPayment;
		this.recurrence = recurrence;
		this.division = division;
		this.owner = owner;
		this.spendingCategory = spendingCategory;
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

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public SpendingCategory getSpendingCategory() {
		return spendingCategory;
	}

	public void setSpendingCategory(SpendingCategory spendingCategory) {
		this.spendingCategory = spendingCategory;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public Set<SpendingUser> getUsers() {
		return users;
	}

	public void setUsers(Set<SpendingUser> users) {
		this.users = users;
	}

}
