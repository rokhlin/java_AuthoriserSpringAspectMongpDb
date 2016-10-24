package tel_ran.persons.model.dao;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;

import tel_ran.persons.model.entities.Person;
import tel_ran.persons.repo.PersonsRepository;

public class PersonsMongoDb {
	 @Autowired     //!!!!!!!!!!!!!!!! 
	PersonsRepository personsRepository;
	
	
	


	public  PersonsMongoDb() {
//		AbstractApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");
//		personsRepository = context.getBean(PersonsRepository.class);
	}
	
	public boolean add(Person p) {
		if(p == null || personsRepository.exists(p.getId()))
			return false;
		
		if(personsRepository.save(p) != null)
			return true;
		return false;
	}
	
	public Iterable<Person> getPersons() {
		return personsRepository.findAll();
	}
	
	public Person get(int id) {
		return personsRepository.findOne(id);
	}
	
	public boolean remove(int id) {
		if(personsRepository.exists(id)) {
			personsRepository.delete(id);
			return true;
		}
		return false;
	}
	
	public boolean remove(Person p) {
		if(personsRepository.exists(p.getId())) {
			personsRepository.delete(p);
			return true;
		}
		return false;
	}



	public Iterable<Person> getPersonsByName(String name) {
		return personsRepository.findByName(name);
	}

	public Iterable<Person> getPersonsByCity(String city) {
		return personsRepository.findByAddressCity(city);
	}
	
	public Iterable<Person> getPersonsByAge(int ageFrom, int ageTo){
		int yearFrom = getYear(ageTo);
		int yearTo = getYear(ageFrom);
		return personsRepository.findByBirthYearBetweenOrderByBirthYear(yearFrom, yearTo);
	}

	private int getYear(int age) {
		return Calendar.getInstance().get(Calendar.YEAR) - age ;
	}
	
	public Iterable<Person> getEmployeesBySalary(int salary){
		return personsRepository.findBySalaryLessThan(salary);
	}


}
