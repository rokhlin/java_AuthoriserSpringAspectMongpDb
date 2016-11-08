package tel_ran.employee.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tel_ran.employee.entities.Employee;

public class EmployeesOrm {
	
	@PersistenceContext(unitName = "springHibernate", type=PersistenceContextType.EXTENDED)//jpa annotations
	EntityManager entityManager;
	
	@Transactional
	public boolean addGeneralManager(Employee e) {
		Employee genManager = entityManager.find(Employee.class, e.getId());
		if(genManager != null)
			return false;
		entityManager.persist(e);
		return true;
	}
	
	@Transactional
	public boolean addEmployee(Employee e, int manager_Id) {
		Employee employee = entityManager.find(Employee.class, e.getId());
		Employee manager = entityManager.find(Employee.class, manager_Id);
		if(employee != null || manager ==null)
			return false;
		e.setManager(manager);
		entityManager.persist(e);
		return true;
	}
	
	@Transactional
	public Set<Employee> getSubordinates(int id){
		Query query = entityManager.createQuery("SELECT s FROM Employees e join e.subordinates s join e.manager m"); 
		return (Set<Employee>) query.getResultList();
	}
	
	@Transactional
	public List<Employee> getAllEmployees(){
		//String jpql = "Select e from Employees e join e.manager m join e.subordinates s";
		String jpql = "Select e from Employees e";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Transactional
	public Employee getEmployee(int id){
		return entityManager.find(Employee.class, id);
	}
}
