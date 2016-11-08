package tel_ran.books.orm.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import tel_ran.books.orm.entities.Book;

public class BooksOrm {
	@PersistenceContext(unitName = "springHibernate")
	EntityManager entityManager;
	
	
	@Transactional
	public boolean addBook(Book b) {
		if(entityManager.find(Book.class, b.getIsbn()) != null)
			return false;
		entityManager.persist(b);//add to Database
		return true;
	}

}
