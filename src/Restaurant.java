/*******
 * Adjust the menu with different menu panels
 *******/


import javax.swing.*;
import java.awt.event.*; 

public class Restaurant_v5 implements ActionListener, Runnable{
	JFrame f;
	JButton b;
	JDialog d;
	JTable jt;
	JTextField tf;
	JScrollPane sp;
	JLabel l, l1, l2, ld;
	JPanel jp;
	int m, n;
	int hour, minute, second;
	
	Customer_v5 temp;

	
	Thread t = null;
	
	// Constructor
	Restaurant_v5(Customer_v5 c) {
		
		temp = c;
		// Create Restaurant window
		f = new JFrame("Restaurant");
		// Set close Restaurant window
		f.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		// Create dialog d
		d = new JDialog();
		// Create text field tf
		tf = new JTextField();
		// Create label l, l1, l2
		l = new JLabel();
		l1 = new JLabel();
		l2 = new JLabel();
		
		
		
		
		
		// Initialize row and column for the table
		m = c.i;		// Copy number of row from Customer window
		n = c.column;	// Copy number of column from Customer window
		String row[][] = new String[m][n];	// Create 2-dimensional string array data[][]
		String column[] = {"Food", "Price", "Quantity"};	// Create column headers
		// Create table jt with rows are data and columns are column
		jt = new JTable(row,column);
		//create JScrollPane for table jt
	  	JScrollPane sp = new JScrollPane(jt);
	  	//f.add(sp);
	  	
	  	
	  	
	  	
		// Copy table from Customer window to Restaurant window's table
	  	for (int i = 0; i < m; i++) {
	  		for (int j = 0; j < n; j++) {
	  			row[i][j] = c.data[i][j];
	  		}
	  	}
	  	//f.add(sp);
	  	// does not work
		
		
	    
	    //int tn = 1;
	    //JLabel l1, l2;
	    //l1.setText("Table number: " + Integer.toString(tn));
		//l1.setBounds(5,120,150,20);
		//f.add(l1);
		// Set text for l2
//	    l2.setText("Total time: ");
	    // Set position for label l2
	    l2.setBounds(5,150,90,20);
	    // Add label l2 to frame f
//	    f.add(l2);
	    
	    
	    // Set position for text field to input time
//	    tf.setBounds(100, 150, 30, 20);
	    
	    
	    
	    // Create the Send button to send time back to Customer window
	    b = new JButton("Send");
	    // Set position for the Send button
	    b.setBounds(60, 230, 120, 30);
	    // Add Restaurant object to listen to the click on the Send button
	    b.addActionListener(this);
	    
/****************************************************
 * 			The Following part is done by 
 * 				Mustapha Azeggaghe
 ****************************************************/
	     JPanel jp = new JPanel();
	     JPanel jpbtf = new JPanel();
	     
	     l2.setText("Please type waiting time: ");
	     tf.setPreferredSize(l2.getSize());
	  
	     jp.add(sp);
	     jpbtf.add(l2);
	     jpbtf.add(b);
	     jpbtf.add(tf);
	     jp.add(jpbtf);
	     f.add(jp);
	     
	     
	     
	     
	    
	    
	    /*************************************************
	     * 						End of part
	     **************************************************/
	    // Add the Send button on the frame f
//	    f.add(b);
	    
	    // Add the text field tf on the frame f
//	    f.add(tf);
	   
	    //Add the scroll pane sp on the frame f
//	    f.add(sp);
	    
	    // Set position and size form frame f
	    f.setBounds(800,100,600,550);
	   
	    // Set the frame f visible
	    f.setVisible(true);
	}
	
	// Check if the text field input is numeric
	boolean checkDigit(String s) {
		try {
			Integer.parseInt(s);
		}
		catch (Exception e){
			return false;
		}
		return true;
	}
	
	// process negative time input
	void negativeTimeInput() {
		
		tf.setText("");		// Clear the text field for next input
		d.setTitle("Invalid");
		d.setBounds(850, 170, 350, 100);
		l.setText("Please input the correct time!");
		l.setBounds(15, 15, 500, 40);
		d.setVisible(true);
		d.add(l);
		d.setLayout(null);
	}
	
	// process valid time input
	void validTimeInput() {
		JLabel l1 = new JLabel("Total cooking time was sent to Customer!");
		l1.setBounds(60,150,250,70);
		l1.setVisible(true);
		f.add(l1);
		l2.setText("Remaining time: ");
		l2.setBounds(15,150,150,20);
		tf.setVisible(false);
		tf.setText("");
	}
	
	// process invalid time input
	void invalidTimeInput() {
		
		tf.setText("");		// Clear the text field for next input
		d.setTitle("Not digit");
		d.setBounds(850, 170, 200, 100);
		l.setText("Not digit!");
		l.setBounds(20, 16, 100, 40);
		d.setVisible(true);
		d.add(l);
		d.setLayout(null);
	}
	
	void digitalWatch(int a) {
		hour = a/60;
		minute = a%60;
		second = 0;
		
		t = new Thread(this);
		t.start();	
	}
	
	public void run() {
		try {
			while (hour != 0 || minute != 0 || second != 0){
				if (second == 0) {
					second = 60;
					
					if (minute == 0) {
						minute = 60;
						hour -= 1;
					}				
					minute -= 1;
				}
				second -= 1;
				printTime();
				t.sleep(1000);
			}
		} catch (Exception e) {}
	}
	
	public void printTime() {
		
		b.setText(hour + "h:" + minute + "m:" + second + "s");
		b.setBounds(140, 145, 130, 30);
		
		/*
		b.setVisible(false);
		ld = new JLabel();
		ld.setText(hour + "h:" + minute + "m:" + second + "s");
		ld.setBounds(140, 145, 130, 30);
		ld.setVisible(true);
		f.add(ld);
		*/
		temp.timeRemaining.setVisible(true);
		temp.digitalWatch.setText(hour + "h:" + minute + "m:" + second + "s");
		if (hour == 0 && minute == 0 && second == 0) {
			temp.digitalWatch.setVisible(false);
			temp.timeRemaining.setText("Enjoy your meal!");
			temp.timeRemaining.setBounds(260, 280, 150, 70);
		}
	}
	
	
	public void actionPerformed(ActionEvent e) {
		
		// Get the text field content
		String s1 = tf.getText();
		
		// Text field is null or empty
		if (s1 == null || s1.isEmpty()) {
			// do nothing
		}
		
		// Text field is not null and not empty
		else {
			
			// Check if text field input is numeric
			if (checkDigit(s1)) {				
				int a = Integer.parseInt(s1);	// If so, convert to an integer
				
				// If the input is a negative number
				if (a<=0) {
					negativeTimeInput();		// Pop up dialog for negative number
				}
				// If the input is a non-negative number
				else {
					validTimeInput();			// Pop up dialog for correct input
					digitalWatch(a);
				}
			}
			// The case the text field contains at least a blank
			else {
				invalidTimeInput();				// Pop up dialog for incorrect input format
			}
		}
	}
}