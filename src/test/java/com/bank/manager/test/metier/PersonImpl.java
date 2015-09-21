package com.bank.manager.test.metier;

public class PersonImpl implements IPerson {
	
	
	private String name;
	
	public PersonImpl()
	{
		
	}
	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public void sayHi() {
		// TODO Auto-generated method stub
		System.out.println(this.getName());
	}
	public String getName() {
		return name;
	}
	
	

}
