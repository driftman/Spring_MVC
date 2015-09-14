package com.bank.manager.test.controllers;


import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.bank.manager.metier.IManagerMetier;
import com.bank.manager.models.EmployeeModel;
import com.bank.manager.web.EmployeeController;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;





@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value={"file:src/main/resources/application-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class EmployeeControllerTest {
	
	
	private MockMvc mock;
	
	@Autowired
	private IManagerMetier metier;
	
	public void setMetier(IManagerMetier metier)
	{
		this.metier = metier;
	}

	
	@Autowired
	private WebApplicationContext context;
	
	
	@Autowired
	private EmployeeController employeeController;
	/*
	public void setEmployeeController(EmployeeController employeeController)
	{
		this.employeeController = employeeController;
	}
	*/
	
	
	@Before
	public void initMockito()
	{
		this.mock= MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	
	@Test
	public void employeeController_RenderingTheFormWithNoError() throws Exception
	{
		//when(methodCall);
		mock.perform(get("/employee/ajouter"))
		.andExpect(status().isOk())
		.andExpect(view().name("employee/add"))
		.andExpect(forwardedUrl("/WEB-INF/views/employee/add.jsp"))
		.andExpect(model().attributeExists("employeeModel"));
	}
/*	<tr><td>Nom : </td><td><f:input path="coordonnee.nom" type="text"/></td></tr>
	<tr><td>Prenom : </td><td><f:input path="coordonnee.prenom" type="text"/></td></tr>
	<tr><td>Age : </td><td><f:input path="coordonnee.age" type="decimal"/></td></tr>
	<tr><td>Email : </td><td><f:input path="coordonnee.email" type="email"/></td></tr>
	<tr><td>Ville : </td><td><f:input path="adresse.ville" type="text"/></td></tr>
	<tr><td>Quartier : </td><td><f:input path="adresse.quartier" type="text"/></td></tr>
	<tr><td>Code Postale : </td><td><f:input path="adresse.code_postale" type="decimal"/></td></tr>
	<tr><td>Numero Lieu : </td><td><f:input path="adresse.numero_lieu" type="decimal"/></td></tr>
	
	
	.
				
	
	*/
	@Test
	public void employeeController_POSTEmployeeADD() throws Exception
	{
		mock.perform(
				post("/employee/ajouter").
				param("nom", "Soufiane").
				param("prenom", "ELBAZ").
				param("age", "22").
				param("email", "elbaz.soufiane92@gmail.com").
				param("ville", "MEKNES").
				param("quartier", "ZITOUNE").
				param("code_postale", "50000").
				param("numero_lieu", "4").
				param("username", "soufiane").
				param("password", "soufiane").
				param("secret_pass", "secret_pass")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).
				sessionAttr("employeeModel", new EmployeeModel()).
				contentType(MediaType.APPLICATION_FORM_URLENCODED))
		
		.andExpect(status().is(200))
		.andExpect(view().name("employee/add"))
		.andExpect(forwardedUrl("/WEB-INF/views/employee/add.jsp"))
		.andExpect(model().attributeExists("employeeModel"))
		.andExpect(model().attribute("employeeModel", hasProperty("prenom", is("ELBAZ"))))
		.andExpect(model().attribute("employeeModel", hasProperty("age", is(22))))
		.andExpect(model().attribute("employeeModel", hasProperty("email", is("elbaz.soufiane92@gmail.com"))))
		.andExpect(model().attribute("employeeModel", hasProperty("ville", is("MEKNES"))))
		.andExpect(model().attribute("employeeModel", hasProperty("quartier", is("ZITOUNE"))))
		.andExpect(model().attribute("employeeModel", hasProperty("code_postale", is(50000))))
		.andExpect(model().attribute("employeeModel", hasProperty("numero_lieu", is(4))))
		.andExpect(model().attribute("employeeModel", hasProperty("username", is("soufiane"))))
		.andExpect(model().attribute("employeeModel", hasProperty("password", is("soufiane"))))
		.andExpect(model().attribute("employeeModel", hasProperty("secret_pass", is("secret_pass"))))
		.andExpect(model().attribute("flashs", hasSize(2)))
		.andExpect(model().attribute("flashs", hasItems("Succes")))
		.andExpect(forwardedUrl("/WEB-INF/views/employee/add.jsp"));
	}
	
	
	@Test
	public void employeeController_POSTEmployeeERROR() throws Exception
	{
		mock.perform(
				post("/employee/ajouter").
				param("nom", "").
				param("prenom", "ELBAZ").
				param("age", "22").
				param("email", "elbaz.soufiane92@gmail.com").
				param("ville", "MEKNES").
				param("quartier", "ZITOUNE").
				param("code_postale", "50000").
				param("numero_lieu", "4").
				param("username", "s").
				param("password", "s").
				param("secret_pass", "secret_pass")
				.contentType(MediaType.APPLICATION_FORM_URLENCODED).
				sessionAttr("employeeModel", new EmployeeModel()).
				contentType(MediaType.APPLICATION_FORM_URLENCODED))
		
		.andExpect(status().is(200))
		.andExpect(view().name("employee/add"))
		.andExpect(forwardedUrl("/WEB-INF/views/employee/add.jsp"))
		.andExpect(model().attributeExists("employeeModel", "flashs"))
		.andExpect(model().attribute("flashs", hasItems("Erreur")))
		.andExpect(model().attributeHasFieldErrors("employeeModel", "nom", "username", "password"));
	}
	
	
}
