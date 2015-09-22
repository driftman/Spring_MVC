package com.bank.manager.models;

public class CompteModel {
	
	private String typeCompte;
	
	private Long ownerId;
	
	private double amount;


	public String getTypeCompte() {
		return typeCompte;
	}

	public void setTypeCompte(String typeCompte) {
		this.typeCompte = typeCompte;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public CompteModel() {
		super();
	}
	
	

}
