	import org.odftoolkit.odfdom.doc.OdfDocument;
	import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
	import org.odftoolkit.odfdom.doc.table.OdfTable;
	import org.odftoolkit.odfdom.doc.table.OdfTableCell;

	import java.util.List;
	import java.util.Iterator;

	public class MainClass {

		public static void main(String[] args) {

			try {
				OdfDocument ods = OdfSpreadsheetDocument
						.loadDocument("/home/kosyo/test.ods");

				List<OdfTable> tables = ods.getTableList();
				Iterator<OdfTable> it = tables.iterator();
				

				OdfTable tbl = ods.getTableByName(it.next().getTableName());
				int cols = tbl.getColumnCount();
				int rows = tbl.getRowCount();

				System.out.println("Cols = " + cols + " Rows = " + rows);

				OdfTableCell cell;
				for (int i = 0; i < rows; i++) {
					cell = tbl.getCellByPosition(0, i);
					System.out.print(cell.getDisplayText() + "   ");
					cell = tbl.getCellByPosition(1, i);
					System.out.println(cell.getDisplayText());
				}
				
			} catch (Exception e) {
				System.out.println("Exception");
				System.out.println(e.toString());
			}
		}
	}