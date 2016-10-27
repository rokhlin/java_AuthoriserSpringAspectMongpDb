package tel_ran.books.orm.entities;

import javax.persistence.Entity;

@Entity
public class TechnicalBook extends Book {

	int edition;
	String subject;
	public TechnicalBook(long isbn, String authors, String title, float price, int pages, String publisher, int edition,
			String subject) {
		super(isbn, authors, title, price, pages, publisher);
		this.edition = edition;
		this.subject = subject;
	}
	public TechnicalBook() {
		super();
	}
	
	public int getEdition() {
		return edition;
	}
	@Override
	public String toString() {
		return "TechnicalBook [edition=" + edition + ", subject=" + subject + ", isbn=" + isbn + ", authors=" + authors
				+ ", title=" + title + ", price=" + price + ", pages=" + pages + ", publisher=" + publisher + "]";
	}
	public void setEdition(int edition) {
		this.edition = edition;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
}
