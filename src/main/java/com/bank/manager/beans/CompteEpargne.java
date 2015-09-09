package com.bank.manager.beans;



import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CE")
public class CompteEpargne extends Compte{

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(String codeCompte, 
			double soldeDepart) {
		super(codeCompte, soldeDepart);
		// TODO Auto-generated constructor stub
	}

}
