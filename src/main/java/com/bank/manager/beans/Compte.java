package com.bank.manager.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_COMPTE", discriminatorType=DiscriminatorType.STRING, length=4)
public abstract class Compte {
	
	{
		this.dateCreation = new Date();
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String codeCompte;
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
	public Long getId() {
		return id;
	}
	private Date dateCreation;
	
	@ManyToOne(targetEntity=Compte.class)
	private Client client;
	
	@ManyToOne(targetEntity=Employee.class)
	private Employee employee;
	
	private double soldeDepart;
	
	@OneToMany(targetEntity=Operation.class, mappedBy="compte")
	private List<Operation> operations;
	
	
	public Compte(String codeCompte, Date dateCreation, Client client, Employee employee,
			double soldeDepart) {
		super();
		this.codeCompte = codeCompte;
		this.client = client;
		this.employee = employee;
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
}
