package com.bank.manager.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Client extends Person {
	
	@OneToMany(targetEntity=Compte.class, mappedBy="client")
	private List<Compte> comptes;
	
	@ManyToOne(targetEntity=Employee.class)
	private Employee createurCompte;
	
	@ManyToOne
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	private Boolean accepte;
	
	public Boolean getAccepte() {
		return accepte;
	}
	public void setAccepte(Boolean accepte) {
		this.accepte = accepte;
	}
	public Employee getCreateurCompte() {
		return createurCompte;
	}
	public void setCreateurCompte(Employee createurCompte) {
		this.createurCompte = createurCompte;
	}
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Client(Coordonnee coordonnee, Employee createurCompte) {
		super(coordonnee);
		this.createurCompte = createurCompte;
		// TODO Auto-generated constructor stub
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

}
