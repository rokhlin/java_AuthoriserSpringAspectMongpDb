package tel_ran.persons.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.persons.model.dao.PersonsMongoDb;
import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Child;
import tel_ran.persons.model.entities.Employee;
import tel_ran.persons.model.entities.Person;

public class PersonsMongoTestAppl {
	public static void main(String[] args) {
		Person[] persons = {new Child(123, "Moshe", 2011, new Address("Rehovot", "Plaut", 10), "tel_ran"),
			new Employee(124, "Vasya", 1980, new Address("Rehovot", "Plaut", 10),15000, "Tel_ran"),
			new Child(125, "Sara", 2013, new Address("Rehovot", "Plaut", 10), "none"),
			new Child(126, "Olya", 2010, new Address("Beersheva", "Yaelim", 3), "Klita"),
			new Child(127, "Sasha", 2012, new Address("Beersheva", "Yaelim", 3), "Klita"),
			new Employee(128, "DAvid", 1970, new Address("Beersheva", "Yaelim", 3), 20000, "Motorola"),
			new Child(129, "Talya", 2010, new Address("Rehovot", "Plaut", 10), "Salut"),
			new Employee(130, "Serg", 1975, new Address("Beersheva", "Yaelim", 3), 18000, "Motorola")};
		
		AbstractApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		PersonsMongoDb personsMongoDb = context.getBean(PersonsMongoDb.class);
	
//		for (Person person : persons) {
//			personsMongoDb.add(person);
//		}
		
		Iterable<Person> persoList = personsMongoDb.getPersons();
		for (Person person : persoList) {
			System.out.println(person + "\n");
		}
		
		
		
		context.close();
	}
	

}
