package com.bank.manager.test.metier;

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
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.metier.IManagerMetier;

@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"file:src/main/resources/application-context.xml"})
public class EmployeeTest {
	
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
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@org.junit.Test
	public void nullPointerException()
	{
		exception.expect(NullPointerException.class);
		exception.expectMessage("NULL REFERENCE NOT ACCEPTED");
		metier.addEmployee(null,completeAccount , 
				null, completeAddress, null);
	}
	
	@Test
	public void returnedValueNotNullException()
	{
	
		Assert.assertNotNull(metier.addEmployee(new Employee(), new Account("userY", "passwordX", "secretPassX"), 
				completeCoordonnee, completeAddress, null));
	}
	
	@Test
	public void getEmployeeNotNull()
	{
		Assert.assertNotNull(metier.getEmployee(1L));
	}
	
	@Test
	public void employeeCoordonneeNotNull()
	{
		Assert.assertNotNull(metier.getEmployee(1L).getCoordonnee());
	}
	
	@Test
	 public void findEmployeeWithMC()
	 {
		 metier.addEmployee(new Employee(), new Account("userZ", "passwordX", "secretPassX"), 
					completeCoordonnee, completeAddress, null);
		 List<Employee> employees = metier.rechercherEmployeParMC("JOHN");
		 Assert.assertTrue((employees.size() >= 1));
	 }
	
	 
	 
	 
	 @Test
	 public void noEmployeeFoundWithThisCriteria()
	 {
		 String _null = "NULL";
		 exception.expect(RuntimeException.class);
		 exception.expectMessage("NO EMPLOYEE MATCH THE CRITERIA YOU SETTED <"+_null+">");
		 metier.rechercherEmployeParMC(_null);
		 Assert.assertEquals(null, metier.rechercherEmployeParMC(_null));
	 }
	
	
	
	
}
