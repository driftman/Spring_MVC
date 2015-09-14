package com.bank.manager.test.metier;

import java.util.Date;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.manager.beans.Account;
import com.bank.manager.metier.IManagerMetier;




@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/application-context.xml"})
public class AccountTest {
	@Autowired
	private IManagerMetier metier;
	
	public void setMetier(IManagerMetier metier)
	{
		this.metier = metier;
	}
	
	
	//You can change the value of username while you attempt to launch this test !
	//
	@Parameter
	private Account completeAccount = new Account("kygo","jUnit","jUnit");
	
	@Parameter
	private Account incompleteAccount = new Account("","","");
	
	
	@Rule
	public ExpectedException expectedException = ExpectedException.none();
	
	
	@Test
	public void illegalArgumentException()
	{
		expectedException.expect(IllegalArgumentException.class);
		expectedException.expectMessage("THE ACCOUNT FIELD SHOULD BE CORRECT");
		metier.addAccount(incompleteAccount);
	}
	
	@Test
	public void returnedAccountAfterPersistingNotNull()
	{
		Assert.assertNotNull(metier.addAccount(completeAccount));
	}
	
	
	@Test
	public void duplicateUserNameRuntimeExecution()
	{
		expectedException.expect(javax.persistence.PersistenceException.class);
		metier.addAccount(new Account("user", "jUnitTest", "jUnitTest"));
	}
	
	
	

}
