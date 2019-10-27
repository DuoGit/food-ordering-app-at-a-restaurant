/*******
 * Adjust the menu with different menu panels
 *******/

import javax.swing.*;
import java.awt.event.*;

public class Customer_v5 implements ActionListener {
	
	JFrame cwindow;
	JPanel menupanel = new JPanel();
	JPanel menupanel2 = new JPanel();
	JButton Pizza, Spaghetti, Salad, Beefsteak, Lasagna, Proceed;
	
	JDialog d1 = new JDialog();
	JDialog d2 = new JDialog();
	JLabel l3 = new JLabel("Thank you for your order!");
	JLabel timeRemaining = new JLabel("Remaining time: ");
	JLabel digitalWatch = new JLabel();
	JLabel price = new JLabel("to pay:							"+0);
	
	// create variables for actionPerformed()
	
	int i=0, proceedCheck=0;		 // Done by Duong
	int 	pizzaRow=0,
			spaghettiRow=0,
			saladRow=0,
			beefsteakRow=0,
			lasagnaRow=0;
	
	int 	pizzaCounter=0,
			spaghettiCounter=0,
			saladCounter=0,
			beefsteakCounter=0,
			lasagnaCounter=0;
	
	int		pricePizza=5,
			priceSpaghetti=4,
			priceSalad=3,
			priceBeefsteak=9,
			priceLasagna=7,
			bill=0;
	

	
	
	// Create a table
	String[] columnNames={ "Menu", "Price", "Quantity" };
	int column = columnNames.length;
	String[][] data= new String[5][3];
	JTable table = new JTable(data,columnNames);

	
	// Constructor
	
	Customer_v5(Restaurant_v5 d) { 			// Done by Duong 
		// TODO Auto-generated method stub
		cwindow = new JFrame("Customer");
		cwindow.setSize(700, 600);
		
	
		
		//Menu Panel and it´s Buttons
		
		Pizza = new JButton("Pizza");
		Spaghetti = new JButton("Spaghetti");
		Salad = new JButton("Salad");
		Beefsteak = new JButton("Beefsteak");
		Lasagna = new JButton("Lasagna");
		
		//menupanel2.add(game);
		
		
		//Proceed button
		Proceed = new JButton("Proceed");
		cwindow.add(Proceed);
		//Proceed.setLayout(null);
		
		
	
		//Pop up dialog 1 for the case: press Proceed button without ordering food
		d1.setTitle("Attention");
		JLabel l1 = new JLabel("Please order food!");
		l1.setBounds(15,12,300,15);
		d1.add(l1);
		d1.setSize(400,80);
		d1.setModal(true);

		//Pop up dialog 2 for the case: order food after having pressed Proceed
		d2.setTitle("Unable to order food after proceeding");
		JLabel l2 = new JLabel("You already proceeded you orders!");
		l2.setBounds(15,12,300,15); //l2.setBounds(x, y, width, height);
		d2.add(l2);
		d2.setSize(400,80);
		d2.setModal(true);
		
		
		
		
		l3.setBounds(260, 200, 300, 15);
		l3.setVisible(false);
		cwindow.add(l3);
		
	
		
		// Time remaining label
		timeRemaining.setBounds(140, 280, 150, 70);
		timeRemaining.setVisible(false);
		cwindow.add(timeRemaining);
		
		// Digital watch label
		digitalWatch.setBounds(270, 280, 80, 70);
		digitalWatch.setVisible(true);
		cwindow.add(digitalWatch);
		
		
		//menupanel.setBounds(-50,100,600,200);
		//menupanel.set
		menupanel.add(new JLabel("Menu"));
		menupanel.add(Pizza);
		menupanel.add(Spaghetti);
		menupanel.add(Salad);
		menupanel.add(Beefsteak);
		menupanel.add(Lasagna);
		menupanel.add(Proceed);
		//Proceed.setBounds(200, 20, 100, 50);
		

		
		
		menupanel2.add(price);
		menupanel.add(menupanel2);
		
		cwindow.add(menupanel);
		//cwindow.add(menupanel2);
		
		//cwindow.add(menupanel2);
		
		/*
		menupanel2.add(Proceed);
		menupanel2.setBounds(-20, -30, 50, 50);
		menupanel2.setLayout(null);
		menupanel2.setVisible(true);
		cwindow.add(menupanel2);
		*/
		
		
		Pizza.addActionListener(this);				// Done by Duong
		Spaghetti.addActionListener(this);			// Done by Duong
		Salad.addActionListener(this);				// Done by Duong
		Beefsteak.addActionListener(this);			// Done by Duong
		Lasagna.addActionListener(this);			// Done by Duong
		Proceed.addActionListener(this);			// Done by Duong
		
		
		
		
	
		
		
		JScrollPane scrollpane = new JScrollPane(table);

		menupanel.add(scrollpane);
		
		cwindow.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		
		cwindow.setVisible(true);
	}
	
	public void totalPrice(int a, int b,int c){
		
		int totalPrice=0;																
		totalPrice=a*b;																	
		table.setValueAt(Integer.toString(totalPrice)+"€", c, 1); 
		
	
	}
	
