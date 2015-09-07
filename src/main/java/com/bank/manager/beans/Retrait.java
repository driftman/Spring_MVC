package com.bank.manager.beans;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
@Entity
@DiscriminatorValue("RE")
public class Retrait extends Operation {

	public Retrait() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Retrait(double somme, Compte compte, Date date, Employee employee) {
		super(somme, compte, date, employee);
		// TODO Auto-generated constructor stub
	}

}
