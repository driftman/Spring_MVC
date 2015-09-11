package com.bank.manager.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.CompteEpargne;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Credit;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Retrait;
import com.bank.manager.beans.Situation;
import com.bank.manager.metier.IManagerMetier;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"file:src/main/resources/application-context.xml"})
public class CompteTest {
	
	
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
	private Account completeAccount = new Account("userX", "passwordX", "secretPassX", new Date());
	
	@Parameter
	private Situation completeSituation = new Situation("TEST", true, true, 0, true, true);
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	
	
	/*public Compte addCompte(Compte compte, Long code_client, Long code_employe);
	public Compte getCompte(String codeCompte);
	public List<Compte> getCompteByClient(Long code_client);
	public List<Compte> getCompteByEmployee(Long code_employee);
	public List<Compte> getComptesWithMC(String mc);*/
	
	
	
	
	
	
	public void initTest()
	{
		metier.addEmployee(new Employee(), 
				new Account("userY", "passwordX", "secretPassX", new Date()), 
				completeCoordonnee, completeAddress, null);
		metier.addClient(
				new Client(), 
				new Account("username", "password", "secretPass", new Date()), 
				new Situation("lettreMotivation", true,true,0,true, true), 
				new Coordonnee("nom", "prenom", 20, "e@email.com"), 
				new Adresse("ville", "quartier", 4,4), 
				1L);
	}
	@org.junit.Test
	public void illegalArgumentException1()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("THE EMPLOYEE OR THE CLIENT YOU SETTED ARE NOT FOUND");
		metier.addCompte(new CompteEpargne("CE1", 250000), 8L, 10L);
	}
	
	@org.junit.Test
	public void illegalArgumentException2()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("NULL PROPERTIES");
		metier.addCompte(new CompteEpargne("CE1", 250000), null, null);
		metier.addCompte(null, 2L, null);
		metier.addCompte(null, null, 1L);
	}
	
	@org.junit.Test
	public void returnedNotNullAfterPersist()
	{
		Assert.assertNotNull(metier.getCompte("CE1"));
		Assert.assertNotNull(metier.getCompte("CE2"));
	}
	
	@org.junit.Test
	public void getCompteTest()
	{
		Assert.assertNotNull(metier.getCompte("CE1"));
		Assert.assertNotNull(metier.getCompte("CE1").getEmployee());
		Assert.assertNotNull(metier.getCompte("CE1").getClient());
		Assert.assertNotNull(metier.getCompte("CE1").getOperations());
		Assert.assertEquals(metier.getEmployee(1L).getCoordonnee().getNom(), 
				metier.getCompte("CE1").getEmployee().getCoordonnee().getNom());
		Assert.assertEquals(metier.getClient(2L).getCoordonnee().getNom(), 
				metier.getCompte("CE1").getClient().getCoordonnee().getNom());
		Assert.assertNotNull(metier.getCompteByClient(2L));
		Assert.assertNotNull(metier.getCompteByEmployee(1L));
		Assert.assertNotNull(metier.getComptesWithMC("CE"));
		
	}
	
	/*
	 *  public Operation addOperation(Operation operation, String code_compte, Long code_employee, double montant);
		public Operation getOperation(Long id);
		public List<Operation> getOperationByCompte(Compte c);
		public List<Operation> getOperationByEmployee(Employee e);
		
		*/
	@Test
	public void setSomeOperationToCompte()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("COMPTE NOT FOUND");
		metier.versement(new Credit(), "CEX", null, 0.0);
		
		
	}
	
	@Test
	public void gatheringOperations()
	{
		Assert.assertEquals(Double.doubleToLongBits(175000), Double.doubleToLongBits(metier.getCompte("CE1").getSoldeDepart()));
		Assert.assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(metier.getCompte("CE2").getSoldeDepart()));
		
		Assert.assertNotNull(metier.versement(new Credit(), "CE2", 1L, 25000));
		Assert.assertNotNull(metier.versement(new Credit(), "CE2", 1L, 50000));
		Assert.assertNotNull(metier.versement(new Credit(), "CE2", 1L, 100000));
		
		Assert.assertNotNull(metier.retrait(new Credit(), "CE1", 1L, 100000));
		Assert.assertNotNull(metier.retrait(new Credit(), "CE1", 1L, 50000));
		Assert.assertNotNull(metier.retrait(new Credit(), "CE1", 1L, 25000));
		
		Assert.assertEquals(Double.doubleToLongBits(175000), Double.doubleToLongBits(metier.getCompte("CE2").getSoldeDepart()));
		Assert.assertEquals(Double.doubleToLongBits(0.0), Double.doubleToLongBits(metier.getCompte("CE1").getSoldeDepart()));
		
		
	}
	
	@Test
	public void runtimeExceptionVirement()
	{
		exception.expect(RuntimeException.class);
		exception.expectMessage("PAS DE CREDIT SUFFISANT POUR : "+metier.getCompte("CE1").getClient().getCoordonnee().getNom());
		metier.virement("CE1", "CE2", 1L, 175000);
	}
	
	
	
	
	
}