package tel_ran.employee.tests;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import tel_ran.employee.dao.EmployeesOrm;
import tel_ran.employee.entities.Employee;

public class EmpTest {
	static AbstractApplicationContext context;
	static EmployeesOrm orm;
	
	public static final int MIN_SALARY=5000;
	public static final int MAX_SALARY=50000;
	@Before
	public void setUp() throws Exception {
		context = new FileSystemXmlApplicationContext("beans.xml");
		orm = context.getBean(EmployeesOrm.class);
		init();
		
		
	}

	private int generate(int min, int max) {
		Random rnd = new Random();
		return min + rnd.nextInt(max-min);
	}
	private void init() {
		Employee[] employees = {new Employee(0, "GenDirector", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(1, "GLavBUHHHHHH", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(2, "LineManager1", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(3, "LineManager2", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(4, "Employee1", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(5, "Employee2", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(6, "Employee3", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(7, "Employee4", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(8, "Employee5", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(9, "Employee6", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(10, "Employee7", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(11, "Employee8", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(12, "Employee9", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(13, "Employee10", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(14, "Manager1", generate(MIN_SALARY, MAX_SALARY)),
				new Employee(15, "Manager2", generate(MIN_SALARY, MAX_SALARY))
				};
		orm.addGeneralManager(employees[0]);
		
	}

	

	@Test
	public void testAddGeneralManager() {
		
		assertEquals(new Employee(0, "GenDirector", generate(MIN_SALARY, MAX_SALARY)), orm.getEmployee(0));
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception {	
		context.close();
	}
}
