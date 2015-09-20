package com.bank.manager.beans;

import java.io.Serializable;
import java.util.Date;



public class SessionBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;
	private Date connectedAt;
	{
		connectedAt = new Date(System.currentTimeMillis());
	}
	
	
	public SessionBean(){}




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


	public Date getConnectedAt() {
		return connectedAt;
	}


	public void setConnectedAt(Date connectedAt) {
		this.connectedAt = connectedAt;
	}




	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	
	


}
