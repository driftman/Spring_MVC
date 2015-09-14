package com.bank.manager.test.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CosNaming.IstringHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultHandler;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;






import com.bank.manager.models.ClientModel;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.hamcrest.Matchers.*;


@RunWith(value=SpringJUnit4ClassRunner.class)
@ContextConfiguration(value=
{
		"file:src/main/resources/application-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"
		})
@WebAppConfiguration
public class ClientControllerTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mock;
	
	@Before
	public void init()
	{
		this.mock = MockMvcBuilders.webAppContextSetup(context).build();
	}
	
	@Test
	public void Test_Ajouter_Demande_GET() throws Exception
	{
		mock.perform(get("/client/demande"))
		.andExpect(status().isOk())
		.andExpect(view().name("client/add"))
		.andExpect(forwardedUrl("/WEB-INF/views/client/add.jsp"))
		.andExpect(model().attributeExists("clientModel"));
	}
	
	
	@Test
	public void Test_Ajouter_Demande_POST() throws Exception
	{
		mock.perform(post("/client/demande").
				param("nom", "JOHN").
				param("prenom", "DOE").
				param("age", "23").
				param("email", "JOHN@DOE.com").
				param("ville", "CASA").
				param("quartier", "BOURGOGNE").
				param("code_postale", "50000").
				param("numero_lieu", "4").
				param("username", "username").
				param("password", "username").
				param("secret_pass", "username").
				param("lettreMotivation", "Acceptez moi !").
				param("fonctionnaire", "true").
				param("salaireFix", "true").
				param("salaireMensuel", "23000").
				param("marie", "true").
				param("biens", "true").
				contentType(MediaType.APPLICATION_FORM_URLENCODED).
				sessionAttr("clientModel", new ClientModel())
				)
		.andExpect(status().isOk())
		.andExpect(view().name("client/add"))
		.andExpect(forwardedUrl("/WEB-INF/views/client/add.jsp"))
		.andExpect(model().attributeExists("clientModel"))
		.andExpect(model().attributeHasNoErrors("clientModel"))
		.andExpect(model().attribute("clientModel", hasProperty("nom", is("JOHN"))))
		.andExpect(model().attribute("clientModel", hasProperty("prenom", is("DOE"))))
		.andExpect(model().attribute("clientModel", hasProperty("marie", is(true))))
		.andExpect(model().attribute("clientModel", hasProperty("biens", is(true))))
		.andExpect(model().attribute("clientModel", hasProperty("salaireFix", is(true))))
		.andExpect(model().attribute("clientModel", hasProperty("code_postale", is(50000))))
		.andExpect(model().attribute("clientModel", hasProperty("salaireMensuel", is(Double.doubleToLongBits(23000)))))
		;
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
