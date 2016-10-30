package tel_ran.books.orm.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Address implements Serializable {
	private static final long serialVersionUID = 1L;
	String country;
	String sity;

	public Address(String country,String sity) {
		super();
		this.country = country;
		this.sity = sity;
	}
	
	public Address() {}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSity() {
		return sity;
	}

	public void setSity(String sity) {
		this.sity = sity;
	}

	@Override
	public String toString() {
		return "Address [country=" + country + ", sity=" + sity + "]";
	}
	
	
}
