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
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Person;
import com.bank.manager.beans.SessionBean;
import com.bank.manager.configs.CustomUser;
import com.bank.manager.metier.IManagerMetier;
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
			metier.addEmployee(employee, account, coordonnee, address, null);
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
	
	@RequestMapping(value="comptes/{code_compte}", method=RequestMethod.GET)
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
	

}
