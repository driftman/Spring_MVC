package com.bank.manager.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.manager.metier.IManagerMetier;
import com.bank.manager.models.ClientModel;

@Controller
@RequestMapping(value="client")
public class ClientController {

	@Autowired
	private IManagerMetier metier;
	
	
	@RequestMapping(value="demande", method=RequestMethod.GET)
	public String ajouterDemandeGET(Model model)
	{
		ClientModel clientModel = new ClientModel();
		model.addAttribute("clientModel", clientModel);
		return "client/add";
	}
	
	@RequestMapping(value="demande", method=RequestMethod.POST)
	@ResponseBody
	public String ajouterDemandePOST(@ModelAttribute("clientModel") @Valid ClientModel clientModel, BindingResult bindingResult, Model model)
	{
		if(bindingResult.hasErrors())
			return null;
		System.out.println(clientModel);
		return clientModel.toString();
	}
}
