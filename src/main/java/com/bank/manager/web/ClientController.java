package com.bank.manager.web;

import java.util.Arrays;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bank.manager.beans.Account;
import com.bank.manager.beans.Adresse;
import com.bank.manager.beans.Client;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Situation;
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
		Employee employee;
		try
		{
			employee = metier.getEmployee(1L);
			metier.addClient(client, account, situation, coordonnee, address, 1L);
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
