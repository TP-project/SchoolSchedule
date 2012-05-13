package org.elsys.test;

import static org.junit.Assert.*;

import org.elsys.Spreadsheet.Cell;
import org.junit.Test;

public class TestCell {

	@Test
	public void testCellGetRow() {
		Cell c = new Cell(4, 5);
		assertEquals(c.getRow(),4);
	}
	
	@Test
	public void testCellGetCol() {
		Cell c = new Cell(4, 5);
		assertEquals(c.getCol(),5);
	}

	@Test
	public void testCellSetRow() {
		Cell c = new Cell(4, 5);
		c.setRow(3);
		assertEquals(c.getRow(),3);
	}
	
	@Test
	public void testCellSetCol() {
		Cell c = new Cell(4, 5);
		c.setCol(3);
		assertEquals(c.getCol(),3);
	}
	
	@Test
	public void testCellConstructor() {
		Cell c = new Cell(4, 5);
		assertEquals(c.getCol(),5);
		assertEquals(c.getRow(),4);
	}
	
	public void testLoginNameAndPassword() {
		
	}
}
