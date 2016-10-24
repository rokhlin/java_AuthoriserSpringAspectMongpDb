package tel_ran.persons.controller;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.persons.model.dao.PersonsMongoDb;
import tel_ran.persons.model.entities.Person;

public class PersonsQueryTestAppl {
	static PersonsMongoDb personsMongoDb;
	
	public static void main(String[] args) {
		AbstractApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
		personsMongoDb = context.getBean(PersonsMongoDb.class);
		
		displayPersons("Persons By Name(Sara)",     personsMongoDb.getPersonsByName("Sara"));
		displayPersons("Persons By City(Rehovot)",  personsMongoDb.getPersonsByCity("Rehovot"));
		displayPersons("Persons By Age(2, 12)",     personsMongoDb.getPersonsByAge(2, 12));
		displayPersons("Employees By Salary(18000)",personsMongoDb.getEmployeesBySalary(18000));
		
		context.close();
	}

	private static void displayPersons(Iterable<Person> persons) {
		System.out.println("_________________________________________________\n");
		for (Person person : persons) {
			System.out.println(person + "\n");
		}
		
	}
	
	private static void displayPersons(String operation, Iterable<Person> persons) {
		System.out.println("_______________"+operation+"_______________\n");
		for (Person person : persons) {
			System.out.println(person + "\n");
		}
		
	}
/**
 * 
 * ауторайзер не содержит конфигураций
 * когда отпределяем метод отпределить какой вызов метода подходит для какой роли.
 * в аннотации укахать роли, которые может использовать польхователь
 * аннотация ауторайзед  которую мы пишем
 * 
 * метод ауторайз должен узнать есть аннотация у вызываемого метода то он вызывает согласно ее роли
 * 
 * 
 * аутентификатор будет работать с mongoDb где будут храниться Accounts
 * 
 * класс Account
 * стринг User ключевой
 * стринг Password
 * стринг Role
 * 
 * 
 * аутификатор обращается к бд и смотрит есть ли тот юзер  и пароль
 * 
 * 
 * 
 * 
 */
}
