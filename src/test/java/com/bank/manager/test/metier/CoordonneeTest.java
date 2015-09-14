package com.bank.manager.test.metier;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.metier.IManagerMetier;




@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"file:src/main/resources/application-context.xml"})
public class CoordonneeTest {
	
	@Autowired
	private IManagerMetier metier;
	
	public void setMetier(IManagerMetier metier)
	{
		this.metier = metier;
	}
	
	@Parameter
	private Adresse completeAddress = new Adresse("VILLE", "QUARTIER", Integer.MAX_VALUE, Integer.MAX_VALUE);
	
	@Parameter
	private Coordonnee badFields = new Coordonnee("", "", Integer.MIN_VALUE, "", completeAddress);
	
	@Parameter
	private Coordonnee incompleteCoordonnee = new Coordonnee("JOHN", "DOE", 20, "john.doe@mail.com");
	
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void illegalArgumentTest()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("LES INFORMATION DOIVENT ETRE CORRECTES");
		metier.addCoordonnee(badFields);
	}
	
	@Test
	public void nullPointerException()
	{
		exception.expectMessage("NULL REFERENCE NOT ACCEPTED");
		exception.expect(NullPointerException.class);
		metier.addCoordonnee(incompleteCoordonnee);
	}
	
	@Test
	public void returnedValueAfterRightParametersNotNull()
	{
		incompleteCoordonnee.setAdresse(completeAddress);
		Assert.assertNotNull("THE RETURNED REFERENCE SHOULD NOT BE NULL", metier.
				addCoordonnee(incompleteCoordonnee));
	}
	
	

}
