package tel_ran.application;

import tel_ran.application.annotations.Authorized;
import tel_ran.security.Authenticater;

public class AplicationClass {
	Authenticater authenticater;
	
	
	
	public Authenticater getAuthenticater() {
		return authenticater;
	}


	public void setAuthenticater(Authenticater authenticater) {
		this.authenticater = authenticater;
	}


	public AplicationClass(Authenticater authenticater) {
		super();
		this.authenticater = authenticater;
	}


	/**
	 * calls authenticate method of the authenticater
	 * @return 
	 */
	public boolean login(String role,String password) {
		return authenticater.authenticate(role, password, this);
	}
	
	@Authorized(roles = {"admin"})
	public void set1() {
		 System.out.println("set1");
	}
	
	@Authorized(roles = {"admin","user"})
	public void set2() {
		System.out.println("set2");
	} 
	
	public void get1() {
		System.out.println("get1");
	} 
	
	public void get3() {
		System.out.println("get3");
	}

}
