package tel_ran.security.aop;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;

import tel_ran.application.annotations.Authorized;
import tel_ran.security.Accounter;
import tel_ran.security.Authenticater;

public class Authorizer {
	private Map<String, Set<String>> rulesMethods = new HashMap<>(); //key -  method, value - set of the permitted role names 
	private Set<String> freeMethods  = new HashSet<>(); //Set contains all method names available to run for all registered users
	
	private Authenticater authenticater;
	private Accounter accounter;
	
	public void setAuthenticater(Authenticater authenticater) {
		this.authenticater = authenticater;
	}


	public Authorizer() {
		super();
	}

	public Authorizer(Accounter accounter) {
		super();
		this.accounter =accounter;
	}



	/**
	 * This method will call always if the user will run an inspecting method 
	 * @param joinPoint
	 * @return
	 * @throws Throwable Security Exception: 
	 * 			401- if we have unauthorized user, 
	 * 			403 - if the user didn't get permission for calling that method.
	 */
	public Object authorize(ProceedingJoinPoint joinPoint) throws Throwable {
		fillRulesMethods(joinPoint);
		
		Object obj = joinPoint.getTarget();
		String calledMethod = joinPoint.getSignature().getName();
		String role = authenticater.getRole(obj);
		
		if (obj != null) {
			if(role == null) 
				throwSecurityException(calledMethod,"401");
			else if(freeMethods.contains(calledMethod)){
				accounter.methodCallReject(calledMethod, false);
				return joinPoint.proceed();
			}
			else{
				Set<String> permitedRoles = rulesMethods.get(calledMethod);
				if(!permitedRoles.contains(role)) 
					throwSecurityException(calledMethod,"403");	
			}
		}
		accounter.methodCallReject(calledMethod, false);
		return joinPoint.proceed();
	}


	private void fillRulesMethods(ProceedingJoinPoint joinPoint) {
		
		Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
		
		for (Method method : methods) {
			method.getParameterTypes()
			if(method.isAnnotationPresent(Authorized.class)) {
				String[] roles = method.getAnnotation(Authorized.class).roles();
				Set<String> rolesSet = new HashSet<>();
				
				for (String role : roles) 
					rolesSet.add(role);
				
				rulesMethods.put(method.getName(), rolesSet);
			}
			else {
				//System.out.println("Adding into freeMethods "+ method.getName());
				freeMethods.add(method.getName());
			}
		}
		
		
	}


	private void throwSecurityException(String calledMethod, String securityCode) {
		accounter.methodCallReject(calledMethod, securityCode.equals("401"));
		throw new SecurityException(securityCode);
		
	}

}
