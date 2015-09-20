package com.bank.manager.web;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bank.manager.beans.SessionBean;



@Controller
@RequestMapping("")
public class LoginController {
	
	/*
	@Autowired
	private SessionBean sessionBean;
	
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String home()
	{
		return "public";
	}
	
	@RequestMapping(value="login", method=RequestMethod.POST)
	public String loginPOST(@RequestParam(defaultValue="username") String username,
			@RequestParam(defaultValue="password") String password,
			@RequestParam(defaultValue="unchecked", required=true) String isEmployee,
			HttpServletRequest request)
	{
		sessionBean.setUsername(username);
		HttpSession httpSession = request.getSession();
		httpSession.setAttribute("sessionBean", sessionBean);
		if(isEmployee=="checked")
			return "redirect:employee/ajouter";
		else 
			return "redirect:client/demande";
	}
	*/
	@RequestMapping(value="login", method=RequestMethod.GET)
	public ModelAndView loginGET(
			@RequestParam(value="success", required=false) String success, 
			@RequestParam(value="error", required=false) String error,
			ModelAndView mav)
	{
		if(error != null)
			mav.addObject("flashs", Arrays.asList(new String[]{"Erreur",
					"Nom d'utilisateur ou mot de passe incorrect !"}));
		if(success != null)
			mav.addObject("flashs", Arrays.asList(new String[]{"Success",
			}));
		mav.setViewName("login");
		return mav;
	}
	/*
	@RequestMapping(value="logout", method=RequestMethod.GET)
	public String logout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:";
	}
	*/
}
