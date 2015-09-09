package com.bank.manager.models;

import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Situation;

public class ClientModel {
	
	{
		this.situation = new Situation();
		this.coordonnee = new Coordonnee();
		this.adresse = new Adresse();
	}
	
	
	private Situation situation;
	private Coordonnee coordonnee;
	private Adresse adresse;
	private Employee employee;
	
	public Adresse getAdresse() {
		return adresse;
	}
	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	public Situation getSituation() {
		return situation;
	}
	public void setSituation(Situation situation) {
		this.situation = situation;
	}
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}
	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	@Override
	public String toString()
	{
		return "CLIENT MODEL"+this.getAdresse();
	}
	
	
	
}