	public void toPay(int a){
		bill+=a;
		price.setText("to pay:							"+Integer.toString(bill)+"€");
	}
	
	
	
	
	
	
	
	/**************************************************************************
	 *								actionPerformed()
	 *
	 *				THIS PART OF CODE WAS IMPLEMENTED BY  	THAI DUONG HOANG
	 **************************************************************************/
	
	
	public void proceedButton() {
		
		// No order is made
		if (i == 0) {
			d1.setVisible(true);		// Pop up the dialog d1
		}
		
		// (At least) an order is made
		else {
			if (proceedCheck == 0){
				l3.setVisible(true);		// Successful order
				new Restaurant_v5(this);
			}
			proceedCheck = 1;			// Proceed was already pressed, initially it's 0
			
		}
		
	}
	
	
	public void pizzaButton() {
		// Pizza
		if (pizzaCounter == 0) {		// Pizza is pressed at the first time
			pizzaRow = i;					// Store its row on the displayed table
			table.setValueAt("Pizza", pizzaRow, 0);	// Write Pizza in the table
			
			i++;					// Increase to the next row
		}
		pizzaCounter++;			// Increase the number of Pizzas every time it is pressed
		table.setValueAt(Integer.toString(pizzaCounter), pizzaRow, 2);	// Write the number of Pizzas
		
		
		totalPrice(pricePizza,pizzaCounter,pizzaRow);			 	// Done by Mustapha
		toPay(pricePizza);											// Done by Mustapha
		
		
		
	}
	
	public void spaghettiButton() {
		// Spaghetti
		if (spaghettiCounter == 0) {
			spaghettiRow = i;
			table.setValueAt("Spaghetti", spaghettiRow, 0);
			i++;
		}
		spaghettiCounter++;
		table.setValueAt(Integer.toString(spaghettiCounter), spaghettiRow, 2);
		
		totalPrice(priceSpaghetti,spaghettiCounter,spaghettiRow); 		// Done by Mustapha
		toPay(priceSpaghetti);											// Done by Mustapha
	}
	
	public void saladButton() {
		// Salad
		if (saladCounter == 0) {
			saladRow = i;
			table.setValueAt("Salad", saladRow, 0);
			i++;
		}
		saladCounter++;
		table.setValueAt(Integer.toString(saladCounter), saladRow, 2);
		
		totalPrice(priceSalad,saladCounter,saladRow); 		// Done by Mustapha
		toPay(priceSalad);									// Done by Mustapha
	}
	
	public void beefsteakButton() {
		// Beefsteak
		if (beefsteakCounter == 0) {
			beefsteakRow = i;
			table.setValueAt("Beefsteak", beefsteakRow, 0);
			i++;
		}
		beefsteakCounter++;
		table.setValueAt(Integer.toString(beefsteakCounter), beefsteakRow, 2);
		
		totalPrice(priceBeefsteak,beefsteakCounter,beefsteakRow); 	// Done by Mustapha
		toPay(priceBeefsteak);										// Done by Mustapha
	}
	
	public void lasagnaButton() {
		// Lasagna
		
		if (lasagnaCounter == 0) {
			lasagnaRow = i;
			table.setValueAt("Lasagna", lasagnaRow, 0);
			i++;
		}
		lasagnaCounter++;
		table.setValueAt(Integer.toString(lasagnaCounter), lasagnaRow, 2);
		
		totalPrice(priceLasagna,lasagnaCounter,lasagnaRow); // Done by Mustapha
		toPay(priceLasagna);								// Done by Mustapha
	}
	
	public void actionPerformed(ActionEvent e) {
		
		// Proceed button is pressed
		if (e.getSource() == Proceed) {
			proceedButton();
		}
		
		// One of the other buttons is pressed
		else {
			
			if (proceedCheck != 0) {	// Proceed was pressed so that customer can no longer order
				d2.setVisible(true);	// Pop up a dialog to announce Customer the case
			}
			
			// Customer has not pressed the Proceed button so that other food can still be ordered
			else {
				
				// Pizza
				if (e.getSource() == Pizza) {	// Check if the Pizza button is pressed
					pizzaButton();
				}	// END_of_Pizza
				
				
				// Spaghetti
				else if (e.getSource() == Spaghetti) {	// Check if the Spaghetti button is pressed
					spaghettiButton();
				}	// END_of_Spaghetti
				
				
				// Salad
				else if (e.getSource() == Salad) {	// Check if the Salad button is pressed
					saladButton();
				}	// END_of_Salad
				
				else if (e.getSource() == Beefsteak) {	// Check if the Beefsteak button is pressed
					beefsteakButton();
				}	// END_of_Beefsteak
				
				
				// Lasagna
				else {
					lasagnaButton();
				}	// END_of_Lasagna
				
			}
				
		}
			
	
	/**************************************************************************
	 * 				THIS PART OF CODE WAS IMPLEMENTED BY  	THAI DUONG HOANG
	 * 
	 *							END_of_actionPerformed()
	 **************************************************************************/
	
	
		
		
		
		
	
	
	

	
	}
/*
	public static void main(String[] args) {
		Customer_v5 c = new Customer_v5(null);
		
	}
*/
}
