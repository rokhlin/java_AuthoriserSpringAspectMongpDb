package tel_ran.application;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.mongo.dao.AccauntsDao;
import tel_ran.mongo.entity.Account;


public class ApplicationController {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new FileSystemXmlApplicationContext("BeansAOP.xml");
		AccauntsDao db = (AccauntsDao) ctx.getBean(AccauntsDao.class);
//		Account acc = new Account("Ivan", "1234", "admin");
//		Account acc2 = new Account("Petr", "123", "user");
//		Account acc3 = new Account("Ira", "12", "observer");
//		Account acc4 = new Account("Mira", "1002", "observer");
//		db.add(acc);
//		db.add(acc2);
//		db.add(acc3);
//		db.add(acc4);
		AplicationClass myClass;
		
		
		
		
		
		
		System.out.println("_____________________admin___________________________");	
		myClass = (AplicationClass) ctx.getBean("aplicationClass");
		try {
			myClass.login("Ivan", "1234");
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.get1();
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.set2();
			
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.set1();
			
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("_____________________user___________________________");
myClass = (AplicationClass) ctx.getBean("aplicationClass");
		
		try {
			myClass.login("Ira", "12");
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.get1();
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.set2();
			
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.set1();
			
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		System.out.println("_____________________observer___________________________");
		
		myClass = (AplicationClass) ctx.getBean("aplicationClass");
		
		try {
			myClass.login("Petr", "123");
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.get1();
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.set2();
			
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		try {
			myClass.set1();
			
		} catch (SecurityException e1) {
			System.out.println(e1.getMessage());
		}
		
		
		System.out.println("_____________________401 test___________________________");
		myClass = (AplicationClass) ctx.getBean("aplicationClass");
				
				try {
					myClass.login("Petr", "12300000");
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				
				try {
					myClass.get1();
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				
				try {
					myClass.set2();
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				
				try {
					myClass.set1();
					
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				
		System.out.println("_____________________ test call unAnnotated method___________________________");
		myClass = (AplicationClass) ctx.getBean("aplicationClass");
				
				try {
					myClass.login("Ira", "12");
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				
				try {
					myClass.get1();
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				
				try {
					myClass.set2();
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				
				try {
					myClass.set1();
					
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
				try {
					myClass.get3();
				} catch (SecurityException e1) {
					System.out.println(e1.getMessage());
				}
		ctx.close();
	}

}
