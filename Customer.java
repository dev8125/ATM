package HW1;

/*
 * CS 151 HW 1
 * @author Dev Patel 
 * @version 01
 */

public class Customer 
{
	private String name; 
	private CashCard card;
	
	/*
	 * To construct a Customer 
	 * @param name is the name of the Customer 
	 */
	public Customer (String name)
	{
		this.name = name;
	}
	
	/*
	 * Returns the Customer's name
	 * @return the Customer's name
	 */
	public String getName()
	{
		return name;
	}
	
	/*
	 * Sets a cash card to a customer
	 * @param card the card to be set to the CashCard
	 */
	public void setCashCard(CashCard card)
	{
		this.card = card;
	}
	
	/*
	 * Returns the CashCard of the customer 
	 * @return the cusomer's card
	 */
	public CashCard getCashCard()
	{
		return card; 
	}
}
