import java.util.ArrayList;

import org.elsys.Database.TeacherTable;

	public class MainClass {


		public static void main(String[] args) {
			TeacherTable table=new TeacherTable();
			table.insert("name5");
//			table.insert("name2");
//			table.insert("name3");
			ArrayList<String> names=table.selectName();
			System.out.println(names.size());
		}
	}