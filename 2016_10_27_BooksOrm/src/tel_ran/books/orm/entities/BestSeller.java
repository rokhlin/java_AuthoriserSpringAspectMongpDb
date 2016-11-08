package tel_ran.books.orm.entities;

import javax.persistence.Entity;

@Entity
public class BestSeller extends LiteratureBook {
	int yearSeller;
	
	public BestSeller(long isbn, String authors, String title, float price, int pages, String publisher,
			int publishYear, int yearSeller) {
		super(isbn, authors, title, price, pages, publisher, publishYear);
		this.yearSeller = yearSeller;
	}



	public BestSeller() {
		super();
		
	}



	public int getYearSeller() {
		return yearSeller;
	}



	public void setYearSeller(int yearSeller) {
		this.yearSeller = yearSeller;
	}



	@Override
	public String toString() {
		return "BestSeller [yearSeller=" + yearSeller + ", publishYear=" + publishYear + ", isbn=" + isbn + ", authors="
				+ authors + ", title=" + title + ", price=" + price + ", pages=" + pages + ", publisher=" + publisher
				+ "]";
	}
	
	

}
