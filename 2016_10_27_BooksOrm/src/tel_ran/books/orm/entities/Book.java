package tel_ran.books.orm.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public abstract class Book {
	@Id
	long isbn;
	String authors;
	String title;
	float price;
	int pages;
	String publisher;
	
	public Book(long isbn, String authors, String title, float price, int pages, String publisher) {
		super();
		this.isbn = isbn;
		this.authors = authors;
		this.title = title;
		this.price = price;
		this.pages = pages;
		this.publisher = publisher;
	}
	
	public Book() {
		
	}

	public long getIsbn() {
		return isbn;
	}

	public void setIsbn(long isbn) {
		this.isbn = isbn;
	}

	public String getAuthors() {
		return authors;
	}

	public void setAuthors(String authors) {
		this.authors = authors;
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

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	
	public abstract String toString();

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (isbn ^ (isbn >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (isbn != other.isbn)
			return false;
		return true;
	}
	
	
}
