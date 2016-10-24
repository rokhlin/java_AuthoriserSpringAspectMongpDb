package tel_ran.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.mongo.dao.AccauntsDao;
import tel_ran.mongo.entity.Account;


public class ApplicationController {
	static AplicationClass myClass;
	static AbstractApplicationContext ctx;
	
	public static void main(String[] args) {
		ctx = new FileSystemXmlApplicationContext("BeansAOP.xml");
		
		//accountsInit();//uncomment this method for adding the new User accounnts

		
		System.out.println("_____________________admin___________________________");	
		
		tryToLogin("Ivan", "1234");
		call(MethodName.get1);
		call(MethodName.set1);
		call(MethodName.set2);
		
		System.out.println("_____________________user___________________________");
		
		tryToLogin("Petr", "123");
		call(MethodName.get1);
		call(MethodName.set1);
		call(MethodName.set2);
		
		System.out.println("_____________________observer___________________________");
		
		tryToLogin("Ira", "12");
		call(MethodName.get1);
		call(MethodName.set1);
		call(MethodName.set2);
		
		System.out.println("_____________________401 test___________________________");
		
		tryToLogin("Petr", "12300000");
		call(MethodName.get1);
		call(MethodName.set1);
		call(MethodName.set2);	
			
		System.out.println("_____________________ test call unAnnotated method___________________________");
		
		tryToLogin("Ira", "12");		
		call(MethodName.get1);
		call(MethodName.get3);	
		
		ctx.close();
	}

	
	
	private static void call(MethodName name) {
		try {
			switch(name) {
			case get1:
				myClass.get1();
				break;
			case get3:
				myClass.get3();
				break;
			case set1:
				myClass.set1();
				break;
			case set2:
				myClass.set2();
				break;
				}
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
	
	}



	private static void tryToLogin(String userName, String password) {
		try {
			myClass = (AplicationClass) ctx.getBean("aplicationClass");
			myClass.login(userName,password);
		
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
	}

	@SuppressWarnings("unused")
	private static void accountsInit() {
		AccauntsDao db = (AccauntsDao) ctx.getBean(AccauntsDao.class);
		db.add(new Account("Ivan", "1234", "admin"));
		db.add(new Account("Petr", "123", "user"));
		db.add(new Account("Ira", "12", "observer"));
		db.add(new Account("Mira", "1002", "observer"));
	}
	
	public static enum MethodName{
		get1, get3, set1, set2
	}

}
