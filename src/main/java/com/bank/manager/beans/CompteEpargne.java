package com.bank.manager.beans;

import java.util.Date;

public class CompteEpargne extends Compte{

	public CompteEpargne() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteEpargne(String codeCompte, Date dateCreation, Client client, Employee employee,
			double soldeDepart) {
		super(codeCompte, dateCreation, client, employee, soldeDepart);
		// TODO Auto-generated constructor stub
	}

}
