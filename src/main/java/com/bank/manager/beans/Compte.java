package com.bank.manager.beans;


import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, length=4)
public abstract class Compte {
	
	{
		this.dateCreation = new Date();
	}
	
	@Id
	@Column(name="CODE_COMPTE", unique=true, updatable=false) @NotNull 
	private String codeCompte;
	
	private double soldeDepart;
	
	@Temporal(TemporalType.DATE) @Column(nullable=false) @NotNull
	private Date dateCreation;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Employee employee;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn
	private Client client;
	
	@OneToMany(targetEntity=Operation.class, mappedBy="compte", fetch=FetchType.EAGER)
	private List<Operation> operations;
	
	public String getCodeCompte() {
		return codeCompte;
	}
	public void setCodeCompte(String codeCompte) {
		this.codeCompte = codeCompte;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
	public Compte(String codeCompte, 
			double soldeDepart) {
		super();
		this.codeCompte = codeCompte;
		this.soldeDepart = soldeDepart;
	}
	public Compte() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getDateCreation() {
		return dateCreation;
	}
	public void setDateCreation(Date dateCreation) {
		this.dateCreation = dateCreation;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getSoldeDepart() {
		return soldeDepart;
	}
	public void setSoldeDepart(double soldeDepart) {
		this.soldeDepart = soldeDepart;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getClass().getSimpleName();
	}
	
}
