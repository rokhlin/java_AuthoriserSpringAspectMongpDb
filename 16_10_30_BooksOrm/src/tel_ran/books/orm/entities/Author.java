package tel_ran.books.orm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Author {
	@Id
	String name;
	
	@Embedded
	Address address;
	
	int birthYear;

	public Author(String name, Address address, int birthYear) {
		super();
		this.name = name;
		this.address = address;
		this.birthYear = birthYear;
	}
	
	public Author() {}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public int getBirthYear() {
		return birthYear;
	}

	public void setBirthYear(int birthYear) {
		this.birthYear = birthYear;
	}

	@Override
	public String toString() {
		return "Author [name=" + name + ", address=" + address + ", birthYear=" + birthYear + "]";
	}
	

	
}
