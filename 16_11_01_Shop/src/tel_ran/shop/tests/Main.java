package tel_ran.shop.tests;

import java.util.Random;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.shop.orm.dao.ShopOrm;
import tel_ran.shop.orm.entities.Person;
import tel_ran.shop.orm.entities.Product;

public class Main {
	private static final int N_OBJECTS = 100;
	private static final String[] TYPE = {"phone","radio","tv","projector","tablet","pc"};
	private static final String[] VENDOR = {"samsung","microsoft", "dell","nokia","huawei","sony"};
	private static long barcode = 314243425l;
	static AbstractApplicationContext context;
	static ShopOrm orm;
	static Random rnd = new Random(System.currentTimeMillis());
	public static void main(String[] args) {
		AbstractApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		ShopOrm orm = context.getBean(ShopOrm.class);
		
		context = new FileSystemXmlApplicationContext("beans.xml");
		orm = context.getBean(ShopOrm.class);
		
		for (int i = 0; i < N_OBJECTS; i++) {
			orm.addProduct(new Product(++barcode, getRandomProductName()));
			
		}
		
		for (int i = 0; i < N_OBJECTS; i++) {
			
			orm.addPerson(new Person((long)i, "Person_"+i));
		}
		
	}
	private static String getRandomProductName() {
		String product = TYPE[generate(0,5)]
						+ "_"
						+ VENDOR[generate(0,5)]
						+ "_"
						+ "_model"
						+generate(100, 10000);
		return  product;
	}

	private static int generate(int min, int max) {	
		return min + rnd.nextInt(max-min);
	}

}
