package tel_ran.persons.model.entities;

import java.time.LocalDate;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //
@Table(name="bsh_persons") //for setting table name not equal className
public abstract class Person {
	@Id
	int id;
	
	String name;
	
	LocalDate birthDate;
	
	@Embedded   //Show for JPA our non primitive class in our class needs to add @Embeddable annotation
 	Address address;
	
	public Person() {
	}
	
	
	public Person(int id, String name, LocalDate birthDate, Address address) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.address = address;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	
	public abstract String toString();

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthYear() {
		return birthDate;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
	
}