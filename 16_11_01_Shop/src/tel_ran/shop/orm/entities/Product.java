package tel_ran.shop.orm.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "01_11_Products")
public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	long barCode;
	String name;
	
	@ManyToMany(mappedBy="owners",fetch = FetchType.EAGER)
	
	List<Person> productOwners;
	
	@ManyToMany(mappedBy="renties",fetch = FetchType.EAGER)
	
	List<Person> productRenties;
	
	

	public Product(long barCode, String name) {
		super();
		this.barCode = barCode;
		this.name = name;
	}
	public Product() {
	}
	
	
	public long getBarCode() {
		return barCode;
	}

	public void setBarCode(long barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Person> getProductOwners() {
		return productOwners;
	}

	public void setProductOwners(List<Person> productOwners) {
		this.productOwners = productOwners;
	}

	public List<Person> getProductRenties() {
		return productRenties;
	}

	public void setProductRenties(List<Person> productRenties) {
		this.productRenties = productRenties;
	}
	@Override
	public String toString() {
		return "Product [barCode=" + barCode + ", name=" + name + ", productOwners=" + productOwners
				+ ", productRenties=" + productRenties + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (barCode ^ (barCode >>> 32));
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
		Product other = (Product) obj;
		if (barCode != other.barCode)
			return false;
		return true;
	}
	
	
	
	
}
