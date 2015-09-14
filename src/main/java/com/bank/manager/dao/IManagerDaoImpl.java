package com.bank.manager.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bank.manager.beans.Account;
import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Situation;
import com.bank.manager.beans.Tache;

public class IManagerDaoImpl implements IManagerDao{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Adresse addAddress(Adresse adresse) {
		// TODO Auto-generated method stub
		if(adresse == null)
			throw new NullPointerException("NULL REFERENCE NOT ACCEPTED");
		if(adresse.getCode_postale()<0 || adresse.getNumero_lieu()<0)
			throw new IllegalArgumentException("CODE POSTALE ET NUMERO LIEU DOIVENT ETRE CORRECTES");
		else
		{
			em.persist(adresse);
			em.flush();
			return adresse;
		}
	}

	@Override
	public Coordonnee addCoordonnee(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		if(coordonnee.getAdresse() == null)
			throw new NullPointerException("NULL REFERENCE NOT ACCEPTED");
		if(coordonnee.getEmail() == "" || coordonnee.getAge() < 16 || coordonnee.getNom() == "" ||
				coordonnee.getPrenom() == "")
			throw new IllegalArgumentException("LES INFORMATION DOIVENT ETRE CORRECTES");
		else
		{
			this.addAddress(coordonnee.getAdresse());
			em.persist(coordonnee);
			em.flush();
			return coordonnee;
		}
	}

	@Override
	public Employee addEmployee(Employee employee, Account account, Coordonnee coordonnee, Adresse adress, Employee sup) {
		// TODO Auto-generated method stub
		if(employee == null || coordonnee == null || adress == null)
			throw new NullPointerException("NULL REFERENCE NOT ACCEPTED");
		else
		{
			System.out.println("PHASE1");
			
			//An Employee can and can't have a superior (IT IS OPTIONAL AND DEPENDS ON THE HIERARCHY)
			if(sup!=null)
				employee.setSuperieurHierarchique(sup);
			
			coordonnee.setAdresse(adress);
			employee.setCoordonnee(this.addCoordonnee(coordonnee));
			employee.setAccount(this.addAccount(account));
			em.persist(employee);
			em.flush();
			return employee;
		}
	}

