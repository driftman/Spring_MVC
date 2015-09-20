package com.bank.manager.interceptors;

import java.util.Arrays;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;

import com.bank.manager.beans.Compte;
import com.bank.manager.dao.IManagerDaoImpl;
import com.bank.manager.metier.IManagerMetier;
import com.bank.manager.metier.IManagerMetierImpl;

public class VirementAdvisor implements MethodInterceptor {
	

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		System.out.println("|******Virement Interceptor is doing his things ...******|");
		System.out.println("Let's first check if the first account have sufficient amount to finish this transfer ...");
		Object[] method_Args = method.getArguments();
		double solde = ((IManagerMetier)method.getThis()).getCompte((String)method_Args[0]).
				getSoldeDepart();
		System.out.println("|_____>>> SOLDE DU DEBITEUR : "+ solde );
		System.out.println(Arrays.toString(method_Args));
		if (!(solde >= (double)method_Args[method_Args.length-1]))
			throw new RuntimeException("|_____>>> THIS TRANSFER IS NOT AUTHORIZED \n "
					+ "--> Account : DO NOT have sufficient amount to finish this transfer".
					toUpperCase());
		System.out.println("|_____>>> All conditions are right, the operation will now proceed !");
		method.proceed();
		System.out.println("|******Interceptor finished his jobs successfully !******|");
		
		return null;
	}

}
