package com.bank.manager.beans;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
@Entity
public class Employee extends Person{
	
	@OneToMany(targetEntity=Operation.class, mappedBy="employee", fetch=FetchType.LAZY)
	private List<Operation> operations;
	
	@OneToMany(targetEntity=Compte.class, mappedBy="employee", fetch=FetchType.LAZY)
	private List<Compte> comptes;
	
	
	
	@OneToOne(targetEntity=Employee.class)
	@JoinColumn(name="superior", nullable=true)
	private Employee superieurHierarchique;
	
	@OneToMany(targetEntity=Tache.class, mappedBy="lanceurTicket")
	private List<Tache> tachesLances;
	
	@OneToMany(targetEntity=Tache.class, mappedBy="receveurTicket")
	private List<Tache> tachesRealises;
	
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Employee(Coordonnee coordonnee) {
		super(coordonnee);
		// TODO Auto-generated constructor stub
	}



	public List<Tache> getTachesLances() {
		return tachesLances;
	}

	public void setTachesLances(List<Tache> tachesLances) {
		this.tachesLances = tachesLances;
	}

	public List<Tache> getTachesRealises() {
		return tachesRealises;
	}

	public void setTachesRealises(List<Tache> tachesRealises) {
		this.tachesRealises = tachesRealises;
	}

	public Employee getSuperieurHierarchique() {
		return superieurHierarchique;
	}

	public void setSuperieurHierarchique(Employee superieurHierarchique) {
		this.superieurHierarchique = superieurHierarchique;
	}

	public List<Compte> getComptes() {
		return comptes;
	}

	public void setComptes(List<Compte> comptes) {
		this.comptes = comptes;
	}

	
	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	
}
