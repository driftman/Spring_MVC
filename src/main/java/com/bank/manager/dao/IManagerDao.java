package com.bank.manager.dao;

import java.util.Date;
import java.util.List;

import com.bank.manager.beans.Account;
import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Authority;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Situation;
import com.bank.manager.beans.Tache;

public interface IManagerDao {

	public Adresse addAddress(Adresse adresse);
	
	public Coordonnee addCoordonnee(Coordonnee coordonnee);
	
	public Account addAccount(Account account);
	
	public Employee addEmployee(Employee employee, Account account, Coordonnee coordonnee, Adresse adresse, Employee sup);
	public Employee getEmployee(Long id);
	public List<Employee> rechercherEmployeParMC(String mc);
	
	public Client addClient(Client client, Account account, Situation situation, Coordonnee coordonnee, Adresse adresse, Long code_employee);
	public Client getClient(Long id);
	public List<Client> getActiveClients();
	public Client acceptClient(Long code_client);
	
	public Compte addCompte(Compte compte, Long code_client, Long code_employe);
	public Compte getCompte(String codeCompte);
	public Compte getCompteCodeCompte(String codeCompte);
	public List<Compte> getComptesWithMC(String mc);
	public List<Compte> getCompteByClient(Long code_client);
	public List<Compte> getCompteByEmployee(Long code_employee);
	
	
	public Operation addOperation(Operation operation, Compte compte, Long code_employee, double montant);
	public Operation getOperation(Long id);
	
	public List<Operation> getOperationByCompte(Compte c);
	public List<Operation> getOperationByEmployee(Employee e);
	
	public Tache addNewTicket(Tache ticket, Long employee1, Long employee2, Date releaseDate);
	public List<Tache> getTickets();
	public List<Compte> getComptes();
	public List<Operation> getOperations();
	public List<Client> getClients();
	public List<Employee> getEmployees();
	
	
	public void deleteEmployee(Long code_employee);
	public void deleteClient(Long code_client);
	
	public void deleteCompte(String code_compte);
	
	public Authority addAuthority(Authority authority, Account account);
	
	public Account findAccountByUsername(String username);
	public String[] loadUserAuthorities(String username);
	
	
}
