package com.bank.manager.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Adresse {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String ville;
	private String quartier;
	private Integer code_postale;
	private Integer numero_lieu;
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getQuartier() {
		return quartier;
	}
	public void setQuartier(String quartier) {
		this.quartier = quartier;
	}
	public Integer getCode_postale() {
		return code_postale;
	}
	public void setCode_postale(Integer code_postale) {
		this.code_postale = code_postale;
	}
	public Integer getNumero_lieu() {
		return numero_lieu;
	}
	public void setNumero_lieu(Integer numero_lieu) {
		this.numero_lieu = numero_lieu;
	}
	public Adresse(String ville, String quartier, Integer code_postale,
			Integer numero_lieu) {
		super();
		this.ville = ville;
		this.quartier = quartier;
		this.code_postale = code_postale;
		this.numero_lieu = numero_lieu;
	}
	public Adresse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	
	public String toString() {
		return "Hello Adresse:"+this.getClass().getSimpleName();
	}
	
}
