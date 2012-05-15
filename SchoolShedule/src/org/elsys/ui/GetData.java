package org.elsys.ui;

import org.eclipse.swt.widgets.Display;
import org.elsys.Spreadsheet.Spreadsheet;

public class GetData {
	static private Spreadsheet sheet = new Spreadsheet();
	static private Display display = new Display();
	
	public Display getDisplay(){
		return display;
	}
	public Spreadsheet getSheet(){
		return sheet;
	} 
}
