package com.bank.manager.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

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
import com.bank.manager.beans.Operation;
import com.bank.manager.beans.Person;
import com.bank.manager.beans.SessionBean;
import com.bank.manager.beans.Situation;
import com.bank.manager.configs.CustomUser;
import com.bank.manager.metier.IManagerMetier;
import com.bank.manager.models.ClientModel;
import com.bank.manager.models.CompteModel;
import com.bank.manager.models.EmployeeModel;




@Controller
@RequestMapping(value="/employee")
public class EmployeeController {
	


	@Autowired
	private IManagerMetier metier;
	@RequestMapping(value="/ajouter", method=RequestMethod.GET)
	public String ajouterEmployeeGET(Model model, HttpServletRequest request)
	{
		CustomUser user = ((CustomUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		Person p = user.getPerson();
		System.out.println(p);
		EmployeeModel employeeModel = new EmployeeModel();
		model.addAttribute("employeeModel", employeeModel);
		request.getSession().setAttribute("test", "passed");
		return "employee/add";
	}
	
	@RequestMapping(value="/ajouter", method=RequestMethod.POST)
	public ModelAndView ajouterEmployeePOST(@ModelAttribute("employeeModel") @Valid EmployeeModel employeeModel, BindingResult bindingResult, Model model)
	{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("employee/add");
		if(bindingResult.hasErrors())
		{
			mav.addObject("flashs", Arrays.asList(new String[]{"Erreur","Les entrées sont invalides ..."}));
			return mav;
		}
		
		Adresse address = new Adresse(employeeModel.getVille(), employeeModel.getQuartier(), 
				employeeModel.getCode_postale(), employeeModel.getNumero_lieu());
		Coordonnee coordonnee = new Coordonnee(employeeModel.getNom(), employeeModel.getPrenom(), 
				employeeModel.getAge(), employeeModel.getEmail());
		Account account = new Account(employeeModel.getUsername(), employeeModel.getPassword(), 
				employeeModel.getSecret_pass());
		Employee employee = new Employee();
		try
		{
			metier.addEmployee(employee, account, new String[]{"ROLE_USER", "ROLE_ADMIN"}, coordonnee, address, null);
			mav.addObject("flashs", Arrays.asList(new String[]{"Succes","Employee ajoute avec succes ..."}));
			
		}
		catch(Exception ex)
		{
			mav.addObject("flashs", Arrays.asList(new String[]{"Erreur",ex.getMessage()}));
		}
		return mav;
	}
	
	@RequestMapping(value="comptes", method=RequestMethod.GET)
	public String  getAllComptes(Model model)
	{
		try {
			model.addAttribute("comptes",metier.getCompteByEmployee(1L));
		}
		catch(Exception e)
		{
			model.addAttribute("flashs",Arrays.asList(new String[]{"Erreur",e.getMessage(),}));
		}
		return "employee/comptes";
	}
	@RequestMapping(value="comptes/search", method=RequestMethod.POST)
	public String searchCompteWithMC(@RequestParam(value="mc", defaultValue="CE2") String mc, Model model)
	{
		try
		{
			
			model.addAttribute("comptes", metier.getComptesWithMC(mc));
		}
		catch(Exception e)
		{
			model.addAttribute("flashs",Arrays.asList(new String[]{"Erreur",e.getMessage()}));
		}
		return "employee/comptes";
	}
	@RequestMapping(value="compte/add", method=RequestMethod.GET)
	public ModelAndView addCompte()
	{
		ModelAndView mav = new ModelAndView();
		mav.addObject("compteModel", new CompteModel());
		mav.setViewName("compte/add");
		return mav;
	}
	
	@RequestMapping(value="compte/add", method=RequestMethod.POST)
	public ModelAndView addComptePost(
			@ModelAttribute("compteModel") @Valid CompteModel compteModel, 
			BindingResult bindingResult)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("compte/add");
		if(bindingResult.hasErrors())
		{
			return mav;
		}
		if(!compteModel.getTypeCompte().equals("Compte Courant") 
				&&
				!compteModel.getTypeCompte().equals("Compte Epargne"))
		{
			mav.addObject("flashs", Arrays.asList(new String[]{"ERROR","Seul les paramètres 'Compte Epargne' "
					+ "et "
					+ " 'Compte Courant' . "}));
		}
		try
		{
			Client client = metier.getClient(compteModel.getOwnerId());
			String typeCompte = compteModel.getTypeCompte();
			Compte c = (typeCompte.equals("Compte Courant")) ? new CompteCourant() : new CompteEpargne();
			c.setCodeCompte((typeCompte.equals("Compte Courant")) ? 
					"CC"+c.getDateCreation().getTime() 
					: "CE"+c.getDateCreation().getTime());
			Long adminId = 
					((CustomUser)(SecurityContextHolder.getContext().getAuthentication().getPrincipal()))
					.getPerson()
					.getId();
			Compte returnedCompte = metier.addCompte(c, client.getId(), adminId);
			Operation o = metier
					.versement(new Credit(), returnedCompte.getCodeCompte(), adminId, compteModel.getAmount());
			mav.addObject("operation", o);
			mav.addObject("compte", metier.getCompte(c.getCodeCompte()));
			mav.addObject("flashs", Arrays.asList(new String[]{"SUCCESS", "Compte ajouté avec succes "
					+ "et première operation faite. "}));
			return mav;
		}
		catch(Exception e)
		{
			mav.addObject("flashs", Arrays.asList(new String[]{"ERROR", e.getMessage()}));
			return mav;
		}
	}
	
	@RequestMapping(value="comptes/show/{code_compte}", method=RequestMethod.GET)
	public String detailsCompte(@PathVariable String code_compte, Model model)
	{
		try
		{
			model.addAttribute("compte", metier.getCompte(code_compte));
		}
		catch(Exception e)
		{
			model.addAttribute("flashs",Arrays.asList(new String[]{"ERROR", e.getMessage()}));
		}
		return "compte/details";
	}
	
	
	@RequestMapping(value="client/demande", method=RequestMethod.GET)
	public String ajouterDemandeGET(Model model)
	{
		ClientModel clientModel = new ClientModel();
		model.addAttribute("clientModel", clientModel);
		return "client/add";
	}
	
	@RequestMapping(value="client/demande", method=RequestMethod.POST)
	public ModelAndView ajouterDemandePOST(@ModelAttribute("clientModel") @Valid ClientModel clientModel, BindingResult bindingResult, Model model)
	{
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/add");
		if(bindingResult.hasErrors())
		{
			mav.addObject("flashs", Arrays.asList(new String[]{"Erreur","Veuillez renseigner les bon coordonnees"}));
			return mav;
		}
		Adresse address = new Adresse(clientModel.getVille(), clientModel.getQuartier(), 
				clientModel.getCode_postale(), clientModel.getNumero_lieu());
		Coordonnee coordonnee = new Coordonnee(clientModel.getNom(), clientModel.getPrenom(), 
				clientModel.getAge(), clientModel.getEmail());
		Account account = new Account(clientModel.getUsername(), clientModel.getPassword(), 
				clientModel.getSecret_pass());
		Situation situation = new Situation(clientModel.getLettreMotivation(), 
				clientModel.isFonctionnaire(), clientModel.isSalaireFix(), clientModel.getSalaireMensuel(), clientModel.isBiens(), 
				clientModel.isMarie());
		Client client = new Client();
		try
		{
			metier.addClient(
					client, 
					account, 
					situation, 
					coordonnee, 
					address, 
					((CustomUser)SecurityContextHolder
							.getContext()
							.getAuthentication()
							.getPrincipal())
								.getPerson()
									.getId());
			mav.addObject("flashs", Arrays.asList(new String[]{"Succes","Utilisateur ajouté avec succes"}));
		}
		catch(Exception ex)
		{
			mav.addObject("flashs", new String[]{"Erreur", ex.getMessage()});
		}
			
		System.out.println(clientModel);
		return mav;
	}
	

}
