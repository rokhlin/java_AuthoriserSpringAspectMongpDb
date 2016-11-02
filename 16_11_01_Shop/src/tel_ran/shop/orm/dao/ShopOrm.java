package tel_ran.shop.orm.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.springframework.transaction.annotation.Transactional;

import tel_ran.shop.orm.entities.Person;
import tel_ran.shop.orm.entities.Product;

public class ShopOrm {
	@PersistenceContext(unitName = "springHibernate", type=PersistenceContextType.EXTENDED)//jpa annotations
	EntityManager entityManager;
	
	
	@Transactional  //spring annotations
	public boolean addPerson(Person p) {
		Person person = entityManager.find(Person.class, p.getId());
		if(person != null)
			return false;
		
		entityManager.persist(p);
		return true;
	}
	
	@Transactional  //spring annotations
	public boolean addProduct(Product p) {
		Product product = entityManager.find(Product.class, p.getBarCode());
		if(product != null)
			return false;
		
		entityManager.persist(p);
		return true;
	}
	
	@Transactional  //spring annotations
	public boolean setOwnerShip(long id, List<Long> barcodes) {
		Person person = getPerson(id);
		if(person == null)
			return false;
		
		List<Product> res = getProducts(barcodes);
		
		person.setOwners(res);
		entityManager.persist(person);
		return true;
	}
	
	@Transactional  //spring annotations
	public boolean setRentShip(long id, List<Long> barcodes) {
		Person person = getPerson(id);
		if(person == null)
			return false;
		List<Product> res = getProducts(barcodes);
		
		person.setRenties(res);
		entityManager.persist(person);
		return true;
	}
	
	@Transactional 
	private Person getPerson(long id) {
		return entityManager.find(Person.class, id);
	}
	
	@Transactional 
	public List<Product> getProducts(List<Long> barcodes) {
		List<Product> res = new ArrayList<>();
		for (Long barcode : barcodes) {
			Product product = entityManager.find(Product.class, barcode);
			if(product != null)
				res.add(product);
		}
		return res;
	}
	
	@Transactional 
	public List<Person> getOwners(long barcode){
		Product p = entityManager.find(Product.class, barcode);
		return p!=null ? p.getProductOwners() : null;
	} 
	
	
	@Transactional 
	public List<Person> getRenties(long barcode){
		Product p = entityManager.find(Product.class, barcode);
		return p!=null ? p.getProductRenties() : null;
	}
	
	@Transactional 
	public List<Product> getProducts(long id){
		Person p  = entityManager.find(Person.class, id);
		return p!=null ? p.getOwners() : null;
	} 
	
	@Transactional 
	public List<Product> getRentedProducts(long id){
		Person p  = entityManager.find(Person.class, id);
		return p!=null ? p.getRenties() : null;
	} 
	
	@Transactional 
	public List<Product> getAllProducts(){
		String jpql = "SELECT p FROM Product p";
		Query query = entityManager.createQuery(jpql );  
		return query.getResultList();
	}
	
	@Transactional 
	public List<Person> getAllPersons(){
		String jpql = "SELECT p FROM Person p";
		Query query = entityManager.createQuery(jpql );  
		return query.getResultList();
	}
	
	@Transactional 
	public Set<Long> getAllPersonIds(){
		Set<Long> ids =new HashSet<>();
		List<Person> persons = getAllPersons();
		for (Person person : persons) {
			ids.add(person.getId());
		}
		return ids;
	}
	
	@Transactional 
	public Set<Long> getAllBarcodes(){
		Set<Long> ids =new HashSet<>();
		List<Product> pp = getAllProducts();
		for (Product p : pp) {
			ids.add(p.getBarCode());
		}
		return ids;
	}
}
