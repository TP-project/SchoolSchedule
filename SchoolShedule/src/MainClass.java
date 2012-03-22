import java.io.IOException;

import com.google.gdata.util.ServiceException;


	public class MainClass {


		public static void main(String[] args) {

			Spreadsheet sp = new Spreadsheet();
			try {
				System.out.println("logging....");
				sp.login("kosyokk", "I'm15number");
				sp.loadSheet(0);
				Cell c= new Cell(3,4);
				//sp.removeCellErrorValue(c);
				//System.out.println(sp.getCellValue(c));
				//ArrayList<Cell> cells=sp.search(2, 3, 1, 4, "kosyo"); 
				
					System.out.println(sp.getCellValue(c));
				
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServiceException e) {
				e.printStackTrace();
			}
		}
	}