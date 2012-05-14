package org.elsys.test;

import static org.junit.Assert.*;

import java.io.IOException;

import org.elsys.Spreadsheet.Cell;
import org.elsys.Spreadsheet.Spreadsheet;
import org.junit.Test;

import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

public class TestSpreadsheet {

	public Spreadsheet fixture() throws AuthenticationException {
		Spreadsheet s = new Spreadsheet();
		s.login("tuesschedule", "schoolSchedule");
		return s;
	}
	
	@Test
	public void testCorrectLogin() {
		try {
			fixture();
		} catch (AuthenticationException e) {
			fail();
		}
		assertTrue(true);
	}

	@Test
	public void testIncorrectLogin() {
		Spreadsheet s = new Spreadsheet();
		boolean incorrect = false;
		try {
			s.login("dsdsadd", "ffsdfdsf");
		} catch (AuthenticationException e) {
			incorrect = true;
			assertTrue(true);
		}
		if (!incorrect) {
			fail();
		}

	}

	@Test
	public void testLoadSheet() throws IOException, ServiceException {
		Spreadsheet s=fixture();
		assertNull(s.getCellFeedUrl());
		s.loadSheet(0);
		assertNotNull(s.getCellFeedUrl());
	}

	@Test
	public void testGetAllSheetsNotNull() throws IOException, ServiceException {
		Spreadsheet s = fixture();
		
		assertNotNull(s.getAllSheets());
	}
	
	@Test
	public void testGetAllSheets() throws IOException, ServiceException {
		Spreadsheet s = fixture();
		
		assertTrue(s.getAllSheets().size()>1);
	}
	
	@Test
	public void testGetSheetNumberRightInfo() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		
		assertFalse(s.getSheetNumber("10", "2012/2013", "1")==-1);
	}
	
	@Test
	public void testGetSheetNumberWrongInfo() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		
		assertEquals(s.getSheetNumber("143", "201s2/2013", "f1"),-1);
	}

	@Test
	public void testSetCellValue() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		s.loadSheet(0);
		s.setCellValue(new Cell(1,1),"test");
		assertTrue(s.getCellValue(new Cell(1,1)).compareTo("test")==0);
	}
	
	@Test
	public void testSetCellErrorValue() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		s.loadSheet(0);
		Cell c= new Cell(1,1);
		s.setCellValue(c,"test");
		s.setCellErrorValue(c);
		
		assertTrue(s.getCellValue(c).compareTo("test!!!")==0);
	}
	
	@Test
	public void testRemoveCellErrorValue() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		s.loadSheet(0);
		Cell c = new Cell(1,1);
		s.setCellValue(c,"test");
		s.setCellErrorValue(c);
		s.removeCellErrorValue(c);
		assertTrue(s.getCellValue(new Cell(1,1)).compareTo("test")==0);
	}
	
	@Test
	public void testGetCellValueNotEmpty() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		s.loadSheet(0);
		Cell c = new Cell(1,1);
		s.setCellValue(c,"test");
		
		assertNotNull(s.getCellValue(c));
	}
	
	@Test
	public void testGetCellValueIfEmpty() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		s.loadSheet(0);
		Cell c = new Cell(1,1);
		s.setCellValue(c,"");
		
		assertNull(s.getCellValue(c));
	}
	
	@Test
	public void testSearch() throws IOException, ServiceException{
		Spreadsheet s = fixture();
		s.loadSheet(0);
		Cell c = new Cell(1,1);
		s.setCellValue(c,"test");
		assertTrue(s.search(1, 1, 1, 1, "test").size()==1);
		s.setCellValue(c,"");
	}	
}
