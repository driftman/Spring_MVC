package com.bank.manager.beans;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("CR")
public class Credit extends Operation {

	public Credit() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Credit(double somme, Compte compte, Date date, Employee employee) {
		super(somme, compte, date, employee);
		// TODO Auto-generated constructor stub
	}

}
