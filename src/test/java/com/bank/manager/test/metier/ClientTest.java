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
import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Situation;
import com.bank.manager.metier.IManagerMetier;



@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"file:src/main/resources/application-context.xml"})
public class ClientTest {
	
	
	@Autowired
	private IManagerMetier metier;
	
	
	public void setMetier(IManagerMetier metier)
	{
		this.metier = metier;
	}
	
	@Parameter
	private Adresse completeAddress = new Adresse("VILLE", "QUARTIER", Integer.MAX_VALUE, Integer.MAX_VALUE);
	
	@Parameter
	private Coordonnee completeCoordonnee = new Coordonnee("JOHN", "DOE", 20, "john.doe@mail.com");
	
	@Parameter
	private Employee employee = new Employee(completeCoordonnee);

	@Parameter
	private Account completeAccount = new Account("userX", "passwordX", "secretPassX");
	
	@Parameter
	private Situation completeSituation = new Situation("TEST", true, true, 0, true, true);
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test		
	public void illegalArgumentException()
	{
				
				exception.expect(IllegalArgumentException.class);
				exception.expectMessage("REMEMBER THE CLIENT AND THE EMPLOYEE SHOULD "
						+ "NOT BE NULL <CLIENT:true>, <EMPLOYEE:true>");
				metier.addClient(null, completeAccount, null, completeCoordonnee, completeAddress, null);
	}
	
	@Test
	public void returnedClientNotNull()
	{
		metier.addEmployee(new Employee(), 
				new Account("userY", "passwordX", "secretPassX"), 
				new String[]{"ROLE_USER", "ROLE_ADMIN"},
				completeCoordonnee, completeAddress, null);
		metier.addClient(
				new Client(), 
				new Account("username", "password", "secretPass"), 
				new Situation("lettreMotivation", true,true,0,true, true), 
				new Coordonnee("nom", "prenom", 20, "e@email.com"), 
				new Adresse("ville", "quartier", 4,4), 
				1L);
	}
	
	@Test
	public void getClientNotNull()
	{
		// Looking if a known present client will be showed
		Assert.assertNotNull(metier.getClient(2L));
		// Testing Eager fetching for Coordonnee.class
		Assert.assertNotNull(metier.getClient(2L).getCoordonnee());
		// Testing Eager fetching for Adresse.class
		Assert.assertNotNull(metier.getClient(2L).getCoordonnee().getAdresse());
		Assert.assertNotNull(metier.getClient(2L).getSituation());
		Assert.assertEquals("username", 
				metier.getClient(2L).
				getAccount().
				getUsername());
		Assert.assertEquals("password", 
				metier.getClient(2L).
				getAccount().
				getPassword());
		Assert.assertEquals("nom", 
				metier.getClient(2L).
				getCoordonnee().
				getNom());
		metier.acceptClient(2L);
		Assert.assertEquals("prenom", 
				metier.getClient(2L).
				getCoordonnee().
				getPrenom());
		Assert.assertEquals(true, metier.getClient(2L).getAccepte());
		Assert.assertEquals(1, metier.getActiveClients().size());
	}
	
	

}
