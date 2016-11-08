package tel_ran.books.orm.controller;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.books.orm.dao.BooksOrm;
import tel_ran.books.orm.entities.Book;

public class BooksCreationAppl {
	static BooksOrm orm;
	static List<Book> books;
	
	public static void main(String[] args) {
		try(AbstractApplicationContext context = new FileSystemXmlApplicationContext("beans.xml");){
			orm = context.getBean(BooksOrm.class);
			RandomBookCreation booksCreation = new RandomBookCreation(100);
			booksCreation.createBooks(orm);
		}
	}

}
