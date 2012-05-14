package org.elsys.Spreadsheet;

import com.google.gdata.client.spreadsheet.CellQuery;
import com.google.gdata.client.spreadsheet.FeedURLFactory;
import com.google.gdata.client.spreadsheet.SpreadsheetService;
import com.google.gdata.data.spreadsheet.CellEntry;
import com.google.gdata.data.spreadsheet.CellFeed;
import com.google.gdata.data.spreadsheet.SpreadsheetEntry;
import com.google.gdata.data.spreadsheet.SpreadsheetFeed;
import com.google.gdata.data.spreadsheet.WorksheetEntry;
import com.google.gdata.util.AuthenticationException;
import com.google.gdata.util.ServiceException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.elsys.CheckingSchedule.SpInfo;

public class Spreadsheet {
	private SpreadsheetService service;
	private URL cellFeedUrl;
	private FeedURLFactory factory;

	public Spreadsheet() {
		this.service=new SpreadsheetService("Spreadsheet");
		this.factory = FeedURLFactory.getDefault();
		
	}
	
	public void login(String username, String password)
			throws AuthenticationException {
		
		service.setUserCredentials(username, password);
	}
	
	public void loadSheet(int worksheetNumber) throws IOException,
			ServiceException {
		SpreadsheetFeed feed = service.getFeed(
				factory.getSpreadsheetsFeedUrl(), SpreadsheetFeed.class);
		SpreadsheetEntry spreadsheet = feed.getEntries().get(0);

		cellFeedUrl=spreadsheet.getWorksheets().get(worksheetNumber).getCellFeedUrl();
	} 	
	
	public List<WorksheetEntry> getAllSheets() throws IOException, ServiceException {
		SpreadsheetFeed feed = service.getFeed(
				factory.getSpreadsheetsFeedUrl(), SpreadsheetFeed.class);
		SpreadsheetEntry spreadsheet = feed.getEntries().get(0);

		return spreadsheet.getWorksheets();

	}
	
	public int getSheetNumber(String schoolClass) {
		try {
			for (int i = 0; i < getAllSheets().size(); i++) {
				loadSheet(i);
				if(getCellValue(SpInfo.firstClass)!=null) {
					if(getCellValue(SpInfo.firstClass).substring(0, 2).compareTo(schoolClass)==0){
						return i;
					}				
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

	
	public void setCellValue(Cell cell,String value)
			throws IOException, ServiceException {

		CellEntry newEntry = new CellEntry(cell.getRow(),cell.getCol(),value);
		service.insert(cellFeedUrl, newEntry);
	}
	
	public void setCellErrorValue(Cell cell)
			throws IOException, ServiceException {
		String errorValue=getCellValue(cell)+" !!!!!!";
		CellEntry newEntry = new CellEntry(cell.getRow(),cell.getCol(),errorValue);
		service.insert(cellFeedUrl, newEntry);
	}
	
	public void removeCellErrorValue(Cell cell)
			throws IOException, ServiceException {
		String value=getCellValue(cell);
		String normalValue=value.replaceAll("!", " ");
		CellEntry newEntry = new CellEntry(cell.getRow(),cell.getCol(),normalValue);
		service.insert(cellFeedUrl, newEntry);
	}


	public String getCellValue(Cell cell) throws IOException, ServiceException {
		CellFeed feed = service.getFeed(cellFeedUrl, CellFeed.class);
		String cellValue = null;
		for (CellEntry entry : feed.getEntries()) {
		     if ((entry.getCell().getRow()==cell.getRow()) && (entry.getCell().getCol()==cell.getCol())) {
		    	 cellValue= entry.getCell().getValue();
		     } 
		   }
		return cellValue;
	}


	public ArrayList<Cell> search(int minRow, int maxRow, int minCol,int maxCol,String fullTextSearchString) throws IOException,
			ServiceException {
		CellQuery query = new CellQuery(cellFeedUrl);
		query.setMinimumRow(minRow);
		query.setMaximumRow(maxRow);
		query.setMinimumCol(minCol);
		query.setMaximumCol(maxCol);
		query.setFullTextQuery(fullTextSearchString);
		CellFeed feed = service.query(query, CellFeed.class);
		ArrayList<Cell> cells = new ArrayList<Cell>();
		for(CellEntry cell: feed.getEntries()) {
			Cell c=new Cell(cell.getCell().getRow(),cell.getCell().getCol());
			cells.add(c);
		}
		return cells;
	}

}