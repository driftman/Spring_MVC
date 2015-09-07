package com.bank.manager.beans;

public class Printer {
	
	private Client client;
	private Employee employee;
	private Operation operation;
	private Situation situation;
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	public Situation getSituation() {
		return situation;
	}
	public void setSituation(Situation situation) {
		this.situation = situation;
	}
	public Printer() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Printer(Client client, Employee employee, Operation operation,
			Situation situation) {
		super();
		this.client = client;
		this.employee = employee;
		this.operation = operation;
		this.situation = situation;
	}
	
	

}
