package com.bank.manager.beans;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class Account {
	{
		this.lastLogin = new Date();
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull @Column(unique=true, updatable=true)
	private String username;
	private String password;
	private Boolean enabled;
	@OneToOne(targetEntity=Person.class, mappedBy="account")
	private Person person;
	@OneToMany(targetEntity=Authority.class, mappedBy="account", fetch=FetchType.EAGER)
	private List<Authority> authorities;
	
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}
	public Account()
	{
		super();
	}
	public Account(String username, String password, String secretPass) {
		this.username = username;
		this.password = password;
		this.secretPass = secretPass;
	}
	private String secretPass;
	@Temporal(TemporalType.DATE) @Column(updatable=true, unique=false)
	private Date lastLogin;
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
	public String getSecretPass() {
		return secretPass;
	}
	public void setSecretPass(String secretPass) {
		this.secretPass = secretPass;
	}
	public Long getId() {
		return id;
	}
	
	@PrePersist
	public void updateLoginTime()
	{
		this.lastLogin = new Date();
		this.enabled = true;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setLocked(Boolean enabled) {
		this.enabled= enabled;
	}
	public Date getLastLogin() {
		return lastLogin;
	}
	@PostPersist
	public void noticeAdded()
	{
		System.out.println("Account added | modified !");
	}

}
