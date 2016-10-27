package tel_ran.books.orm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import tel_ran.books.orm.dao.BooksOrm;
import tel_ran.books.orm.entities.BestSeller;
import tel_ran.books.orm.entities.Book;
import tel_ran.books.orm.entities.LiteratureBook;
import tel_ran.books.orm.entities.TechnicalBook;

public class RandomBookCreation {
	private int nBooks = 100;
	private int count = 100000;
	
	
	public RandomBookCreation(int nBooks) {
		super();
		this.nBooks = nBooks;
	}

	
	public List<Book> createBooks(BooksOrm orm) {
		List<Book> res = new ArrayList<>();
		for (int i = 0; i < nBooks; i++) {
			Book b = generateBook(i);
			res.add(b);
			orm.addBook(b);
		}
		
		return res;
	}


	private int getRandom(int min ,int max) {
		Random r = new Random(System.currentTimeMillis());
		return r.nextInt(max - min) + min; 
	}

	/**
	 * 
	 * @param index
	 * @return The book with generated type
	 */
	private Book generateBook(int index) {
		long isbn = count++;
		String authors = "Author_"+index;
		String title = "Title_"+index;
		float price =(float)getRandom(1, 100)+ (float)Math.random();
		int pages = getRandom(0, 700);
		String publisher = "Publisher_" +index;
		int publishYear = getRandom(1980, 2016); 
		int edition = getRandom(1, 3);
		String subject = "Subject_"+ index;
		int yearSeller = getRandom(1980, 2016);
		
		Book res = null;
		
		switch(getRandom(0 ,2)) {
		case 0:
			res = new BestSeller(isbn, authors, title, price, pages, publisher, publishYear, yearSeller);
			break;
		case 1:
			res = new LiteratureBook(isbn, authors, title, price, pages, publisher, publishYear);
			break;
		case 2:
			res = new TechnicalBook(isbn, authors, title, price, pages, publisher, edition, subject);
			break;
		}
	
		return res;
	}

}
