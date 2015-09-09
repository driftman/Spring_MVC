package com.bank.manager.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Situation {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String lettreMotivation;
	private Boolean fonctionnaire;
	private Boolean salaireFix;
	private double salaireMensuel;
	private Boolean biens;
	private Boolean marie;
	
	
	
	
	public Long getId() {
		return id;
	}
	public Situation(String lettreMotivation,
			Boolean fonctionnaire, Boolean salaireFix, double salaireMensuel,
			Boolean biens, Boolean marie) {
		super();
		this.lettreMotivation = lettreMotivation;
		this.fonctionnaire = fonctionnaire;
		this.salaireFix = salaireFix;
		this.salaireMensuel = salaireMensuel;
		this.biens = biens;
		this.marie = marie;
	}
	public Situation() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String getLettreMotivation() {
		return lettreMotivation;
	}
	public void setLettreMotivation(String lettreMotivation) {
		this.lettreMotivation = lettreMotivation;
	}
	public Boolean getFonctionnaire() {
		return fonctionnaire;
	}
	public void setFonctionnaire(Boolean fonctionnaire) {
		this.fonctionnaire = fonctionnaire;
	}
	public Boolean getSalaireFix() {
		return salaireFix;
	}
	public void setSalaireFix(Boolean salaireFix) {
		this.salaireFix = salaireFix;
	}
	public double getSalaireMensuel() {
		return salaireMensuel;
	}
	public void setSalaireMensuel(double salaireMensuel) {
		this.salaireMensuel = salaireMensuel;
	}
	public Boolean getBiens() {
		return biens;
	}
	public void setBiens(Boolean biens) {
		this.biens = biens;
	}
	public Boolean getMarie() {
		return marie;
	}
	public void setMarie(Boolean marie) {
		this.marie = marie;
	}
	
	
}
