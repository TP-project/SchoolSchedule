package org.elsys.ui;

import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.elsys.Database.ClassTable;
import org.elsys.Database.ContactTable;
import org.elsys.Database.GeneralTable;
import org.elsys.Database.RoomTable;
import org.elsys.Database.ScheduleTable;
import org.elsys.Database.SpecificationTable;
import org.elsys.Database.TeacherTable;
import org.elsys.DatabaseConnection.Database;
import org.elsys.Spreadsheet.Spreadsheet;

import com.google.gdata.util.AuthenticationException;

public class Menu {

	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);
	
	
	public Menu() {
		openDataBase();
		// open();
	}

	
	private void login() {
		Button logIn = new Button(shell, SWT.PUSH);
		logIn.setText("Вход");
		logIn.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				new LogIn();
			}
		});
	}

	private void teachers() {
		Button teachers = new Button(shell, SWT.PUSH);
		teachers.setText("Въведи информация за учителите");
		teachers.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				new Teachers();
			}
		});
	}

	private void schedule() {
		Button schedule = new Button(shell, SWT.PUSH);
		schedule.setText("Въведи учебния план");
		schedule.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				new Year();
			}
		});
	}
	
	private void checking() {
		Button ch = new Button(shell, SWT.PUSH);
		ch.setText("Проверка на въвевдането на програмата");
		ch.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				new Checking ();
			}
		});
	}

	private void shellHar() {
		shell.setLayout(new RowLayout());
		shell.setLocation(100, 50);
	}

	private void init() {
		login();
		teachers();
		schedule();
		checking();
		shellHar();
	}

	public void openDataBase() {
		Database db = new Database();
		try {
			db.createDatabaseConnection();
			open();
		} catch (SQLException e) {
			display.dispose();
			new Message(
					"Програмата вече е отворена. Моля затворете всички прозорци и опитайте отново.", 1);
			// e.printStackTrace();
		} catch (Exception e) {
			new Message("Възникна неочаквана грешка!");
			e.printStackTrace();
		}

	}
	

	public void open() {

		init();
		shell.pack();
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();

	}

}
