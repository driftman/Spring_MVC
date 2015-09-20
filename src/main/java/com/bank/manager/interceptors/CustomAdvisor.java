package com.bank.manager.interceptors;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class CustomAdvisor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation arg0) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("|**PROXY FACTORY BEAN ADVISOR : "+arg0.getMethod().getName()+"**|");
		arg0.proceed();
		System.out.println("|******PROXY FACTORY BEAN******|");
		return null;
	}

}
