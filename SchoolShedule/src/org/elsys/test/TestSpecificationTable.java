package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.elsys.Database.SpecificationTable;
import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestSpecificationTable {

	@Test
	public void testCreateTable() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		boolean exc = false;
		Database d = new Database();
		SpecificationTable s = new SpecificationTable();
		d.createDatabaseConnection("test");
		try {
			s.create();
			s.create();
		} catch (SQLException e) {
			exc = true;
			assertTrue(true);
		}
		if (!exc) {
			fail();
		}
	}

	@Test
	public void testInsert() throws SQLException {
		SpecificationTable s = new SpecificationTable();
		s.insert("test");
		assertTrue(s.selectSpecification().size() == 1);
		s.delete("test");
	}

	@Test
	public void testDelete() throws SQLException {
		SpecificationTable s = new SpecificationTable();
		s.insert("test");
		s.delete("test");
		assertTrue(s.selectSpecification().size() == 0);
	}

	@Test
	public void testSelectSpecification() throws SQLException {
		SpecificationTable s = new SpecificationTable();
		s.insert("test");
		assertTrue(s.selectSpecification().get(0).compareTo("test") == 0);
		s.delete("test");
	}

	@Test
	public void testGetID() throws SQLException {
		SpecificationTable s = new SpecificationTable();
		s.insert("test");
		s.insert("test1");
		s.delete("test");
		assertTrue(s.getID("test1") == 2);
		s.delete("test1");
	}

	@Test
	public void testGetIDWrongInfo() throws SQLException {
		SpecificationTable s = new SpecificationTable();
		s.insert("test");
		s.insert("test1");
		s.delete("test");
		assertTrue(s.getID("1346") == -1);
		s.delete("test1");
	}

	@Test
	public void testHaveString() throws SQLException {
		SpecificationTable s = new SpecificationTable();
		s.insert("test");
		assertTrue(s.haveString("test"));
		assertFalse(s.haveString("1335"));
		s.delete("test");
	}

	@Test
	public void testDrop() {
		boolean exc = false;
		SpecificationTable s = new SpecificationTable();
		try {
			s.drop();
			s.drop();
		} catch (SQLException e) {
			assertTrue(true);
			exc = true;
		}
		try {
			s.create();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!exc) {
			fail();
		}
	}
}
