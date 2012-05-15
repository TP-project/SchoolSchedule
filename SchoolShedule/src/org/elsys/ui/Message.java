package org.elsys.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class Message {

	
	
	public Message(String text, int i){
		Display display = new Display(); 
		Shell shell = new Shell(display);
		  shell.setBounds(300, 300, 600, 100);
		  shell.setLayout(new GridLayout(1, false));
		  Label l = new Label(shell, SWT.LEFT);
		  l.setText(text);
		  open(shell);
		  dis(shell, display);
		 
	}
	  public Message(String text) {  
		   Display display = new GetData().getDisplay();
		   Shell shell = new Shell(display);
		  shell.setBounds(300, 300, 600, 100);
		  shell.setLayout(new GridLayout(1, false));
		  Label l = new Label(shell, SWT.LEFT);
		  l.setText(text);
		  open(shell);
	  }
	  
	  public void dis(Shell shell, Display display){
	   while (!shell.isDisposed()) {
			  if (!display.readAndDispatch()) {
		        display.sleep();
		      }
		  }
		  display.dispose();
	  }
	  
	  public void open(final Shell shell) {
		  Button ok = new Button(shell, SWT.PUSH);
		  ok.setText("Добре");
		    
		  ok.addSelectionListener(new SelectionAdapter() {
			  public void widgetSelected(SelectionEvent event) {
				  shell.close();
			  }    
		  });
		   
		  shell.open();
		
		
	  }
}