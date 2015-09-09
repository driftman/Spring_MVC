package com.bank.manager.beans;

import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType=DiscriminatorType.STRING, name="TYPE_OPERATION", length=4)
public class Operation {
	{
		this.date = new Date();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private double somme;
	
	@ManyToOne(targetEntity=Compte.class, fetch=FetchType.LAZY)
	private Compte compte;
	
	@ManyToOne(targetEntity=Employee.class)
	private Employee employee;
	
	private Date date;
	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Operation(double somme, Compte compte, Date date, Employee employee) {
		super();
		this.somme = somme;
		this.compte = compte;
		this.date = date;
		this.employee = employee;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public double getSomme() {
		return somme;
	}
	public void setSomme(double somme) {
		this.somme = somme;
	}
	public Compte getCompte() {
		return compte;
	}
	public void setCompte(Compte compte) {
		this.compte = compte;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	
	
}
