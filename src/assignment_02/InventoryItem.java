
// Programmer: Sean DePotter
// Filename: InventoryItem.java
// Due Date: 02/26/2016
// Description: Individual Assignment #2 – InventoryItem class file.
package assignment_02;

public class InventoryItem 
{
	//assign threshold and tax rate constants
	private final  int TAX_THRESHOLD = 1000;
	private final  double TAX_RATE = .02;
	private String itemName;
	private String itemTaxStatus;
	private double itemCost;
	private int itemQuantity;
	private double itemValuation;
	private double itemTax;
	private int taxable = 0;
	private int untaxable= 0;
	
	//constructor method to create variables each time new instance is spawned
	public InventoryItem(String name,String tax, double cost, int quantity)
	{
		itemName = name;
		itemTaxStatus = tax;
		itemCost = cost;
		itemQuantity = quantity;
	}
	// set unit cost to set unit cost from input file
	public void setUnitCost (double cost)
	{
		itemCost = cost;
	}
	// set quantity to store quantity from user input
	public void setQuantity (int quantity)
	{
		itemQuantity = quantity;
	}
	//see if item is taxable or not assign to variable
	public void setTaxStatus (String status)
	{
		itemTaxStatus = status;
	}
	//grab unit cost assigned above
	public double getUnitCost ()
	{
		return itemCost;
	}
	//grab quantity assigned above 
	public int getQuantity ()
	{
		return itemQuantity;
	}
	//grab and return tax status string
	public String getTaxStatus()
	{
		return itemTaxStatus;
	}
	
	// get the total inventory cost as a sum of the cost of every item
	public double getTotalInventory()
	{
		return itemCost * itemQuantity;
	}
	//pass through total inventory to get tax owed
	public double getTaxOwed() 
	{
		
		//check if item is taxable
		if (itemTaxStatus == "Taxable")
		{
			//add to taxable
			taxable += 1;
			//return tax on inventory greater than 1000 currency
			if (itemCost>1000)
			{
				return (itemCost - TAX_THRESHOLD)*TAX_RATE;
	
			}
			//if item is taxable and not over 1000
			else
			{
				return 0.0;
			}
		}
		//if item not taxable then give 0$ in tax
		else
		{
			//add to nontaxable
			untaxable += 1;
			return 0.0;
			
		}
		
		
	}
	//count the amount of taxable items
	public int getTaxed()
	{
		return taxable;
	}
	//count amount of untaxable
	public int getunTaxed()
	{
		return untaxable;
	}
}
