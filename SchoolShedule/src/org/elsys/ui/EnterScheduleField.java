package org.elsys.ui;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class EnterScheduleField implements ModifyListener {

  private Text subject;
  private Text hours;
  private Display display = new Display();
  private Shell shell = new Shell(display);
 
  EnterScheduleField() {
    shell.setText("Objects"); 
    shell.setLayout(new GridLayout(3, true));
    
   
  }

  
  private void createContents(String labelText, Text text) {
	  new Label(shell, SWT.LEFT).setText(labelText);
	  text = new Text(shell, SWT.BORDER);
	  GridData data = new GridData(GridData.FILL_HORIZONTAL);
	  data.horizontalSpan = 2;
	  text.setLayoutData(data);
	  text.addModifyListener(this);

//    new Label(shell, SWT.LEFT).setText("Hours per week");
//    hours = new Text(shell, SWT.BORDER);
//    data = new GridData(GridData.FILL_HORIZONTAL);
//    data.horizontalSpan = 2;
//    hours.setLayoutData(data);
//    hours.addModifyListener(this);
//    
//   
//    new Label(shell, SWT.LEFT).setText("Teacher");
//    teacher = new Text(shell, SWT.BORDER);
//    data = new GridData(GridData.FILL_HORIZONTAL);
//    data.horizontalSpan = 2;
//    teacher.setLayoutData(data);
//    teacher.addModifyListener(this);
//    
//    new Label(shell, SWT.LEFT).setText("Cabinet");
//    cab = new Text(shell, SWT.BORDER);
//    data = new GridData(GridData.FILL_HORIZONTAL);
//    data.horizontalSpan = 2;
//    cab.setLayoutData(data);
//    cab.addModifyListener(this);
//    
 
  }
  
  public void open(){
	  createContents("Subject", subject);
	    createContents("Hourse per week", hours);
	    
	    shell.pack();
	    shell.open();
	    while (!shell.isDisposed()) {
	      if (!display.readAndDispatch()) {
	        display.sleep();
	      }
	    }
	    display.dispose();
	  
  }


  public void modifyText(ModifyEvent event) {
    // Remove all the listeners, so we don't enter any infinite loops
//	hours.removeModifyListener(this);
//    subject.removeModifyListener(this);
//    teacher.removeModifyListener(this);
//    cab.removeModifyListener(this);
    // Get the widget whose text was modified

    Text text = (Text) event.widget;

      // Get the modified text
     System.out.println(text.getText());

    // Add the listeners back
//    hours.addModifyListener(this);
//    subject.addModifyListener(this);
//    teacher.addModifyListener(this);
//    cab.addModifyListener(this);
  }


}
