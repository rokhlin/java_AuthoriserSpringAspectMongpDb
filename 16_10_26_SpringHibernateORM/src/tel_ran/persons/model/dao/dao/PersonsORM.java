package tel_ran.persons.model.dao.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;

import tel_ran.persons.model.entities.Address;
import tel_ran.persons.model.entities.Person;

/**
 * 
 * 
 *
 */
public class PersonsORM {
	
	// binding JavaCode,  Spring, Hibernate with PersistentUnit by name "springHibernate" 
	@PersistenceContext(unitName = "springHibernate")
	EntityManager entityManager;
	
	@Transactional //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! Important for editing !!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	public boolean addPerson(Person p) {
		if(entityManager.find(Person.class, p.getId()) != null)
			return false;
		entityManager.persist(p);//add to Database
		return true;
	}
	
	@Transactional
	public boolean updateAddress(int id, Address address) {
		Person p =entityManager.find(Person.class, id);
		if(p == null)
			return false;
		p.setAddress(address);
		return true;
	}
	
	@Transactional
	public Person getPerson(int id) {
		return entityManager.find(Person.class, id);
	}
	
	@Transactional
	public Person removePerson(int id) {
		Person p =entityManager.find(Person.class, id);
		if(p != null)
			entityManager.remove(p);
		return p;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
