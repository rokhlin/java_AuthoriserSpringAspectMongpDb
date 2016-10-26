package tel_ran.persons.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.persons.model.dao.dao.PersonsORM;
import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Child;
import tel_ran.persons.model.entities.Person;

public class PersonsOrmTests {
	private static final int ID = 1234;
	private static final String CITY = "Tel-Aviv";
	private static final String STREET = "Sokolov";
	private static final int BUILDING = 10;
	static AbstractApplicationContext context;
	static PersonsORM orm;
	@Before
	public void setUp() throws Exception {
		orm.addPerson(new Child(ID, "Masha", 
								LocalDate.of(2013, 11, 1), 
								new Address(CITY, STREET, BUILDING), 
								"Shalom"));
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new FileSystemXmlApplicationContext("beans.xml");
			orm = context.getBean(PersonsORM.class);
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		context.close();
	}
	
	@Test
	public void testGetPerson() {
		Person p = orm.getPerson(ID);
		assertEquals("Get Person CITY",CITY, p.getAddress().getCity());
		assertEquals("Get Person STREET",STREET, p.getAddress().getStreet());
		assertEquals("Get Person BUILDING",BUILDING, p.getAddress().getBuild());
	}
	
	@Test
	public void testRemovePerson() {
		Person p = orm.getPerson(ID);
		assertEquals("Get Person Before remove", p ,orm.getPerson(ID));
		orm.removePerson(ID);
		assertEquals("Get Person After remove", null,orm.getPerson(ID));
	}
}
