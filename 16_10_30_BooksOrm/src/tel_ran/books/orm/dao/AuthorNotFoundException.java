package tel_ran.books.orm.dao;

public class AuthorNotFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String name;

	public AuthorNotFoundException(String name) {
		super("Author "+name+" not found");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
}
