package tel_ran.books.orm.controller;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.books.orm.dao.BooksOrm;
import tel_ran.books.orm.entities.Address;
import tel_ran.books.orm.entities.Author;
import tel_ran.books.orm.entities.Book;
import tel_ran.books.orm.entities.Publisher;


public class RandomBookCreation {
	private static final int N_COUNTRIES = 3;
	private static final int N_CITIES = 15;
	private static final int N_PUBLISHERS = 3;
	private static int N_BOOKS = 100;
	private static int count = 100000;
	static BooksOrm booksOrm;
	static Random rnd = new Random(System.currentTimeMillis());
	private static final int N_AUTHORS = 20;
	private static long isbn = 12030143l;
	
	public static void main(String[] args) {
		try(AbstractApplicationContext ctx =new FileSystemXmlApplicationContext("beans.xml")){
			booksOrm = ctx.getBean(BooksOrm.class);
			createRandomBooks();
		}
	}

	private static void createRandomBooks() {
		createAuthors();
		createPublishers();
		for (int i = 0; i < N_BOOKS; i++) {
			createRandomBook();
		}
		
	}

	private static void createRandomBook() {
		for (int i = 0; i < N_BOOKS; i++) {
			try {
				booksOrm.addBook(new Book((++isbn),  getRandomTitle(i), getRandomPrice()), getAuthorNames(), getPublisherName());
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
		
	}

	private static String getPublisherName() {
		
		return "publisher_"+rnd.nextInt(N_PUBLISHERS);
	}

	private static Set<String> getAuthorNames() {
		Set<String> res = new LinkedHashSet<>();
		int nAuthorsPerBook = 1+rnd.nextInt(5);
		for (int i = 0; i < nAuthorsPerBook; i++) {
			res.add("author_"+ rnd.nextInt(N_AUTHORS));
		}
		return res;
	}

	private static float getRandomPrice() {
		
		return 100.5f+rnd.nextInt(500);
	}

	private static String getRandomTitle(int i) {
		return "title"+i;
	}

	private static void createPublishers() {
		for (int i = 0; i < N_PUBLISHERS; i++) {
			booksOrm.addPublisher(new Publisher("publisher_"+i,getRamdomAddress()));
		}
		
	}

	private static void createAuthors() {
		for (int i = 0; i < N_AUTHORS ; i++) {
			booksOrm.addAuthor(new Author("author_" + i,getRamdomAddress(),getRandomYear()));
		}
		
	}

	private static int getRandomYear() {
		return 1940+rnd.nextInt(2016-1940);
	}

	private static Address getRamdomAddress() {
		return new Address("Country_"+rnd.nextInt(N_COUNTRIES),"city_"+rnd.nextInt(N_CITIES));
		
	}
	
	

}