	@Override
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT e FROM Employee e WHERE e.id = :id");
		query.setParameter("id",id);
		Employee e = (Employee)query.getSingleResult();
		if(e==null)
			throw new RuntimeException("EMPLOYEE NOT FOUND !");
		return e;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> rechercherEmployeParMC(String mc) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT e FROM Employee e LEFT JOIN e.coordonnee as c LEFT JOIN e.account as a"
				+ " WHERE c.nom like :mc OR c.prenom like :mc OR a.username like :mc ");
		query.setParameter("mc", "%"+mc+"%");
		List<Employee> liste = (List<Employee>)query.getResultList();
		if(liste.size() == 0)
			throw new RuntimeException("NO EMPLOYEE MATCH THE CRITERIA YOU SETTED <"+mc+">");
		return liste;
	}

	@Override
	public Client addClient(Client client, Account account, Situation situation, Coordonnee coordonnee, Adresse adresse, Long code_employee) {
		// TODO Auto-generated method stub
		if(client == null || code_employee == null || coordonnee == null)
			throw new IllegalArgumentException(
					"REMEMBER THE CLIENT AND THE EMPLOYEE SHOULD NOT BE NULL <CLIENT:"+
			String.valueOf(client==null)+">, <EMPLOYEE:"+String.valueOf(code_employee==null)+">");
		
		Employee e = em.find(Employee.class, code_employee);
		coordonnee.setAdresse(adresse);
		client.setCoordonnee(this.addCoordonnee(coordonnee));
		client.setAccount(this.addAccount(account));
		client.setEmployee(e);
		em.persist(situation);
		client.setSituation(situation);
		em.persist(client);
		em.flush();
		return client;
	}

	@Override
	public Client getClient(Long id) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT c FROM Client c WHERE c.id = :id");
		query.setParameter("id",id);
		try
		{
			Client c = (Client)query.getSingleResult();
			return c;
		}
		catch(NoResultException ex)
		{
			throw new RuntimeException("CLIENT NOT FOUND !");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getActiveClients() {
		// TODO Auto-generated method stub
		return (List<Client>)em.createQuery("SELECT c FROM Client c WHERE c.accepte = true").getResultList();
	}

	@Override
	public Client acceptClient(Long code_client) {
		// TODO Auto-generated method stub
		if(code_client==null)
			throw new RuntimeException("CLIENT SHOULD NOT BE NULL");
		Client c = this.getClient(code_client);
		c.setAccepte(true);
		em.flush();
		return c;
	}

	@Override
	public Compte addCompte(Compte compte, Long code_client, Long code_employee) {
		// TODO Auto-generated method stub
		if(compte==null || code_client==null || code_employee==null)
			throw new IllegalArgumentException(
					"NULL PROPERTIES");
		else
		{
			Employee employee ;
			Client client ;
				client = em.find(Client.class, code_client);
				employee = em.find(Employee.class, code_employee);
				if(client==null || employee==null)
					throw new IllegalArgumentException("THE EMPLOYEE OR THE CLIENT YOU SETTED ARE NOT FOUND");
				compte.setClient(client);
				compte.setEmployee(employee);
				em.persist(compte);
				em.flush();
				return compte;
		}
	}

	@Override
	public Compte getCompte(String codeCompte) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT c FROM Compte c WHERE c.codeCompte = :id");
		query.setParameter("id", codeCompte);
		Compte c = null;
		try
		{
			c = (Compte)query.getSingleResult();
			return c;
		}catch(NoResultException e)
		{
			throw new IllegalArgumentException("COMPTE NOT FOUND");
		}
		
	}

	@Override
	public Compte getCompteCodeCompte(String codeCompte) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT c from CompteEpargne c WHERE c.codeCompte like :codeCompte");
		query.setParameter("codeCompte", codeCompte);
		Compte compte = (Compte)query.getSingleResult();
		if(compte==null)
			throw new RuntimeException("COMPTE NOT FOUND !");
		else
			System.out.println("NOT NULL COMPTE"+compte);
		return compte;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Compte> getCompteByClient(Long code_client) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT compte from Compte compte WHERE compte.client.id = :id");
		query.setParameter("id", code_client);
		List<Compte> comptes = (List<Compte>)query.getResultList();
		if(comptes == null || comptes.size() == 0)
			throw new RuntimeException("THE CLIENT WITH ID: "+code_client+" HAS NO COMPTE");
		return comptes;
	}

	@Override
	public Operation addOperation(Operation operation, Compte compte,
			Long code_employee, double somme) {
		// TODO Auto-generated method stub
		if(operation==null || compte==null || code_employee==null)
			throw new IllegalArgumentException("NULL REFERENCES ARE NOT PERMITTED");
		Employee e = em.find(Employee.class, code_employee);
		operation.setCompte(compte);
		operation.setEmployee(e);
		operation.setSomme(somme);
		System.out.println(operation+" TO : "+compte.getCodeCompte());
		em.persist(operation);
		em.flush();
		return operation;
	}

	@Override
	public Operation getOperation(Long id) {
		// TODO Auto-generated method stub
		Operation operation = em.find(Operation.class, id);
		if(operation==null)
			throw new RuntimeException("NO OPERATION FOUND");
		return operation;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> getOperationByCompte(Compte c) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT o FROM Operation o LEFT JOIN o.compte as compte WHERE compte.id = :id");
		query.setParameter("id", c.getCodeCompte());
		try{
			List<Operation> operations = query.getResultList();
			return operations;
		}catch(NoResultException ex)
		{
			throw new RuntimeException("NO OPERATION FOUND FOR THIS COMPTE");
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> getOperationByEmployee(Employee e) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT o FROM Operation o LEFT JOIN o.employee as employee WHERE employee.id = :id");
		query.setParameter("id", e.getId());
		
		try{
			List<Operation> operations = query.getResultList();
			return operations;
		}catch(NoResultException ex)
		{
			throw new RuntimeException("NO OPERATION FOUND FOR THIS EMPLOYEE");
		}
		
	}

	@Override
	public Tache addNewTicket(Tache task, Long employee1, Long employee2, Date releaseDate) {
		// TODO Auto-generated method stub
		Employee e1, e2;
		if(task==null || employee1==null || employee2==null || releaseDate==null)
			throw new RuntimeException(
					"REMEMBER THE CLIENT AND THE EMPLOYEE SHOULD NOT BE NULL "
					+ "<TACHE :"+String.valueOf(task==null)+">, "
					+ "<EMPLOYEE 1:"+String.valueOf(employee1==null)+">, "
					+ "<EMPLOYEE 2:"+String.valueOf(employee2==null)+">, "
					+ "<RELEASE DATE:"+String.valueOf(releaseDate==null)+">");
		try {
			e1 = this.getEmployee(employee1);
			e2 = this.getEmployee(employee2);
		}catch(Exception e)
		{
			throw new RuntimeException(e.getMessage());
		}
		task.setLanceurTicket(e1);
		task.setReceveurTicket(e2);
		em.persist(task);
		em.flush();
		return task;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Compte> getComptes() {
		// TODO Auto-generated method stub
		return (List<Compte>)em.createQuery("SELECT c FROM Compte c").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> getOperations() {
		// TODO Auto-generated method stub
		List<Operation> operations = (List<Operation>)em.createQuery("SELECT o FROM Operation o").getResultList();
		if(operations == null || operations.size() == 0)
			throw new RuntimeException("There is no operations");
		return operations;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Tache> getTickets() {
		// TODO Auto-generated method stub
		return (List<Tache>)em.createQuery("SELECT t FROM Tache t").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Client> getClients() {
		// TODO Auto-generated method stub
		return (List<Client>)em.createQuery("SELECT c FROM Client c").getResultList();
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		List<Employee> employees=  (List<Employee>)em.createQuery("SELECT e FROM Employee e").getResultList();
		if(employees.size()==0)
			throw new RuntimeException("FOREVER ALONE BUDDY SORRY FOR YOU!");
		return employees;
	}

	@Override
	public List<Compte> getCompteByEmployee(Long code_employee) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT compte from Compte compte WHERE compte.employee.id = :id");
		query.setParameter("id", code_employee);
		List<Compte> comptes = (List<Compte>)query.getResultList();
		if(comptes == null || comptes.size() == 0)
			throw new RuntimeException("THE EMPLOYEE WITH ID: "+code_employee+" HAS NO COMPTE");
		return comptes;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Compte> getComptesWithMC(String mc) {
		// TODO Auto-generated method stub
		List<Compte> comptes =  (List<Compte>)em.createQuery("SELECT c FROM Compte c WHERE c.codeCompte like :mc")
				.setParameter("mc",	"%"+mc+"%")
				.getResultList();
		if(comptes.size()==0)
			throw new RuntimeException("NO COMPTE FOUND FOR THIS KEYWORD !");
		return comptes;
	}

	@Override
	public Account addAccount(Account account) {
		// TODO Auto-generated method stub
		if(account==null || account.getUsername().equals("") || account.getPassword().equals("") 
				|| account.getSecretPass().equals(""))
			throw new IllegalArgumentException("THE ACCOUNT FIELD SHOULD BE CORRECT");
		em.persist(account);
		em.flush();
		return account;
			
	}

	@Override
	public void deleteEmployee(Long code_employee) {
		// TODO Auto-generated method stub
		Employee e = this.getEmployee(code_employee);
		em.remove(e);
		em.flush();
		
	}

	@Override
	public void deleteClient(Long code_client) {
		// TODO Auto-generated method stub
		Client client = this.getClient(code_client);
		em.remove(client);
		em.flush();
	}

	@Override
	public void deleteCompte(String code_compte) {
		// TODO Auto-generated method stub
		Compte c = this.getCompte(code_compte);
		em.remove(c);
		em.flush();
	}

}
