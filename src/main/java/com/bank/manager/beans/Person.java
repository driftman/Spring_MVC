package com.bank.manager.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public abstract class Person {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@OneToOne(targetEntity=Coordonnee.class)
	@JoinColumn(name="coordonnees", nullable=false)
	private Coordonnee coordonnee;
	@OneToOne(targetEntity=Situation.class)
	@JoinColumn(name="situation", nullable=false)
	private Situation situation;
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	public Person(Coordonnee coordonnee) {
		super();
		this.coordonnee = coordonnee;
	}
	public Coordonnee getCoordonnee() {
		return coordonnee;
	}
	public void setCoordonnee(Coordonnee coordonnee) {
		this.coordonnee = coordonnee;
	}
	
}
