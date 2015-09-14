package com.bank.manager.test.metier;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bank.manager.beans.Account;
import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.CompteCourant;
import com.bank.manager.beans.CompteEpargne;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Credit;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Retrait;
import com.bank.manager.beans.Situation;
import com.bank.manager.metier.IManagerMetier;

public class Test {

	public static void main(String...args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{
				"classpath*:/application-context.xml"
		});
		IManagerMetier metier = (IManagerMetier)context.getBean("metier");
		//System.out.println(metier.getEmployees());
//		
//		try {
//			metier.virement("CC1", "CE1", 1L, 162000);
//		}
//		catch(Exception ex)
//		{
//			System.out.println(ex.getMessage());
//		}
		metier.virement(metier.getCompte("CE2"), metier.getCompte("CE1"), 1L, 2000);
		//Compte compte = metier.getCompteCodeCompte("CEE1");
		//System.out.println(compte);
//		metier.versement(new Credit(), "CE1", 1L, 200000);
//		metier.retrait(new Retrait(), metier.getCompte("CE1"), 1L, 200);
//		
//		
////		
//		CompteEpargne ce = new CompteEpargne();
//		ce.setCodeCompte("CE2");
//		ce.setSoldeDepart(20000);
//		metier.addCompte(ce, 2L, 1L);
		
//		 Employee employee = new Employee();
//		 Adresse adresse = new Adresse("MEKNES", "4, rue AHMED DIGHOUSSI ZITOUNE", 60000, 4);
//		 Coordonnee coordonnee = new Coordonnee("Soufiane", "ELBAZ", 23, "elbaz.soufiane92@gmail.com");
//		 metier.addEmployee(employee, coordonnee, adresse, metier.getEmployee(2L));
		
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
//		metier.addClient(c, account, situation, coordonnee, adresse, 1L);
//		
//		 
//		 Employee employee = new Employee();
//		 Adresse adresse = new Adresse("MEKNES", "4, rue AHMED DIGHOUSSI ZITOUNE", 60000, 4);
//		 Coordonnee coordonnee = new Coordonnee("Soufiane", "ELBAZ", 23, "elbaz.soufiane92@gmail.com");
//		 coordonnee.setAdresse(adresse);
//		 employee.setCoordonnee(coordonnee);
//		 Account account = new Account("admin", "password", "password");
//		 employee.setAccount(account);
//		 metier.addEmployee(employee,account, coordonnee, adresse, null);
		
		//metier.addClient(c, situation, coordonnee, adresse, responsableDuClient);
		
		
		
		 
		 
		
		
		
//		context.close();
	}

}
