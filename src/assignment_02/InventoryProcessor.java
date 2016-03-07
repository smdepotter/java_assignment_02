
// Programmer: Sean DePotter
// Filename: InventoryProcessor.java
// Due Date: 02/26/2016
// Description: Individual Assignment #2 – InventoryItem class file.

/* My code is able to be compiled but seems to break at line 69 when reading the lines from the input file. I was unable to find a solution
 * 
 */

package assignment_02;
//import of jOption/Scanner/IO
import javax.swing.JOptionPane;
import java.util.Scanner;
import java.io.*;

public class InventoryProcessor
{
	
	public static void main (String[] args) throws IOException
	{
		final String INPUT_FILE = "ItemList.txt"; //input file containing info about each item
		final String OUTPUT_FILE = "ItemValuation.txt"; // output file containing summary of processed info 
		String itemName; // hold name of the item
		String itemTaxStatus; //hold if item is taxable
		double itemCost; //hold cost of the item
		int itemQuantity; //hold how many of the item
		
		
		File myFile = new File(OUTPUT_FILE); // Check to create before append
		if(!myFile.exists()) // Make Item Valuation if Item Val file doens't exist
		{
			PrintWriter file = new PrintWriter(OUTPUT_FILE);
		}
		File input = new File (INPUT_FILE);
		
		//check if input file exists in Java folder, exits if not found
		if(!input.exists())
		{
			System.out.println("ItemList.txt not found, ending");
			System.exit(0);
		}
		
		//append data to output file Item Val.txt
		FileWriter fwriter = new FileWriter(OUTPUT_FILE, true);
		PrintWriter outputFile = new PrintWriter(fwriter);
	
		// Scanner object to read input file
		Scanner readinput = new Scanner(input); 
		//accumulator for Inventory valuation in the following while loop
		double totalInventoryValuation = 0.0;
		//accumulator for Tax owed in following while loop
		double totalTaxOwed = 0.0;
		//accumulators for taxed items
		int totalTaxed = 0;
		int totalUntaxed = 0;
		
		String inventoryYear = JOptionPane.showInputDialog(null, "Enter year for inventory:");
		// loop if not 4 digit year, assign variable to inventory year
		while (inventoryYear.length() != 4) 
		{
		System.out.println("Please enter a 4 digit year");	
		inventoryYear = JOptionPane.showInputDialog(null, "What Inventory Year?");	
		}
		//print inventory year at top of entry
		outputFile.println("INVENTORY YEAR: " + inventoryYear);
		//scan each line and print information to output file, error checks for valid user input
		while (readinput.hasNextLine())
		{			
			//read name from file
			itemName = readinput.nextLine();
			//read if item is taxable, put into if statement 
			itemTaxStatus = readinput.nextLine();
			//read the cost and convert to double
			String cost = readinput.nextLine();
			itemCost = Double.parseDouble(cost);
			//request the quantity of the item
			String inventoryNumber = JOptionPane.showInputDialog(null, "Item Name: " + itemName +"\n Enter number of units in inventory:");
			//check to see if inventory number given was null or 0
			while (inventoryNumber.isEmpty() || Integer.parseInt(inventoryNumber)  <= 0)
			{
				inventoryNumber = JOptionPane.showInputDialog(null, "Please enter again. How much of (" + itemName +") is in inventory?");
				
			}
			//convert inventory number to int
			itemQuantity = Integer.parseInt(inventoryNumber);
			// create instance of the InventoryItem class
			InventoryItem inventory = new InventoryItem(itemName, itemTaxStatus, itemCost,itemQuantity); 
			//Call inventory item class methods
			//print name to output file
			outputFile.println("Item Name: " + itemName);
			outputFile.println("Tax Status: " + inventory.getTaxStatus() );
			outputFile.println("Unit Cost: "+ inventory.getUnitCost());
			outputFile.println("Quantity: "+ inventory.getQuantity());
			outputFile.println("Inventory Valuation: "+ inventory.getTotalInventory());
			outputFile.println("Inventory Tax: " + inventory.getTaxOwed());
			outputFile.println(); //blank space for clarity
			
			
			//accumulate the totals of the following variables
			totalInventoryValuation =+inventory.getTotalInventory()  ;
			totalTaxOwed =+ inventory.getTaxOwed() ;
			//accumulate taxable
			totalTaxed =+ inventory.getTaxed();
			totalUntaxed =+ inventory.getunTaxed();
			
		}
		
		//print message to user for totals for end of year
		System.out.println("Total Inventory Valuation: " + totalInventoryValuation + 
				"\nTotal Inventory Tax Owed: " + totalTaxOwed);
		outputFile.println("TOTAL INVENTORY VALUATION: "+ totalInventoryValuation);
		outputFile.println("TOTAL INVENTORY TAX OWED: "+ totalTaxOwed);
		outputFile.println(); //blank space for clarity
		outputFile.println("TOTAL TAXABLE ITEMS: " + totalTaxed); 
		outputFile.println("TOTAL NON-TAXABLE ITEMS: " + totalUntaxed);
		outputFile.println(); //blank space for clarity
		outputFile.println(); //blank space for clarity
		
		//close both files opened
		outputFile.close();
		readinput.close();
		
		
	
	
	
	
	
		
	}
}

