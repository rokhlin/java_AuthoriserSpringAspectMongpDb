package tel_ran.books.orm.entities;

import javax.persistence.Entity;

@Entity
public class LiteratureBook extends Book {
	int publishYear;

	
	
	public LiteratureBook(long isbn, String authors, String title, float price, int pages, String publisher,
			int publishYear) {
		super(isbn, authors, title, price, pages, publisher);
		this.publishYear = publishYear;
	}
	
	public LiteratureBook() {
		super();	
	}

	public int getPublishYear() {
		return publishYear;
	}

	public void setPublishYear(int publishYear) {
		this.publishYear = publishYear;
	}
	
	

}
