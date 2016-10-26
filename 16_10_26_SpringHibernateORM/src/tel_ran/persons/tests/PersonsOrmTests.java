package tel_ran.persons.tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.persons.model.dao.dao.PersonsORM;
import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Child;
import tel_ran.persons.model.entities.Employee;
import tel_ran.persons.model.entities.Person;

import static tel_ran.persons.tests.PersonOrmTestCreationAppl.*;

public class PersonsOrmTests {
	private static final int ID = 1234;
	private static final String CITY = "Tel-Aviv";
	private static final String STREET = "Sokolov";
	private static final int BUILDING = 10;
	
	private static Person[] persons = {new Child(123, "Moshe", LocalDate.of(2011, 11, 3), new Address("Rehovot", "Plaut", 10), "tel_ran"),
			new Employee(124, "Vasya", LocalDate.of(1980, 12, 4), new Address("Rehovot", "Plaut", 10),15000, "Tel_ran"),
			new Child(125, "Sara", LocalDate.of(2013, 6, 14), new Address("Rehovot", "Plaut", 10), "none"),
			new Child(126, "Olya", LocalDate.of(2010, 8, 12), new Address("Beersheva", "Yaelim", 3), "Klita"),
			new Child(127, "Sasha", LocalDate.of(2012, 11, 23), new Address("Beersheva", "Yaelim", 3), "Klita"),
			new Employee(128, "DAvid", LocalDate.of(1970, 1, 3), new Address("Beersheva", "Yaelim", 3), 20000, "Motorola"),
			new Child(129, "Talya", LocalDate.of(2010, 12, 30), new Address("Rehovot", "Plaut", 10), "Salut"),
			new Employee(130, "Serg", LocalDate.of(1975, 5, 13), new Address("Beersheva", "Yaelim", 3), 18000, "Motorola")};
	
	private static Person[] personsNov = {
			new Child(123, "Moshe", LocalDate.of(2011, 11, 3), new Address("Rehovot", "Plaut", 10), "tel_ran"),
			new Child(ID, "Masha", LocalDate.of(2013, 11, 1), new Address(CITY, STREET, BUILDING), "Shalom"),
			new Child(127, "Sasha", LocalDate.of(2012, 11, 23), new Address("Beersheva", "Yaelim", 3), "Klita")};
	
	static AbstractApplicationContext context;
	static PersonsORM orm;
	
	@Before
	public void setUp() throws Exception {
		orm.addPerson(personsNov[1]);
		createPersons(persons, orm);
		
	}

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		context = new FileSystemXmlApplicationContext("beans.xml");
			orm = context.getBean(PersonsORM.class);
	}
	
	@After
	public void tearDown() throws Exception {
		orm.removePerson(ID);
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
	
	@Test
	public void testGetPersonsByMonth() {
		List<Person> actual = orm.getPersonsByMonth(11);
		Person[] actualArray = actual.toArray(new Person[actual.size()]);
		Arrays.sort(personsNov,(a,b)->a.getId() - b.getId());
		Arrays.sort(actualArray,(a,b)->a.getId() - b.getId());
		assertArrayEquals("Get Persons By Month",personsNov, actualArray);
		
	}
}
