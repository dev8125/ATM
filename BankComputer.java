package HW1;

import java.util.ArrayList;
import java.util.HashMap;

/*
 * CS 151 HW 1
 * @author Dev Patel 
 * @version 01
 */
public class BankComputer
{
	private int compNum; 
	private ArrayList<ATM> atms;
	private Bank bank;
	private HashMap<CashCard, Integer> cardLog;
	
	/*
	 * Constructs a Bank Computer
	 * @param compNum the computer's number
	 */
	public BankComputer(int compNum)
	{
		this.compNum= compNum;
		atms = new ArrayList<ATM>();
		bank = null; 
		cardLog = new HashMap<CashCard, Integer>();
	}
	
	/*
	 * Connects a computer to an ATM
	 * @param a atm to be linked
	 */
	public void connectATM(ATM a)
	{
		atms.add(a);
	}
	
	/*
	 * To disconnect an atm and a computer
	 * @param a the atm to disconnect
	 */
	public void disConnectATM(ATM a)
	{
		atms.remove(a);
	}
	
	/*
	 * Sets a bank to a comp
	 * @param b bank being set 
	 */
	public void setBank(Bank bank)
	{
		this.bank = bank;
	}
	
	/*
	 * Returns the bank connected to the atm
	 */
	public Bank getBank()
	{
		return bank;
	}
	
	/*
	 * Checks is a card is valid for the computer
	 * @param cardID the ID of the card with the bank
	 * @return true if the card belongs to the bank
	 */
	public boolean checkID(String cardID)
	{
		if(bank.getBankID().equals(cardID))
			return true;
		else
			return false;
	}
	
	/*
	 * Checks if the password matches the expected pw
	 * @param userPass the user's input pw
	 * @param card the card given by user
	 * @return true if the password matches 
	 */
	public boolean passwordCheck(String input, CashCard card)
	{
		if(bank.checkPassword(input, card))
			return true;
		else
			return false;
	}
	
	/*
	 * Checks amount entered to withdraw is more than balance
	 * @param requestedAmount amount the user is trying to withdraw
	 * @param account the account to be withdrawn from
	 * @return true if the amount is valid, else false
	 * Precondition: the money in account to be more than requested
	 * Postcondition: if less then moves on to take out in another method
	 */
	public boolean checkAmount(int requestedAmount, Account account)
	{
		if (requestedAmount <= account.getBalance())
		{
			bank.reduceBalance(requestedAmount, account);
			return true;
		}
		else
			return false;
	}
	
	/*
	 * Logs the daily dispenses in a HashMap.
	 * @param insertedCard the card used
	 * @param amount the amount the user is withdrawing
	 * Precondition: an arraylist to hold log
	 * Postcondition adds the card to log
	 */
	public void logDispense(CashCard card, int amount)
	{
		if (cardLog.containsKey(card))
			cardLog.put(card, cardLog.get(card) + amount);
		// First time the cash card has withdrawn today
		else
			cardLog.put(card, amount);
	}
	
	public HashMap<CashCard, Integer> getCardLog()
	{
		return cardLog;
	}
	
	/*
	 * Returns a statement with the Bank computer's specs
	 * @return specs of computer
	 */
	public String toString()
	{
		return "Bank Computer #" + compNum + " - Bank ID: " + bank.getBankID(); 
	}
	
	
}
