package tel_ran.network.management.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class NetworkManagementAppl {

	public static void main(String[] args) {
		try(AbstractApplicationContext context = new FileSystemXmlApplicationContext("mbeans.xml");){
			
			while(true);
				
		}
		
		
		
		
		
		
	}

}
