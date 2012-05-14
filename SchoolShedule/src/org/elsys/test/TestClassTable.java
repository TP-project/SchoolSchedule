package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.elsys.Database.ClassTable;
import org.elsys.Database.SpecificationTable;
import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestClassTable {

	@Test
	public void testCreateTable() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		boolean exc = false;
		Database d = new Database();
		ClassTable c = new ClassTable();
		d.createDatabaseConnection("test");
		try {
			c.create();
			c.create();
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
		ClassTable c = new ClassTable();
		c.insert("14v", 1);
		assertTrue(c.selectClass().size() == 1);
		c.delete("14v");
	}

	@Test
	public void testDelete() throws SQLException {
		ClassTable c = new ClassTable();
		c.insert("14v",1);
		c.delete("14v");
		assertTrue(c.selectClass().size() == 0);
	}

	@Test
	public void testSelectClass() throws SQLException {
		ClassTable c = new ClassTable();
		c.insert("14v",1);
		assertTrue(c.selectClass().get(0).compareTo("14v") == 0);
		c.delete("14v");
	}

	@Test
	public void testSelectSpecification() throws SQLException {
		ClassTable c = new ClassTable();
		SpecificationTable s = new SpecificationTable();
		try{
			s.insert("testspec");
		} catch(SQLException e) {
			s.create();
			s.insert("testspec");
		}
		c.insert("14v",1);
		assertTrue(c.selectSpecification().get(0).compareTo("testspec") == 0);
		c.delete("14v");
	}
	
	@Test
	public void testGetID() throws SQLException {
		ClassTable c = new ClassTable();
		c.insert("15v",1);
		c.insert("16v",1);
		c.delete("15v");
		assertTrue(c.getID("16v") == 2);
		c.delete("16v");
	}

	@Test
	public void testGetIDWrongInfo() throws SQLException {
		ClassTable c = new ClassTable();
		c.insert("14v",1);
		c.insert("16v",1);
		c.delete("14v");
		assertTrue(c.getID("1346") == -1);
		c.delete("16v");
	}

	@Test
	public void testHaveString() throws SQLException {
		ClassTable c = new ClassTable();
		c.insert("14v",1);
		assertTrue(c.haveString("14v"));
		assertFalse(c.haveString("1335"));
		c.delete("14v");
	}

	@Test
	public void testDrop() {
		boolean exc = false;
		ClassTable c = new ClassTable();
		try {
			c.drop();
			c.drop();
		} catch (SQLException e) {
			assertTrue(true);
			exc = true;
		}
		try {
			c.create();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (!exc) {
			fail();
		}
	}
}
