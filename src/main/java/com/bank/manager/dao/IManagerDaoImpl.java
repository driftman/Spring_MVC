package com.bank.manager.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Tache;

public class IManagerDaoImpl implements IManagerDao{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Adresse addAddress(Adresse adresse) {
		// TODO Auto-generated method stub
		if(adresse == null)
			throw new RuntimeException("NULL REFERENCE NOT ACCEPTED");
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
		if(coordonnee == null)
			throw new RuntimeException("NULL REFERENCE NOT ACCEPTED");
		else
		{
			em.persist(coordonnee);
			em.flush();
			return coordonnee;
		}
	}

	@Override
	public Employee addEmployee(Employee employee, Employee sup) {
		// TODO Auto-generated method stub
		if(employee == null || sup == null)
			throw new RuntimeException("NULL REFERENCE NOT ACCEPTED");
		else
		{
			employee.setSuperieurHierarchique(sup);
			em.persist(employee);
			em.flush();
			return employee;
		}
	}

	@Override
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT e FROM Employe e WHERE e.id = :id");
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
		Query query = em.createQuery("SELECT e FROM Employe e LEFT JOIN e.coordonne as c"
				+ " WHERE c.nom like :mc OR c.prenom like :mc");
		query.setParameter("mc", mc);
		List<Employee> liste = (List<Employee>)query.getResultList();
		if(liste==null || liste.size() == 0)
			throw new RuntimeException("NO EMPLOYEE MATCH THE CRITERIA YOU SETTED <"+mc+">");
		return liste;
	}

	@Override
	public Client addClient(Client client, Employee employee) {
		// TODO Auto-generated method stub
		if(client == null || employee == null)
			throw new RuntimeException(
					"REMEMBER THE CLIENT AND THE EMPLOYEE SHOULD NOT BE NULL <CLIENT:"+
			String.valueOf(client==null)+">, <EMPLOYEE:"+String.valueOf(employee==null)+">");
		
		client.setEmployee(employee);
		em.persist(client);
		em.flush();
		return client;
	}

	@Override
	public Client getClient(Long id) {
		// TODO Auto-generated method stub
		return em.find(Client.class, id);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Client> getActiveClients() {
		// TODO Auto-generated method stub
		return (List<Client>)em.createQuery("SELECT c FROM Client c WHERE c.accepte = true").getResultList();
	}

	@Override
	public Client acceptClient(Client c) {
		// TODO Auto-generated method stub
		if(c==null)
			throw new RuntimeException("CLIENT SHOULD NOT BE NULL");
		Query q = em.createQuery("UPDATE c FROM Client c WHERE c.id = :id SET c.accepte=true");
		q.setParameter("id", c.getId());
		Long id = Long.decode(String.valueOf(q.executeUpdate()));
		Client updatedClient = em.find(Client.class, id);
		if(updatedClient==null)
			throw new RuntimeException("ERROR WHILE UPDATING ...");
		return updatedClient;
	}

	@Override
	public Compte addCompte(Compte compte, Client client, Employee employee,
			double soldeDepart) {
		// TODO Auto-generated method stub
		if(compte==null || client==null || employee==null)
			throw new RuntimeException(
					"REMEMBER THE CLIENT AND THE EMPLOYEE SHOULD NOT BE NULL "
					+ "<CLIENT:"+String.valueOf(client==null)+">, "
					+ "<EMPLOYEE:"+String.valueOf(employee==null)+">, "
					+ "<COMPTE:"+String.valueOf(compte==null)+">");
		else
		{
			compte.setClient(client);
			compte.setEmployee(employee);
			em.persist(compte);
			return compte;
		}
	}

	@Override
	public Compte getCompte(Long id) {
		// TODO Auto-generated method stub
		Compte c = em.find(Compte.class, id);
		if(c==null)
			throw new RuntimeException("COMPTE NOT FOUND !");
		return c;
	}

	@Override
	public Compte getCompteCodeCompte(String codeCompte) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT c from Compte c WHERE c.codeCompte = :codeCompte");
		query.setParameter("codeCompte", codeCompte);
		Compte compte = (Compte)query.getSingleResult();
		if(compte==null)
			throw new RuntimeException("COMPTE NOT FOUND !");
		return compte;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Compte> getCompteByClient(Client c) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT c from Compte compte LEFT JOIN c.client as client "
				+ "WHERE client.id = :id");
		query.setParameter("id", c.getId());
		List<Compte> comptes = (List<Compte>)query.getResultList();
		if(comptes == null || comptes.size() == 0)
			throw new RuntimeException("THE CLIENT WITH ID: "+c.getId()+" HAS NO COMPTE");
		return comptes;
	}

	@Override
	public Operation addOperation(Operation operation, Compte compte,
			Employee employee, double somme) {
		// TODO Auto-generated method stub
		if(operation==null || compte==null || employee==null)
			throw new RuntimeException(
					"REMEMBER THE CLIENT AND THE EMPLOYEE SHOULD NOT BE NULL "
					+ "<OPERATION:"+String.valueOf(operation==null)+">, "
					+ "<EMPLOYEE:"+String.valueOf(employee==null)+">, "
					+ "<COMPTE:"+String.valueOf(compte==null)+">");
		operation.setCompte(compte);
		operation.setEmployee(employee);
		operation.setSomme(somme);
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
		query.setParameter("id", c.getId());
		List<Operation> operations = (List<Operation>)query.getResultList();
		if(operations==null || operations.size()==0)
			throw new RuntimeException("NO OPERATION FOUND FOR THE COMPTE : "+c.getId());
		return operations;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Operation> getOperationByEmployee(Employee e) {
		// TODO Auto-generated method stub
		Query query = em.createQuery("SELECT o FROM Operation o LEFT JOIN o.employee as employee WHERE employee.id = :id");
		query.setParameter("id", e.getId());
		List<Operation> operations = (List<Operation>)query.getResultList();
		if(operations==null || operations.size()==0)
			throw new RuntimeException("NO OPERATION FOUND FOR THE EMPLOYEE : "+e.getId());
		return operations;
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
	public Compte persistCompte(Compte c) {
		// TODO Auto-generated method stub
		em.persist(c);
		return c;
	}

}
