package com.bank.manager.beans;

import java.util.Date;
import java.util.Hashtable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Tache {
	
	static 
	{
		String URGENT = "URGENT";
		String BAS = "BAS";
		String MOYEN = "MOYEN";
		String ELEVE = "ELEVE";
		
		Hashtable<String, Integer> optionsEtats = new Hashtable<String, Integer>();
		
		optionsEtats.put(URGENT, 1);
		optionsEtats.put(ELEVE, 2);
		optionsEtats.put(MOYEN, 3);
		optionsEtats.put(BAS, 4);
	}
	
	{
		this.dateLancement = new Date();
		this.ferme= false;
	}
	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	private String contenuTache;
	private Date dateLancement;
	private Date dateAuPlutard;
	
	@ManyToOne(targetEntity=Employee.class)
	private Employee lanceurTicket;
	
	@ManyToOne(targetEntity=Employee.class)
	private Employee receveurTicket;
	
	private Boolean ferme;
	private String etat;
	public  String getContenuTache() {
		return contenuTache;
	}
	public void setContenuTache(String contenuTache) {
		this.contenuTache = contenuTache;
	}
	public Date getDateLancement() {
		return dateLancement;
	}
	public void setDateLancement(Date dateLancement) {
		this.dateLancement = dateLancement;
	}
	public Date getDateAuPlutard() {
		return dateAuPlutard;
	}
	public void setDateAuPlutard(Date dateAuPlutard) {
		this.dateAuPlutard = dateAuPlutard;
	}
	public Employee getLanceurTicket() {
		return lanceurTicket;
	}
	public void setLanceurTicket(Employee lanceurTicket) {
		this.lanceurTicket = lanceurTicket;
	}
	public Employee getReceveurTicket() {
		return receveurTicket;
	}
	public void setReceveurTicket(Employee receveurTicket) {
		this.receveurTicket = receveurTicket;
	}
	public Boolean getFerme() {
		return ferme;
	}
	public void setFerme(Boolean ferme) {
		this.ferme = ferme;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public Tache() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tache(String contenuTache, Date dateAuPlutard,
			Employee lanceurTicket, Employee receveurTicket, String etat) {
		super();
		this.contenuTache = contenuTache;
		this.dateAuPlutard = dateAuPlutard;
		this.lanceurTicket = lanceurTicket;
		this.receveurTicket = receveurTicket;
		this.etat = etat;
	}
	
	
}
