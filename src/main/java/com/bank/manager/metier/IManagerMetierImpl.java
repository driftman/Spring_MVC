package com.bank.manager.metier;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Tache;
import com.bank.manager.dao.IManagerDao;


@Transactional
public class IManagerMetierImpl implements IManagerMetier {
	
	@Autowired
	IManagerDao dao;
	
	public void setDao(IManagerDao dao) {
		this.dao = dao;
	}

	@Override
	public Adresse addAddress(Adresse adresse) {
		// TODO Auto-generated method stub
		return dao.addAddress(adresse);
	}

	@Override
	public Coordonnee addCoordonnee(Coordonnee coordonnee) {
		// TODO Auto-generated method stub
		return dao.addCoordonnee(coordonnee);
	}

	@Override
	public Employee addEmployee(Employee employee, Employee sup) {
		// TODO Auto-generated method stub
		return dao.addEmployee(employee, sup);
	}

	@Override
	public Employee getEmployee(Long id) {
		// TODO Auto-generated method stub
		return dao.getEmployee(id);
	}

	@Override
	public List<Employee> rechercherEmployeParMC(String mc) {
		// TODO Auto-generated method stub
		return dao.rechercherEmployeParMC(mc);
	}

	@Override
	public Client addClient(Client client, Employee employee) {
		// TODO Auto-generated method stub
		return dao.addClient(client, employee);
	}

	@Override
	public Client getClient(Long id) {
		// TODO Auto-generated method stub
		return dao.getClient(id);
	}

	@Override
	public List<Client> getActiveClients() {
		// TODO Auto-generated method stub
		return dao.getActiveClients();
	}

	@Override
	public Client acceptClient(Client c) {
		// TODO Auto-generated method stub
		return dao.acceptClient(c);
	}

	@Override
	public Compte addCompte(Compte compte, Client client, Employee employee,
			double soldeDepart) {
		// TODO Auto-generated method stub
		return dao.addCompte(compte, client, employee, soldeDepart);
	}

	@Override
	public Compte getCompte(Long id) {
		// TODO Auto-generated method stub
		return dao.getCompte(id);
	}

	@Override
	public Compte getCompteCodeCompte(String codeCompte) {
		// TODO Auto-generated method stub
		return dao.getCompteCodeCompte(codeCompte);
	}

	@Override
	public List<Compte> getCompteByClient(Client c) {
		// TODO Auto-generated method stub
		return dao.getCompteByClient(c);
	}

	@Override
	public Operation Versement(Operation operation, Compte compte,
			Employee employee, double montant) {
		// TODO Auto-generated method stub
		dao.addOperation(operation, compte, employee, montant);
		Compte c = this.getCompte(compte.getId());
		c.setSoldeDepart(c.getSoldeDepart()+montant);
		this.persistCompte(c);
		return operation;
		
	}

	@Override
	public Operation Retrait(Operation operation, Compte compte,
			Employee employee, double montant) {
		// TODO Auto-generated method stub
		dao.addOperation(operation, compte, employee, montant);
		Compte c = this.getCompte(compte.getId());
		c.setSoldeDepart(c.getSoldeDepart()-montant);
		this.persistCompte(c);
		return operation;
	}

	@Override
	public Operation Virement(Operation operation, Compte compte1,
			Compte compte2, Employee employee, double montant) {
		// TODO Auto-generated method stub
		this.Retrait(operation, compte1, employee, montant);
		this.Versement(operation, compte2, employee, montant);
		return null;
	}

	@Override
	public Operation getOperation(Long id) {
		// TODO Auto-generated method stub
		return dao.getOperation(id);
	}

	@Override
	public List<Operation> getOperationByCompte(Compte c) {
		// TODO Auto-generated method stub
		return dao.getOperationByCompte(c);
	}

	@Override
	public List<Operation> getOperationByEmployee(Employee e) {
		// TODO Auto-generated method stub
		return dao.getOperationByEmployee(e);
	}

	@Override
	public Tache addNewTicket(Tache ticket, Long employee1, Long employee2,
			Date releaseDate) {
		// TODO Auto-generated method stub
		return dao.addNewTicket(ticket, employee1, employee2, releaseDate);
	}

	@Override
	public Compte persistCompte(Compte c) {
		// TODO Auto-generated method stub
		return dao.persistCompte(c);
	}

}
