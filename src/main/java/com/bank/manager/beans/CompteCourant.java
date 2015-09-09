package com.bank.manager.beans;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("CC")
public class CompteCourant extends Compte{

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(String codeCompte, 
			double soldeDepart) {
		super(codeCompte, soldeDepart);
		// TODO Auto-generated constructor stub
	}

}
