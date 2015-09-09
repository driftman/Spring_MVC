package com.bank.manager.metier;

import java.util.Date;
import java.util.List;

import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Situation;
import com.bank.manager.beans.Tache;

public interface IManagerMetier {
public Adresse addAddress(Adresse adresse);
	
	public Coordonnee addCoordonnee(Coordonnee coordonnee);
	
	public Employee addEmployee(Employee employee, Coordonnee coordonnee, Adresse adresse, Employee sup);
	public Employee getEmployee(Long id);
	public List<Employee> rechercherEmployeParMC(String mc);
	
	public Client addClient(Client client, Situation situation, Coordonnee coordonnee, Adresse adresse, Employee employee);
	public Client getClient(Long id);
	public List<Client> getActiveClients();
	public Client acceptClient(Client c);
	
	public Compte persistCompte(Compte c);
	public Compte addCompte(Compte compte, Long code_client, Long code_employe);
	public Compte getCompte(String codeCompte);
	public Compte getCompteCodeCompte(String codeCompte);
	public List<Compte> getCompteByClient(Client c);
	
	public Operation versement(Operation operation, String code_compte, Long code_employee, double montant);
	public Operation retrait(Operation operation, String code_compte, Long code_employee, double montant);
	public Operation virement(String code_compte1, String code_compte2, Long code_employee, double montant);
	
	public Operation getOperation(Long id);
	public List<Operation> getOperationByCompte(Compte c);
	public List<Operation> getOperationByEmployee(Employee e);
	
	public Tache addNewTicket(Tache ticket, Long employee1, Long employee2, Date releaseDate);

}
