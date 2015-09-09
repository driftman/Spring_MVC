package com.bank.manager.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.manager.beans.Compte;
import com.bank.manager.metier.IManagerMetier;
import com.bank.manager.models.EmployeeModel;




@Controller
@RequestMapping(value="/employee")
public class EmployeeController {
	
	@Autowired
	private IManagerMetier metier;
	
	@RequestMapping(value="/ajouter", method=RequestMethod.GET)
	public String ajouterEmployeeGET(Model model)
	{
		EmployeeModel employeeModel = new EmployeeModel();
		model.addAttribute("employeeModel", employeeModel);
		return "employee/add";
	}
	
	@RequestMapping(value="/ajouter", method=RequestMethod.POST)
	@ResponseBody
	public String ajouterEmployeePOST(@ModelAttribute("employeeModel") @Valid EmployeeModel employeeModel, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
			return null;
		System.out.println(employeeModel);
		return employeeModel.toString();
	}
	
	@RequestMapping(value="comptes", method=RequestMethod.GET)
	public String  getAllComptes(Model model)
	{
		try {
			model.addAttribute("comptes",metier.getCompteByEmployee(1L));
		}
		catch(Exception e)
		{
			model.addAttribute("flags",Arrays.asList(new String[]{"Erreur",e.getMessage(),}));
		}
		return "employee/comptes";
	}
	@RequestMapping(value="comptes/search", method=RequestMethod.POST)
	public String searchCompteWithMC(@RequestParam(name="mc", defaultValue="CE3") String mc, Model model)
	{
		try
		{
			model.addAttribute("comptes", metier.getComptesWithMC(mc));
		}
		catch(Exception e)
		{
			model.addAttribute("flag", e.getMessage());
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
