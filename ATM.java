package HW1;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

/*
 * CS 151 HW 1
 * @author Dev Patel 
 * @version 01
 */
public class ATM 
{
	private String atmNum;
	private int maxPer;
	private int cashAvail;
	private BankComputer comp;
	private CashCard card;
	private ArrayList<CashCard> cardLog;
	private Scanner in; 
	
	/*
	 * Constructs an ATM 
	 * @param atmNum the ATM corresponding's number 
	 * @param maxPer max cash user can withdraw per transaction 
	 * @param cashAvail money in the bank available
	 */
	public ATM(String atmNum, int maxPer, int cashAvail)
	{
		this.atmNum = atmNum;
		this.maxPer = maxPer;
		this.cashAvail = cashAvail;
		comp = null;
		card = null;
		cardLog = new ArrayList<CashCard>();
		in = ATMSystem.in;
	}
	
	/*
	 * Connects the ATM to a computer 
	 * @param comp the computer being connected
	 * Precondition: needs a computer
	 * Postcondition: links a computer
	 */
	public void connectComp(BankComputer comp)
	{
		this.comp= comp;
		comp.connectATM(this);
	}
	
	/*
	 * Disconnects a comp to an ATM
	 * @param comp computer to be disconnected
	 * Precondition: needs to be connected
	 * Postcondition: disconnects the comp
	 */
	public void disconnectComp(BankComputer comp)
	{
		this.comp= null;
		comp.disConnectATM(this);
	}
	
	/*
	 * Returns the ATM number
	 * @return atmNum
	 */
	public String getATMNum()
	{
		return atmNum; 
	}
	
	/*
	 * Returns a string with the ATM's specs
	 * @return the ATM's specs
	 */
	public String toString()
	{
		return "ATM " + atmNum + " from Bank " + comp.getBank().getBankID()
				+ "\nThe maximum amount of cash a card can withdraw: " 
				+ cashAvail + "\n";
	}
	
	/*
	 * Checks if the ATM is available
	 * @return true is no card is accessing the ATM
	 */
	public boolean available()
	{
		if(card == null)
			return true;
		else
			return false;
	}
	
	/*
	 * Sets the card to the ATM
	 * Precondition: needs an empty ATM
	 * Postcondition: sets the card to ATM
	 */
	public void setInsertedCard(CashCard card)
	{
		if (available())
		{
			this.card = card;
			checkExpirationDate();
		}
		else
		{
			System.out.println("This ATM is out of order. "
					+ "Please choose a different one.");
		}
	}
	
	/*
	 * Remove the card from ATM
	 */
	public void removeCard()
	{
		card = null; 
	}
	
	/*
	 * Checks the card's bankID and password 
	 */
	public void checkCard()
	{
		if(comp.checkID(card.getBankID()))
		{
			System.out.println("Card ID valid.");
			checkPassword();
		}
		else
		{
			System.out.println("Card ID invalid for ATM");
			card = null;
		}
	}
	/*
	 * Checks the password after the bank ID is matched
	 */
	private void checkPassword()
	{
		System.out.println("Enter password:");
		String userInput = "";
		userInput= in.nextLine();
		
		if(comp.passwordCheck(userInput, card))
		{
			System.out.println("Card accepted.");
			dialouge();
		}
		else
		{
			System.out.println("Incorrect password. Card Ejected.");
			card = null; 
		}
	}
	
	/*
	 * Returns prompt to user 
	 */
	private void dialouge()
	{	
		System.out.println("\nChoose the Bank Account Number:");
		Account acc = displayAccounts();
		if(acc == null)
			removeCard();
		else
			withdrawCash(acc);
	}
	
	/**
	 * Displays the accounts that the inserted cash card can access for user
	 * selection.
	 * @return the account the user would like to withdraw from
	 */
	public Account displayAccounts()
	{
		int count = 1;
		ArrayList<Account> accounts = card.getAccount();
		for (int i = 0; i < accounts.size(); i++)
		{
			System.out.println("(" + count + ") "
					+ " (#" + accounts.get(i).getAccountNum() + ") ");
			count++;
		}
		System.out.println("(" + count + ") Cancel");
		int input = Integer.parseInt(in.nextLine());
		if (input == count)
		{
			System.out.println("Your transaction has been canceled."
					+ "\nCard has been ejected.");
			return null;
		}
		else
			return accounts.get(input - 1);
	}
	
	
	
	/*
	 * Checks if expiration date has passed
	 */
	public void checkExpirationDate()
	{
		if(card.getExpirationDate().after(new GregorianCalendar()))
		{
			cardLog.add(card);
			checkCard();
		}
		else
		{
			System.out.println("This card is expired.");
			card = null; 
		}
	}
	
	/*
	 * Set the amount of money in the ATM
	 * @param amount is the amount of money put into the ATM 
	 * Precondition: an account to set money to
	 * Postcondition: sets money to an account
	 */
	public void setCash(int amount)
	{
		cashAvail = amount; 
	}
	
	/*
	 * Asks user how much money to be withdrawn
	 * @param the account being withdrawn from
	 * Precondition: An account to withdrawn from
	 * Postcondition: Takes money from an account and gives it out
	 */
	private void withdrawCash(Account acc)
	{
		System.out.println("Do you want to withdraw? Y/N");
		String userInput= "";
		userInput= in.nextLine();
		if(userInput.equals("N"))
				{
		System.out.println("Card ejected.");
		removeCard();
				}
		else if(userInput.equals("Y"))
		{
			System.out.println("Enter amount to be withdrawn:");
			int amount = Integer.parseInt(in.nextLine());
			if(amount <= maxPer) // withdrawn amount ok
			{
				if(comp.checkAmount(amount, acc))
				{
					System.out.println("Amount approved.");
					System.out.println("$"+(maxPer - amount) +" left in account");
					maxPer = maxPer-amount;
					dispenseMoney(amount, acc);
				}
			}
			else
			{
				System.out.println("Denied. Amount requested greater than balance. Try smaller amount.");
				withdrawCash(acc);
			}
		}
		}
	
	/*
	 * ATM gives customer cash
	 * @param money how much cash given back
	 * Precondition: Needs money in balance
	 * Postcondition: Lowers the amount in balance and gives cash out
	 */
	public void dispenseMoney(int amount, Account acc)
	{
		cashAvail = cashAvail - amount; 
		withdrawCash(acc);
	}
	
}

