package com.bank.manager.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.manager.metier.IManagerMetier;



@Controller
@RequestMapping("transaction")
public class TransactionController {
	
	@Autowired
	private IManagerMetier metier;
	
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
			@RequestParam(name="from", required=true) String from,
			@RequestParam(name="to", required=true) String to,
			@RequestParam(name="somme", required=true) String somme,
			@RequestParam(name="typeTransfert", required=true) String typeTransfert,
			Model model, HttpServletRequest request)
	{
		try {
			metier.virement(from, to, 1L, Double.parseDouble(somme));
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
