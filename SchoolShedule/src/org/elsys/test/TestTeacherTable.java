package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.elsys.Database.TeacherTable;
import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestTeacherTable extends Database{

	@Test
	public void testCreateTable() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		boolean exc=false;
		Database d = new Database();
		TeacherTable t = new TeacherTable();
		d.createDatabaseConnection("test");	
		try {			
			t.create();
			t.create();
		} catch (SQLException e) {
			exc=true;
			assertTrue(true);
		}
		if(!exc) {
			fail();
		}
		
	}

	@Test
	public void testInsert() throws SQLException{
		TeacherTable t = new TeacherTable();
		t.insert("test","te");
		assertTrue(t.selectName().size()==1);
		t.delete("test");
	}
	
	@Test
	public void testDelete() throws SQLException{
		TeacherTable t = new TeacherTable();
		t.insert("test","te");
		t.delete("test");
		assertTrue(t.selectName().size()==0);
	}
	
	@Test
	public void testSelectName() throws SQLException{
		TeacherTable t = new TeacherTable();
		t.insert("test","te");
		assertTrue(t.selectName().get(0).compareTo("test")==0);
		t.delete("test");
	}
	
	@Test
	public void testSelectShortName() throws SQLException{
		TeacherTable t = new TeacherTable();
		t.insert("test","te");
		assertTrue(t.selectShortName().get(0).compareTo("te")==0);
		t.delete("test");
	}
	
	@Test
	public void testGetID() throws SQLException{
		TeacherTable t = new TeacherTable();
		t.insert("test","te");
		t.insert("test1","te1");
		t.delete("test");
		assertTrue(t.getID("test1")==2);
		t.delete("test1");
	}
	
	@Test
	public void testGetIDWrongInfo() throws SQLException{
		TeacherTable t = new TeacherTable();
		t.insert("test","te");
		t.insert("test1","te1");
		t.delete("test");
		assertTrue(t.getID("tefsdfsst1")==-1);
		t.delete("test1");
	}
	
	@Test
	public void testHaveString() throws SQLException{
		TeacherTable t = new TeacherTable();
		t.insert("test","te");
		assertTrue(t.haveString("test"));
		assertFalse(t.haveString("tefsdfdsfasfst"));
		t.delete("test");
	}
	
	@Test
	public void testDrop(){
		boolean exc=false;
		TeacherTable t = new TeacherTable();
		try {
			t.drop();
			t.drop();
		} catch (SQLException e) {
			assertTrue(true);
			exc=true;
		}
		try {
			t.create();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!exc) {
			fail();
		}
	}
	
	
}
