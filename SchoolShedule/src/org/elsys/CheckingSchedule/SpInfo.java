package org.elsys.CheckingSchedule;

import org.elsys.Spreadsheet.Cell;

public class SpInfo {
	public static final int rowForYear = 1;
	public static final int colForYear = 14;
	public static final int rowForTerm = 1;
	public static final int colForTerm = 13;
	public static final int classRow = 2;
	public static final int firstClassCol = 4;
	public static final int secondClassCol = 13;
	public static final int thirdClassCol = 22;
	public static final int fourthClassCol = 31;
	public static final int startClassRow = 2;
	public static final int endClassRow = 50;
	public static final int firstClassSubjectsCol = 4;
	public static final int secondClassSubjectsCol = 13;
	public static final int thirdClassSubjectsCol = 22;
	public static final int fourthClassSubjectsCol = 31;
	public static final Cell firstClass = new Cell(classRow, firstClassCol);
	public static final Cell secondClass = new Cell(classRow, secondClassCol);
	public static final Cell thirdClass = new Cell(classRow, thirdClassCol);
	public static final Cell fourthClass = new Cell(classRow, fourthClassCol);
	public static final Cell year = new Cell(rowForYear, colForYear);
	public static final Cell termCell = new Cell(rowForTerm, colForTerm);
}
