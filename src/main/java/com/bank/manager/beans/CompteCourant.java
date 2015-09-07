package com.bank.manager.beans;

import java.util.Date;

public class CompteCourant extends Compte{

	public CompteCourant() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CompteCourant(String codeCompte, Date dateCreation, Client client, Employee employee,
			double soldeDepart) {
		super(codeCompte, dateCreation, client, employee, soldeDepart);
		// TODO Auto-generated constructor stub
	}

}
