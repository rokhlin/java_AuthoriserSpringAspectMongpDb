package tel_ran.databases.jdbc.tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tel_ran.databases.jdbc.DatabaseConnection;

public class DatabaseConnectionTests {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() throws Exception {
		DatabaseConnection databaseConnection = DatabaseConnection.getDatabase("root", "3221", null, null);
		DatabaseConnection databaseConnection2 = DatabaseConnection.getDatabase("root", "3221", null, null);
		assertTrue(databaseConnection == databaseConnection2);
		assertTrue(databaseConnection != null);
	}

}
