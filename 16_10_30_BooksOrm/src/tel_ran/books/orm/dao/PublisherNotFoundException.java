package tel_ran.books.orm.dao;

public class PublisherNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	String name;

	public PublisherNotFoundException(String name) {
		super("Publisher "+name+" not found");
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

}
