package com.bank.manager.test.metier;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.bank.manager.beans.Account;
import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Authority;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.CompteCourant;
import com.bank.manager.beans.CompteEpargne;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Credit;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Person;
import com.bank.manager.beans.Retrait;
import com.bank.manager.beans.Situation;
import com.bank.manager.configs.CustomUser;
import com.bank.manager.metier.IManagerMetier;

public class Test {

	public static void main(String...args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
				"classpath*:/application-context.xml"
		});
		IManagerMetier metier = (IManagerMetier)context.getBean("metierService");
		Client client = metier.getClient(4L);
//		Object object = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		System.out.println(object);
//		metier.virement("CE2", "CE1", 1L, 2);
		
//		Account account = metier.findAccountByUsername("root");
//		System.out.println(account.getUsername()+" | "+account.getPassword());
//		Person person = account.getPerson();
//		System.out.println(person);
		
				
		
		
		
		
		
		
		
		
		
		//System.out.println(metier.getEmployees());
//		
//		try {
//			metier.virement("CC1", "CE1", 1L, 162000);
//		}
//		catch(Exception ex)
//		{
//			System.out.println(ex.getMessage());
//		}
		
		//metier.versement(new Credit(), "CE2", 1L, 22000);
		
		//Compte compte = metier.getCompteCodeCompte("CEE1");
		//System.out.println(compte);
//		metier.versement(new Credit(), "CE1", 1L, 200000);
//		metier.retrait(new Retrait(), metier.getCompte("CE1"), 1L, 200);
//		
//		
//		
//		CompteEpargne ce = new CompteEpargne();
//		ce.setCodeCompte("CE2000");
//		ce.setSoldeDepart(20000);
//		metier.addCompte(ce, 2L, 1L);
		
//		 Employee employee = new Employee();
//		 Adresse adresse = new Adresse("MEKNES", "4, rue AHMED DIGHOUSSI ZITOUNE", 60000, 4);
//		 Coordonnee coordonnee = new Coordonnee("Soufiane", "ELBAZ", 23, "elbaz.soufiane92@gmail.com");
//		 Account account = new Account("admin", "admin", "admin");
//		 account.setEnabled(true);
//		 metier.addEmployee(employee,account, new String[]{"ROLE_USER","ROLE_ADMIN"}, coordonnee, adresse, null);
		
//		Client c = new Client();
//		
//		Adresse adresse = new Adresse("MEKNES", "4, rue AHMED DIGHOUSSI ZITOUNE", 60000, 4);
//		
//		Coordonnee coordonnee = new Coordonnee("JOHN", "DOE", 99, "john.doe@gmail.com");
//		
//		Account account = new Account("client1", "password", "password");
//		
//		Situation situation = new Situation("SVP",true,false,30000,false,true);
//		
//		c.setAccepte(true);
//		
//		metier.addClient(c, account, situation, coordonnee, adresse, 1L);
//		
//		 
//		 Employee employee = new Employee();
//		 Adresse adresse = new Adresse("MEKNES", "4, rue AHMED DIGHOUSSI ZITOUNE", 60000, 4);
//		 Coordonnee employeeCoordonnee = new Coordonnee("TAZI", "Kojak", 39, "bennouma@gmail.com");
//		 Coordonnee clientCoordonnee = new Coordonnee("Soufiane", "ELBAZ", 23, "elbaz.soufiane92@gmail.com");
//		 employeeCoordonnee.setAdresse(adresse);
//		 employee.setCoordonnee(employeeCoordonnee);
//		 Account account = new Account("root", "root", "root");
//		 employee.setAccount(account);
//		 metier.addEmployee(employee,account, employeeCoordonnee, adresse, null);
//		 Authority proxyObject = new Authority();
//		 proxyObject.addAuth("ROLE_USER");
//		 proxyObject.addAuth("ROLE_ADMIN");
//		 proxyObject.setUsername(account.getUsername());
//		 proxyObject.setEnabled(true);
//		 
//		 for(String auth : proxyObject.getAuths())
//		 {
//			 Authority authority = new Authority();
//			 authority.setAuthority(auth);
//			 authority.setUsername(proxyObject.getUsername());
//			 authority.setEnabled(proxyObject.getEnabled());
//			 metier.addAuthority(authority, account);
//		 }
		
		 //metier.addClient(c, situation, coordonnee, adresse, responsableDuClient);
		
		
		
		 
		 
		
		
		
//		context.close();
	}

}
