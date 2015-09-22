package com.bank.manager.web;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Coordonnee;
import com.bank.manager.beans.Employee;
import com.bank.manager.beans.Situation;
import com.bank.manager.configs.CustomUser;
import com.bank.manager.metier.IManagerMetier;
import com.bank.manager.models.ClientModel;

@Controller
@RequestMapping(value="client")
public class ClientController {

	@Autowired
	private IManagerMetier metier;
	
	
	
	@RequestMapping("")
	public ModelAndView sayHello()
	{
		CustomUser cu = ((CustomUser)(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/home");
		mav.addObject("name", cu.getPerson().getCoordonnee().getNom());
		return mav;
	}
	@RequestMapping("comptes")
	public ModelAndView getComptes()
	{
		CustomUser cu = ((CustomUser)(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
		ModelAndView mav = new ModelAndView();
		mav.setViewName("client/comptes");
		try {
			List<Compte> comptes = metier.getCompteByClient(cu.getPerson().getId());
			mav.addObject("comptes", comptes);
			return mav;
		}
		catch(Exception e){
			mav.addObject("flashs", Arrays.asList(new String[]{"Erreur",e.getMessage()}));
			return mav;
		}
	}
	
	
	
}
