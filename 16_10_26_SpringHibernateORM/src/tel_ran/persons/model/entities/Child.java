package tel_ran.persons.model.entities;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Child extends Person {
	String garden;
	
	
	public Child() {} //Required an empty constructor
	
	public Child(int id, String name, LocalDate birthDate, Address address,String garden) {
		super(id, name, birthDate, address);
		this.garden = garden;
	}



	@Override
	public String toString() {
		return "Child [garden=" + garden + ", id=" + id + ", name=" + name + ", birthYear=" + birthDate + ", address="
				+ address + "]";
	}



	public String getGarden() {
		return garden;
	}



	public void setGarden(String garden) {
		this.garden = garden;
	}



	
}
