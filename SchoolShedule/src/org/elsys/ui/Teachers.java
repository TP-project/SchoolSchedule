package org.elsys.ui;

import java.sql.SQLDataException;
import java.sql.SQLException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.elsys.Database.ContactTable;
import org.elsys.Database.TeacherTable;

public class Teachers{

	private Display display = new GetData().getDisplay();
	private Shell shell = new Shell(display);
	
	public Teachers() {
		shell.setText("Учители");
		shell.setLayout(new GridLayout(6, false));
		shell.setLocation(270, 120);

		open();

	}

	private void createContents() {

		new Label(shell, SWT.LEFT).setText("Име на учител:");
		new Label(shell, SWT.LEFT).setText("Инициали:");
		new Label(shell, SWT.LEFT).setText("Телефонен номер:");
		new Label(shell, SWT.LEFT).setText(" ");
		new Label(shell, SWT.LEFT).setText(" ");
		new Label(shell, SWT.LEFT).setText(" ");

	}

	public void createLine() {
		final Text name = new Text(shell, SWT.BORDER);
		final Text initials = new Text(shell, SWT.BORDER);
		final Text phone = new Text(shell, SWT.BORDER);
		final TeacherTable t = new TeacherTable();
		final ContactTable c = new ContactTable();

		Button save = new Button(shell, SWT.PUSH);
		save.setText("Запиши");
		save.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					if (name.getText().equals("")) {
						new Message("Името на учителя не може да бъде празно.");
					}else if (!t.haveString(name.getText())) {
						t.insert(name.getText(), initials.getText());
						c.insert(phone.getText(), t.getID(name.getText()));
					} else {
						new Message("Този учител вече е въведен!");
					}

				} catch (SQLDataException e) {
					new Message(
							"Името трябва да бъде не по-дълго от 30 символа, инициалите - 5, а телефонният номер - 13." );
				} catch (SQLException e) {
					e.printStackTrace();

				}
				shell.pack();
			}
		});
		makeDeleteButton( name, phone, initials);
		makeNewButton();
	}

	public void makeDeleteButton( final Text name,
			final Text phone, final Text initials) {
		final TeacherTable t = new TeacherTable();
		final ContactTable c = new ContactTable();
		Button del = new Button(shell, SWT.PUSH);
		del.setText("Изтрий");
		del.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent event) {
				try {
					t.delete(name.getText());
					c.delete(phone.getText());
				} catch (SQLException e) {
					// TODO??
				}
				name.setText("");
				initials.setText("");
				phone.setText("");
				shell.pack();
			}
		});

	}

	public void makeNewButton() {
		final Button newB = new Button(shell, SWT.PUSH);
		newB.setText("Нов запис");
		newB.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent event) {
				createLine();
				newB.setVisible(false);
				shell.pack();
			}
		});

	}

	public void open() {
		createContents();
		createLine();
		shell.pack();
		shell.open();
	}


}
