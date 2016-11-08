package tel_ran.persons.model.entities;

import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Employee extends Person {
	String company;
	int salary;
	
	public Employee(int id, String name, LocalDate birthDate, Address address,int salary,String company) {
		super(id, name, birthDate, address);
		this.salary = salary;
		this.company = company;
	}
	
	public Employee() {}//Required an empty constructor

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [company=" + company + ", salary=" + salary + ", id=" + id + ", name=" + name + ", birthYear="
				+ birthDate + ", address=" + address + "]";
	}
	
	
	
}
