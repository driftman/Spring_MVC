package com.bank.manager.test.metier;

import junit.framework.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bank.manager.beans.Adresse;
import com.bank.manager.metier.IManagerMetier;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/resources/application-context.xml"})
public class AddressTest {

	@Parameter
	public Adresse illegalArgumentExceptionAddressParam = new Adresse("ARBITRARY", "ARBITRARY", Integer.MIN_VALUE, Integer.MAX_VALUE);
	
	@Parameter
	public Adresse notNullAddressParam = new Adresse("ARBITRARY", "ARBITRARY", Integer.MAX_VALUE, Integer.MAX_VALUE);
	
	@Autowired
	private IManagerMetier metier;
	
	public void setMetier(IManagerMetier metier)
	{
		this.metier = metier;
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void addAddressNullPointerException()
	{
		exception.expect(NullPointerException.class);
		exception.expectMessage("NULL REFERENCE NOT ACCEPTED");
		metier.addAddress(null);
	}
	
	@Test
	public void addAddressIllegalArgumentException()
	{
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("CODE POSTALE ET NUMERO LIEU DOIVENT ETRE CORRECTES");
		metier.addAddress(illegalArgumentExceptionAddressParam);
		
	}
	@Test
	@SuppressWarnings(value="deprecation")
	public void returnedAddressNotNull()
	{
		Assert.assertNotNull(metier.addAddress(notNullAddressParam));
	}
}
