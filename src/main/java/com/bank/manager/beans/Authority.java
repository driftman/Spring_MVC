package com.bank.manager.beans;

import java.io.Serializable;









import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;





@Entity
@SuppressWarnings("serial")
public class Authority implements Serializable{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	private String username;
	
	private String authority;
	
	private Boolean enabled;
	
	@ManyToOne(targetEntity=Account.class)
	private Account account;
	
	private transient TreeSet<String> auths = new TreeSet<String>();
	
	
	public Authority() {}
	//
	public void addAuth(String auth)
	{
		this.auths.add(auth);
	}
	public TreeSet<String> getAuths()
	{
		return this.auths;
	}
	//
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	public Authority(String username, String authority, Boolean enabled) {
		super();
		this.username = username;
		this.authority = authority;
		this.enabled = enabled;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
