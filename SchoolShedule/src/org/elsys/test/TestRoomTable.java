package org.elsys.test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.elsys.Database.RoomTable;
import org.elsys.Database.TeacherTable;
import org.elsys.DatabaseConnection.Database;
import org.junit.Test;

public class TestRoomTable {

	@Test
	public void testCreateTable() throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, SQLException {
		boolean exc = false;
		Database d = new Database();
		RoomTable r = new RoomTable();
		d.createDatabaseConnection("test");
		try {
			r.create();
			r.create();
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
		RoomTable r = new RoomTable();
		r.insert("15");
		assertTrue(r.selectRoomNumber().size() == 1);
		r.delete("15");
	}

	@Test
	public void testDelete() throws SQLException {
		RoomTable r = new RoomTable();
		r.insert("15");
		r.delete("15");
		assertTrue(r.selectRoomNumber().size() == 0);
	}
	
	@Test
	public void testSelectRoomNumber() throws SQLException{
		RoomTable r = new RoomTable();
		r.insert("15");
		assertTrue(r.selectRoomNumber().get(0).compareTo("15")==0);
		r.delete("15");
	}
	
	@Test
	public void testGetID() throws SQLException{
		RoomTable r = new RoomTable();
		r.insert("15");
		r.insert("16");
		r.delete("15");
		assertTrue(r.getID("16")==2);
		r.delete("16");
	}
	
	@Test
	public void testGetIDWrongInfo() throws SQLException{
		RoomTable r = new RoomTable();
		r.insert("15");
		r.insert("16");
		r.delete("15");
		assertTrue(r.getID("1346")==-1);
		r.delete("16");
	}
	
	@Test
	public void testHaveString() throws SQLException{
		RoomTable r = new RoomTable();
		r.insert("15");
		assertTrue(r.haveString("15"));
		assertFalse(r.haveString("1335"));
		r.delete("15");
	}
	
	@Test
	public void testDrop(){
		boolean exc=false;
		RoomTable r = new RoomTable();
		try {
			r.drop();
			r.drop();
		} catch (SQLException e) {
			assertTrue(true);
			exc=true;
		}
		try {
			r.create();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(!exc) {
			fail();
		}
	}



}
