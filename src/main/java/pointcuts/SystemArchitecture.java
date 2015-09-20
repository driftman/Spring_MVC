package pointcuts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;



@Aspect
public class SystemArchitecture {

	@Pointcut(value="execution(* com.bank.manager.metier.IManagerMetierImpl.*(..))")
	public void allMetierMethods(){}
	
	@Pointcut(value="execution(* com.bank.manager.metier.IManagerMetierImpl.versement(..))")
	public void versement() {}
	@Pointcut(value="execution(* com.bank.manager.metier.IManagerMetierImpl.virement(..))")
	public void virement() {}
	@Pointcut(value="execution(* com.bank.manager.metier.IManagerMetierImpl.retrait(..))")
	public void retrait() {}
	
	
	@Pointcut(value=""
			+ "allMetierMethods() && "
			+ "!versement() && "
			+ "!virement() && "
			+ "!retrait()")
	public void allMetierExceptVirVerRet(){}
	
}
