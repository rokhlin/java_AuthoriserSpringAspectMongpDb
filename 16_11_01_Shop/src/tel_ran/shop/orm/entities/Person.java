package tel_ran.shop.orm.entities;

import java.io.Serializable;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "01_11_Persons")
public class Person implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	long id;
	
	String name;
	
	@ManyToMany
	@JoinTable(name = "Owners")
	List<Product> owners;
	
	@ManyToMany
	@JoinTable(name = "Renties")
	List<Product> renties;

	public Person() {
	}
	
	public Person(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Product> getOwners() {
		return owners;
	}

	public void setOwners(List<Product> owners) {
		this.owners = owners;
	}

	public List<Product> getRenties() {
		return renties;
	}

	public void setRenties(List<Product> renties) {
		this.renties = renties;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", owners=" + owners + ", renties=" + renties + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
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
	
	

}
