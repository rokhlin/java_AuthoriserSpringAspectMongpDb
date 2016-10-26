package tel_ran.persons.tests;

import java.time.LocalDate;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.persons.model.dao.dao.PersonsORM;
import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Child;
import tel_ran.persons.model.entities.Employee;
import tel_ran.persons.model.entities.Person;

public class PersonOrmTestCreationAppl {
	static PersonsORM orm;
	public static void main(String[] args) {
		Person[] persons = {new Child(123, "Moshe", LocalDate.of(2011, 11, 3), new Address("Rehovot", "Plaut", 10), "tel_ran"),
				new Employee(124, "Vasya", LocalDate.of(1980, 12, 4), new Address("Rehovot", "Plaut", 10),15000, "Tel_ran"),
				new Child(125, "Sara", LocalDate.of(2013, 6, 14), new Address("Rehovot", "Plaut", 10), "none"),
				new Child(126, "Olya", LocalDate.of(2010, 8, 12), new Address("Beersheva", "Yaelim", 3), "Klita"),
				new Child(127, "Sasha", LocalDate.of(2012, 5, 23), new Address("Beersheva", "Yaelim", 3), "Klita"),
				new Employee(128, "DAvid", LocalDate.of(1970, 1, 3), new Address("Beersheva", "Yaelim", 3), 20000, "Motorola"),
				new Child(129, "Talya", LocalDate.of(2010, 12, 30), new Address("Rehovot", "Plaut", 10), "Salut"),
				new Employee(130, "Serg", LocalDate.of(1975, 5, 13), new Address("Beersheva", "Yaelim", 3), 18000, "Motorola")};
		
		
		try(AbstractApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");){
			orm = context.getBean(PersonsORM.class);
			createPersons(persons, orm);
			updatePerson(123, new Address("Ramat Gan", "Jabotinsky", 20));
			
		}
	}
	
	private static void updatePerson(int id, Address address) {
		orm.updateAddress(id, address);
		
	}

	

	public static void createPersons(Person[] persons, PersonsORM ormm) {
		for (Person person : persons) {
			ormm.addPerson(person);
		}
	}
	
	private static Person getPerson(int id) {
		return orm.getPerson(id);
	}
	
	private static Person removePerson(int id) {
		return orm.removePerson(id);
	}

}
