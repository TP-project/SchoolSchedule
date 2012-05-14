package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestDatabase extends Database{

	@Test
	public void testDatabaseConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Database d = new Database();
		d.createDatabaseConnection();
		assertNotNull(d.conn);
	}

}
