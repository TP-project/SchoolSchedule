package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.elsys.Database.ContactTable;
import org.elsys.Database.TeacherTable;
import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestContactTable {
	
	@Test
	public void testCreateTable() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		boolean exc = false;
		Database d = new Database();
		ContactTable c = new ContactTable();
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
		ContactTable c = new ContactTable();
		c.insert("0878", 1);
		assertTrue(c.selectGsmNumber().size() == 1);
		c.delete("0878");
	}

	@Test
	public void testDelete() throws SQLException {
		ContactTable c = new ContactTable();
		c.insert("0878", 1);
		c.delete("0878");
		assertTrue(c.selectGsmNumber().size() == 0);
	}

	@Test
	public void testSelectGsmNumber() throws SQLException {
		ContactTable c = new ContactTable();
		c.insert("0878", 1);
		assertTrue(c.selectGsmNumber().get(0).compareTo("0878") == 0);
		c.delete("0878");
	}

	@Test
	public void testSelectTeacher() throws SQLException {
		TeacherTable t = new TeacherTable();
		ContactTable c = new ContactTable();
		c.insert("0878", 1);
		try {
			t.insert("test", "te");
		} catch (SQLException e) {
			t.create();
			t.insert("test", "te");
		}
		assertTrue(c.selectTeacher().get(0).compareTo(t.selectName().get(0)) == 0);
		c.delete("0878");
		t.delete("test");
	}

	@Test
	public void testGetID() throws SQLException {
		ContactTable c = new ContactTable();
		c.insert("0878", 1);
		c.insert("0877", 1);
		c.delete("0878");
		assertTrue(c.getID("0877") == 2);
		c.delete("0877");
	}

	@Test
	public void testGetIDWrongInfo() throws SQLException {
		ContactTable c = new ContactTable();
		c.insert("0878", 1);
		c.insert("0877", 1);
		c.delete("0878");
		assertTrue(c.getID("08fsaf77") == -1);
		c.delete("0877");
	}

	@Test
	public void testHaveString() throws SQLException {
		ContactTable c = new ContactTable();
		c.insert("0878", 1);
		assertTrue(c.haveString("0878"));
		assertFalse(c.haveString("tefsdfdsfasfst"));
		c.delete("0878");
	}

	@Test
	public void testDrop() {
		boolean exc = false;
		ContactTable c = new ContactTable();
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
