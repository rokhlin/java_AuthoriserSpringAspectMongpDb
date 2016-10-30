package tel_ran.books.orm.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Publisher {
	@Id
	String name;
	@Embedded
	Address address;
	
	
	public Publisher(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	public Publisher() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}
	
	


	@Override
	public String toString() {
		return "Publisher [name=" + name + ", address=" + address + "]";
	}

	public void setCountry(Address address) {
		this.address = address;
	}
	
	
	
}
