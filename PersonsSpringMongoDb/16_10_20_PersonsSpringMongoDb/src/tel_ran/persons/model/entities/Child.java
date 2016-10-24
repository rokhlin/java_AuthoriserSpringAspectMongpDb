package tel_ran.persons.model.entities;

public class Child extends Person {
	String garden;
	
	

	public Child(int id, String name, int birthYear, Address address,String garden) {
		super(id, name, birthYear, address);
		this.garden = garden;
	}



	@Override
	public String toString() {
		return "Child [garden=" + garden + ", id=" + id + ", name=" + name + ", birthYear=" + birthYear + ", address="
				+ address + "]";
	}



	public String getGarden() {
		return garden;
	}



	public void setGarden(String garden) {
		this.garden = garden;
	}



	
}
