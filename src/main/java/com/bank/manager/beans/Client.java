package com.bank.manager.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Client extends Person {
	
	@OneToMany(targetEntity=Compte.class, mappedBy="client", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Compte> comptes;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="employee")
	private Employee employee;
	
	@OneToOne(targetEntity=Situation.class, fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	@JoinColumn(name="situation", nullable=true, updatable=true)
	private Situation situation;
	
	
	
	public Situation getSituation() {
		return situation;
	}
	public void setSituation(Situation situation) {
		this.situation = situation;
	}
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
	
	public Client() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Compte> getComptes() {
		return comptes;
	}
	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}
	
	

}
