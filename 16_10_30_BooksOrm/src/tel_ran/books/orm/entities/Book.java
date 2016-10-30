package tel_ran.books.orm.entities;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "library")
public class Book {
	@Id
	long isbn;
	
	
	String title;
	float price;

	
	@ManyToOne
	Publisher publisher;
	
	@ManyToMany
	Set<Author> authors;

	public Book(long isbn, String title, float price) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.price = price;
	}
	
	public Book() {
		
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", price=" + price + ", publisher=" + publisher
				+ ", authors=" + authors + "]";
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}
	
	
}
