package com.bank.manager.aspects;

import java.util.Arrays;
import java.util.Date;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.mockito.internal.stubbing.answers.ThrowsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.manager.beans.Compte;
import com.bank.manager.beans.Operation;
import com.bank.manager.metier.IManagerMetier;


@Aspect
@Component
public class ServiceAspect {
	/*private long t1;
	private long tVirement;
	@Autowired
	private IManagerMetier metier;
	
	public void setMetier(IManagerMetier metier)
	{
		this.metier = metier;
	}
	
	
	@Pointcut("execution(* retrait(..))")
	public void execRetrait(){}
	@Pointcut("call(* retrait(..))")
	public void callRetrait(){}
	
	@Before("callRetrait()")
	public void loggingRetraitBefore()
	{
		System.out.println("Start of (call) RETRAIT");
		t1 = System.currentTimeMillis();
	}
	
	@After("callRetrait()")
	public void loggingRetraitAfter()
	{
		System.out.println("End of (call) RETRAIT");
		System.out.println("Time ellapsed : "+(System.currentTimeMillis() - t1));
	}
	
	
	
	@Around("execRetrait() && args(operation,compte,code_employee,montant)")
	public Operation beforeRetrait(ProceedingJoinPoint joinPoint, 
			Operation operation, 
			Compte compte,
			Long code_employee,
			double montant) throws Throwable
	{
		System.out.println("BEFORE->"+operation);
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		if(!(compte.getSoldeDepart()>=montant))
			throw new RuntimeException("SOLDE INSUFFISANT");
		Operation o = (Operation)joinPoint.proceed();
		System.out.println("AFTER->"+o);
		return operation;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Pointcut("execution(* versement(..))")
	public void execCredit(){}
	@Pointcut("call(* versement(..))")
	public void callCredit(){}
	
	@Before("callCredit()")
	public void loggingCreditBefore()
	{
		System.out.println("Start of (call) VERSEMENT");
		t1 = System.currentTimeMillis();
	}
	
	@After("callCredit()")
	public void loggingCreditAfter()
	{
		System.out.println("End of (call) VERSEMENT");
		System.out.println("Time ellapsed : "+(System.currentTimeMillis() - t1));
	}
	
	
	
	@Around("execCredit() && args(operation,compte,code_employee,montant)")
	public Operation beforeCredit(ProceedingJoinPoint joinPoint, 
			Operation operation, 
			Compte compte,
			Long code_employee,
			double montant) throws Throwable
	{
		System.out.println("SOLDE AVANT OPERATION->"+compte.getSoldeDepart());
		System.out.println(Arrays.toString(joinPoint.getArgs()));
		joinPoint.proceed();
		System.out.println("SOLDE APRES OPERATION->"+compte.getSoldeDepart());
		return null;
	}
	
	@Around("execution(* virement(..)) && args(compte1, compte2, code_employee, montant)")
	public Operation virementExec(ProceedingJoinPoint joinPoint, Compte compte1, Compte compte2, Long code_employee, double montant) throws
	Throwable
	{
		tVirement = System.currentTimeMillis();
		System.out.println("************AspectJ*********");
		System.out.println("UN VIREMENT DE : "+compte1.getCodeCompte()+" A : "+compte2.getCodeCompte()+" est "
				+ "au point de commencer !");
		System.out.println(" COMPTE CREDITEUR [ solde avant virement => "+compte1.getSoldeDepart()+"]");
		System.out.println(" COMPTE DEBITEUR [ solde avant virement => "+compte2.getSoldeDepart()+"]");
		
		joinPoint.proceed();
		
		System.out.println(" COMPTE CREDITEUR [ solde après virement => "+compte1.getSoldeDepart()+"]");
		System.out.println(" COMPTE DEBITEUR [ solde après virement => "+compte2.getSoldeDepart()+"]");
		System.out.println("OPERATIONS DE VIREMENTS TERMINEES ! ");
		System.out.println("ELAPSED Time ==> "+(System.currentTimeMillis()-tVirement)/1000+" s");
		System.out.println("************AspectJ*********");
		return null;
	}
	
	
	
	*/

}
