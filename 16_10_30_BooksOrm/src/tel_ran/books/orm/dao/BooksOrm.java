package tel_ran.books.orm.dao;

import java.security.PublicKey;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.transaction.annotation.Transactional;

import tel_ran.books.orm.entities.Author;
import tel_ran.books.orm.entities.Book;
import tel_ran.books.orm.entities.Publisher;

public class BooksOrm {
	@PersistenceContext(unitName = "springHibernate", type=PersistenceContextType.EXTENDED)//jpa annotations
	EntityManager entityManager;
	
	
	@Transactional  //spring annotations
	public boolean addBook(Book b, Set<String> authorNames, String publisherName) throws Exception {
		if(entityManager.find(Book.class, b.getIsbn()) != null)
			return false;
		Publisher publisher = getPublisher(publisherName);
		b.setPublisher(publisher);
			Set<Author> authors = getAuthors(authorNames);
			b.setAuthors(authors);
			
			
		
		
		entityManager.persist(b);
		return true;
	}
	
	private Publisher getPublisher(String publisherName) throws PublisherNotFoundException {
		Publisher publisher = entityManager.find(Publisher.class, publisherName);
		if(publisher == null)
			throw new PublisherNotFoundException(publisherName);
		return publisher;
	}

	private Set<Author> getAuthors(Set<String> authorNames) throws AuthorNotFoundException {
		Set<Author> res = new LinkedHashSet<>();
		for (String name : authorNames) {
			Author author = entityManager.find(Author.class,name);
			if(author == null)
				throw new AuthorNotFoundException(name);
			res.add(author);
		}
		return res;
	}

	@Transactional
	public boolean addAuthor(Author author) {
		if(author == null || entityManager.find(Author.class, author.getName()) != null)
			return false;
		entityManager.persist(author);
		return true;
		
	}
	
	@Transactional
	public boolean addPublisher(Publisher publisher) {
		if(publisher == null || entityManager.find(Publisher.class, publisher.getName()) != null)
			return false;
		entityManager.persist(publisher);
		return true;
		
	}
}
