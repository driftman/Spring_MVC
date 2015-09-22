package com.bank.manager.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Situation;

public class ClientModel {

	
	
	@NotBlank
	@Length(min=3, max=30, message="Le nom doit etre compris entre 4 et 30.")
	private String nom;
	public String getPassword_confirmation() {
		return password_confirmation;
	}
	public void setPassword_confirmation(String password_confirmation) {
		this.password_confirmation = password_confirmation;
	}
	@NotBlank
	@Length(min=3, max=30, message="Le prenom doit etre compris entre 4 et 30.")
	private String prenom;
	@NotNull
	@Range(min=18, max=99, message="Age doit etre sup à 18 ans.")
	private int age;
	@Email(message="Email incorrect")
	@NotBlank
	private String email;
	@NotBlank
	private String ville;
	@NotBlank
	private String quartier;
	@NotNull
	private int code_postale;
	@NotNull
	private int numero_lieu;
	@NotBlank
	@Length(min=5, max=30, message="Nom d'utilisateur entre 5 et 15 caractères.")
	private String username;
	@NotBlank
	@Length(min=5, max=30, message="Mot de passe entre 5 et 15 caractères.")
	private String password;
	@Length(min=5, max=30, message="Mot de passe entre 5 et 15 caractères.")
	private String password_confirmation;
	@NotBlank
	@Length(min=4, max=30, message="Le Secret Pass doit etre compris entre 4 et 30.")
	private String secret_pass;
	
	@NotBlank
	private String lettreMotivation;
	
	@NotNull
	private boolean fonctionnaire;
	@NotNull
	private boolean salaireFix;
	@NotNull
	private double salaireMensuel;
	@NotNull
	private boolean marie;
	@NotNull
	private boolean biens;
	
	
	
	
	public boolean isMarie() {
		return marie;
	}
	public void setMarie(boolean marie) {
		this.marie = marie;
	}
	public boolean isBiens() {
		return biens;
	}
	public void setBiens(boolean biens) {
		this.biens = biens;
	}
	public String getLettreMotivation() {
		return lettreMotivation;
	}
	public void setLettreMotivation(String lettreMotivation) {
		this.lettreMotivation = lettreMotivation;
	}
	public boolean isFonctionnaire() {
		return fonctionnaire;
	}
	public void setFonctionnaire(boolean fonctionnaire) {
		this.fonctionnaire = fonctionnaire;
	}
	public boolean isSalaireFix() {
		return salaireFix;
	}
	public void setSalaireFix(boolean salaireFix) {
		this.salaireFix = salaireFix;
	}
	public double getSalaireMensuel() {
		return salaireMensuel;
	}
	public void setSalaireMensuel(double salaireMensuel) {
		this.salaireMensuel = salaireMensuel;
	}
	public String getSecret_pass() {
		return secret_pass;
	}
	public void setSecret_pass(String secret_pass) {
		this.secret_pass = secret_pass;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public ClientModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
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
	public int getCode_postale() {
		return code_postale;
	}
	public void setCode_postale(int code_postale) {
		this.code_postale = code_postale;
	}
	public int getNumero_lieu() {
		return numero_lieu;
	}
	public void setNumero_lieu(int numero_lieu) {
		this.numero_lieu = numero_lieu;
	}
	
	
}
