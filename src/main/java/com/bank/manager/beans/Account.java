package com.bank.manager.beans;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
public class Account {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(updatable=false, unique=true)
	private Long id;
	@NotNull @Column(unique=true, updatable=true)
	private String username;
	private String password;
	
	public Account()
	{
		super();
	}
	public Account(String username, String password, String secretPass,
			Date lastLogin) {
		this.username = username;
		this.password = password;
		this.secretPass = secretPass;
		this.lastLogin = lastLogin;
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
	}
	
	@PostPersist
	public void noticeAdded()
	{
		System.out.println("Account added | modified !");
	}

}
