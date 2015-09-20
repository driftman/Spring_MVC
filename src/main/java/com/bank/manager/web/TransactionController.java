package com.bank.manager.web;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.manager.metier.IManagerMetier;



@Controller
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	private IManagerMetier metier;
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="", method=RequestMethod.POST)
	public String transaction(Model model, HttpServletRequest request)
	{
		List<String> flashs = (List<String>)request.getAttribute("flashs");
		model.addAttribute("flashs", flashs);
		model.addAttribute("operations", metier.getOperations());
		return "transaction/all";
	}

	@RequestMapping(value="add", method=RequestMethod.GET)
	public String addTranstaction(Model model)
	{
		return "transaction/add";
	}
	
	@RequestMapping(value="add", method=RequestMethod.POST)
	public String addTranstaction(
			@RequestParam(required=true) String from,
			@RequestParam(required=true) String to,
			@RequestParam(required=true) String somme,
			@RequestParam(required=true) String typeTransfert,
			Model model, HttpServletRequest request)
	{
		try {
			//metier.virement(metier.getCompte(from), metier.getCompte(to), 1L, Double.parseDouble(somme));
		}
		catch(Exception ex)
		{
			request.setAttribute("flashs", (List<String>)Arrays.asList(new String[]{ex.getMessage()}));
			return "forward:/transaction";
		}
		model.addAttribute("flashs", (List<String>)Arrays.asList(new String[]{"Succesfully added ... "}));
		return "forward:/transaction";
	}
}
