package com.bank.manager.beans;

public class TestingTools {

	public TestingTools()
	{
		
	}
	
	
	public int multiply(int x, int y)
	{
		if(x<0 || y<0)
		{
			throw new IllegalArgumentException("NEGATIVE PARAMS NOT PERMITTED");
		}
		return x*y;
	}
}
