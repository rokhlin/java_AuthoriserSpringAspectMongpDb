package tel_ran.shop.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


import tel_ran.shop.orm.dao.ShopOrm;
import tel_ran.shop.orm.entities.Person;
import tel_ran.shop.orm.entities.Product;

public class ShopTests {
	private static final int N_OBJECTS = 100;
	private static final String[] TYPE = {"phone","radio","tv","projector","tablet","pc"};
	private static final String[] VENDOR = {"samsung","microsoft", "dell","nokia","huawei","sony"};
	private static final long  BARCODE = 314243425l;
	private long barcode = BARCODE;
	static AbstractApplicationContext context;
	static ShopOrm orm;
	List<Person> persons;
	List<Product> products;
	static Random rnd = new Random(System.currentTimeMillis());
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		context.close();
	}

	

	@Before
	public void setUp() throws Exception {
		context = new FileSystemXmlApplicationContext("beans.xml");
		orm = context.getBean(ShopOrm.class);
		
		for (int i = 0; i < N_OBJECTS; i++) {
			orm.addProduct(new Product(++barcode, getRandomProductName()));
			orm.addPerson(new Person((long)i, "Person_"+i));
		}
		updateEntityValues();
		
		addOwnerShip();
		addRentiesShip();
		
	}

	

	private void addRentiesShip() {
		List<Long> barcodes;
		for (int i = 0; i < N_OBJECTS; i++) {
			barcodes = genBarcodes(10);
			//System.out.println("AddOwnerShip id="+i+"-Barcodes"+Arrays.deepToString(barcodes.toArray()));
			orm.setRentShip(i, barcodes);
			
		}
		
	}
	
	private void updateEntityValues() {
		persons = orm.getAllPersons();
		products = orm.getAllProducts();
		
	}

	
	
	
	
	
	
	private String getRandomProductName() {
		String product = TYPE[generate(0,5)]
						+ "_"
						+ VENDOR[generate(0,5)]
						+ "_"
						+ "_model"
						+generate(100, 10000);
		return  product;
	}

	private int generate(int min, int max) {	
		return min + rnd.nextInt(max-min);
	}
	
	

	@After
	public void tearDown() throws Exception {
	}
	
	
	@Test
	public void testRefresh() {
		assertEquals(N_OBJECTS,persons.size());
		assertEquals(10,persons.get(0).getOwners().size());
	}
	
	
	@Test
	public void testAddOwnerShip() {

		updateEntityValues();
		
	assertEquals(10,persons.get(0).getOwners().size());
		

		
	}

	@Test
	public void testAddRentShip() {

		updateEntityValues();
		
		assertEquals(10,persons.get(0).getRenties().size());
		
		
	}
	private void addOwnerShip() {
		List<Long> barcodes;
		for (int i = 0; i < N_OBJECTS; i++) {
			barcodes = genBarcodes(10);
			//System.out.println("AddOwnerShip id="+i+"-Barcodes"+Arrays.deepToString(barcodes.toArray()));
			orm.setOwnerShip(i, barcodes);
			
		}
		
	}

	@Test
	public void testProductCreation() {
		assertEquals(N_OBJECTS,persons.size());
		assertEquals(N_OBJECTS,products.size());
	}
	
	@Test
	public void testGenBarcodes() {
		assertEquals(10,genBarcodes(10).size());
	}

	
	@Test
	public void testOrmGetProducts() {
		List<Long> barcodes = genBarcodes(10);
		System.out.println(Arrays.deepToString(barcodes.toArray()));
		assertEquals(10,orm.getProducts(barcodes).size());
		
	}
	
	@Test
	public void testGetOwners() {
		
		for (int i = 0; i < products.size(); i++) {
			long barcode = products.get(i).getBarCode();
			List<Person> renties = orm.getOwners(barcode);
			assertEquals(products.get(i).getProductOwners().size(),renties.size());
		}
	}
	@Test
	public void testGetRenties() {
		
		for (int i = 0; i < products.size(); i++) {
			long barcode = products.get(i).getBarCode();
			List<Person> renties = orm.getRenties(barcode);
			assertEquals(products.get(i).getProductRenties().size(),renties.size());
		}	
	}
	
	@Test
	public void testGetOwnerProducts() {
		for (int i = 0; i < persons.size(); i++) {
			List<Product> res = orm.getProducts(i);
			assertEquals(persons.get(i).getOwners().size(),res.size());
		}
	}
	@Test
	public void testGetRentedProducts() {
		for (int i = 0; i < persons.size(); i++) {
			List<Product> res = orm.getRentedProducts(i);
			assertEquals(persons.get(i).getRenties().size(),res.size());
		}
	}
	


	private List<Long> genBarcodes(int count) {
		List<Long> res = new ArrayList<Long>();
		for (int j = 0; j < count; j++) {
			res.add(BARCODE+generate(0, N_OBJECTS-1));
		}
		return res;
	}

}
