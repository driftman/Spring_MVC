package com.bank.manager.metier;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;

import com.bank.manager.beans.Account;
import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Authority;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Credit;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Retrait;
import com.bank.manager.beans.Situation;
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
	public Employee addEmployee(Employee employee, Account account, Coordonnee coordonnee, Adresse adresse, Employee sup) {
		// TODO Auto-generated method stub
		return dao.addEmployee(employee, account, coordonnee, adresse, sup);
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
	public Compte addCompte(Compte compte, Long code_client, Long code_employe) {
		return dao.addCompte(compte, code_client, code_employe);
	}

	@Override
	public Compte getCompte(String codeCompte) {
		// TODO Auto-generated method stub
		return dao.getCompte(codeCompte);
	}

	@Override
	public Compte getCompteCodeCompte(String codeCompte) {
		// TODO Auto-generated method stub
		return dao.getCompteCodeCompte(codeCompte);
	}

	@Override
	public List<Compte> getCompteByClient(Long code_client) {
		// TODO Auto-generated method stub
		return dao.getCompteByClient(code_client);
	}
	@Override
	public Operation versement(Operation operation, String compte, Long code_employee, double montant) {
		// TODO Auto-generated method stub
		Compte c = this.getCompte(compte);
		c.setSoldeDepart(c.getSoldeDepart()+montant);
		dao.addOperation(operation, c, code_employee, montant);
		return operation;
		
		
	}

	@Override
	public Operation retrait(Operation operation, String code_compte, Long code_employee, double montant) {
		// TODO Auto-generated method stub
		Compte compte = this.getCompte(code_compte);
		compte.setSoldeDepart(compte.getSoldeDepart()-montant);
		dao.addOperation(operation, compte, code_employee, montant);
		return operation;
	}

	
	@Override
	public Operation virement(String code_compte1, String code_compte2, Long code_employee, double montant) {
		this.retrait(new Retrait(), code_compte1, code_employee, montant);
		this.versement(new Credit(), code_compte2, code_employee, montant);
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
	public Client addClient(Client client, Account account, Situation situation,
			Coordonnee coordonnee, Adresse adresse, Long code_employee) {
		// TODO Auto-generated method stub
		return dao.addClient(client, account, situation, coordonnee, adresse, code_employee);
	}

	@Override
	public Client acceptClient(Long code_client) {
		// TODO Auto-generated method stub
		return dao.acceptClient(code_client);
	}

	@Override
	public List<Tache> getTickets() {
		// TODO Auto-generated method stub
		return dao.getTickets();
	}

	@Override
	public List<Compte> getComptes() {
		// TODO Auto-generated method stub
		return dao.getComptes();
	}

	@Override
	public List<Operation> getOperations() {
		// TODO Auto-generated method stub
		return dao.getOperations();
	}

	@Override
	public List<Client> getClients() {
		// TODO Auto-generated method stub
		return dao.getClients();
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		return dao.getEmployees();
	}

	@Override
	public List<Compte> getCompteByEmployee(Long code_employee) {
		// TODO Auto-generated method stub
		return dao.getCompteByEmployee(code_employee);
	}

	@Override
	public List<Compte> getComptesWithMC(String mc) {
		// TODO Auto-generated method stub
		return dao.getComptesWithMC(mc);
	}

	@Override
	public Account addAccount(Account account) {
		// TODO Auto-generated method stub
		return dao.addAccount(account);
	}

	@Override
	public void deleteEmployee(Long code_employee) {
		// TODO Auto-generated method stub
		dao.deleteEmployee(code_employee);
	}

	@Override
	public void deleteClient(Long code_client) {
		// TODO Auto-generated method stub
		dao.deleteClient(code_client);
	}

	@Override
	public void deleteCompte(String code_compte) {
		// TODO Auto-generated method stub
		dao.deleteCompte(code_compte);
	}

	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("TEST PASSED !");
	}

	@Override
	public Account findAccountByUsername(String username) {
		// TODO Auto-generated method stub
		return dao.findAccountByUsername(username);
	}

	@Override
	public String[] loadUserAuthorities(String username) {
		// TODO Auto-generated method stub
		return dao.loadUserAuthorities(username);
	}

	@Override
	public Authority addAuthority(Authority authority, Account account) {
		// TODO Auto-generated method stub
		return dao.addAuthority(authority, account);
	}
	
	

}
