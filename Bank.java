package HW1;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

/*
 * CS 151 HW 1
 * @author Dev Patel 
 * @version 01
 */
public class Bank 
{
	private String bankID;
	private BankComputer computer;
	private HashMap<Customer, ArrayList<Account>> customers;
	private HashMap<CashCard, String> cashCards;
	private int nextCardNum;
	
	/*
	 * Constructs a bank with a bank ID parameter 
	 * @param bankID ID tied to the bank 
	 */
	public Bank(String bankID)
	{
		this.bankID = bankID;
		customers = new HashMap<Customer, ArrayList<Account>>();
		cashCards = new HashMap<CashCard, String>();
		nextCardNum = 0;
	}

	/*
	 * Initializes the Bank Computer
	 * @param computer the computer being setup 
	 */
	public void initializeComp(BankComputer computer)
	{
		this.computer = computer;
		computer.setBank(this);
	}
	
	/*
	 * Returns the computer of this bank 
	 * @return this bank's computer
	 */
	public BankComputer getComputer()
	{
		return computer;
	}
	
	/*
	 * Returns the bank ID 
	 * @return the bank's ID
	 */
	public String getBankID()
	{
		return bankID;
	}
	
	/*
	 * Adds a customer a bank 
	 * @param customer customer to be added
	 * @param acc account to add if needed
	 * Precondition: a customer and account not already linked
	 * Postcondition: adds a customer to an account
	 */
	public void addCustomer(Customer customer, Account acc)
	{
		ArrayList<Account> accountList = new ArrayList<Account>();
		accountList.add(acc);
		customers.put(customer, accountList);
	}
	
	/*
	 * Removes a customer from a bank 
	 * @param customer customer to remove
	 * Precondition: a link customer to account
	 * Postcondition: disconnects the two
	 */
	public void removeCustomer(Customer customer)
	{
		customers.remove(customer);
	}
	
	/*
	 * Create a Cash Card with expiration date and password 
	 * @param expirationDate expiration date of the card
	 * @param password password of the card
	 * @return created card
	 */
	public CashCard issueCard(Calendar expirationDate, String password)
	{
		CashCard newCard = new CashCard(bankID, nextCardNum, expirationDate);
		cashCards.put(newCard, password);
		nextCardNum++; // to keep CashCard count of customer 
		return newCard;
	}
	
	/*
	 * Checks a user's inputed password
	 * @param input the string to be checked as the password
	 * @param card the card trying to be accessed 
	 */
	public boolean checkPassword(String input, CashCard card)
	{
		if (cashCards.containsKey(card))
		{
			if (cashCards.get(card).equals(input))
				return true;
		}
		return false;
		}
	
	/*
	 * Reduces an account's balance
	 * @param amount amount to be reduced
	 * @param account account to be withdrawn from 
	 * Precondition: money in an account
	 * Postcondition: reduces the balance
	 */
	public void reduceBalance(int amount, Account account)
	{
		account.withdraw(account.getBalance() - amount);
	}
	
	
	/*
	 * Returns a string of the bank's specs
	 * @return the bank's specs
	 */
	public String toString()
	{
		String statement = "Bank " + bankID + " (" + customers.size() 
				+ " customers)\n";
		int customerCount = 1;
		for (Customer c: customers.keySet())
		{
			CashCard card = c.getCashCard();
			statement = statement + "Customer " + customerCount + ": " + c.getName() 
					+ card.toString() + ", password: " + cashCards.get(card) + "\n";
			customerCount++;
		}
		return statement;
	}

}
